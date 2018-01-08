package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import xd.medicine.cache.EmergMapCache;
import xd.medicine.calculator.AuthHelper;
import xd.medicine.calculator.Constants;
import xd.medicine.calculator.DutyExecutor;
import xd.medicine.calculator.SensitivityCalculator;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.DutySensitivity;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;
import xd.medicine.service.ProDutyService;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import java.util.*;

/**
 * created by xdCao on 2017/12/25
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class BTGController {

    private EmergMapCache emergMapCache = EmergMapCache.getInstance();

    private static final Logger LOGGER= LoggerFactory.getLogger("------请求处理模块： ");

    /**session操作类*/
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private OthersService othersService;

    @Autowired
    private ProDutyService proDutyService;

    @Autowired
    private AuthHelper authHelper;

    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/btgRequest")
    public void btgRequest(String message) throws Exception {
        AuthRequest authRequest= GsonUtils.jsonToObject(message,AuthRequest.class);
        //这里是用户发起授权请求的地方，在此处将用户信息放入MapCache，在定时任务中会取出缓存进行授权
        if (authRequest.getUserType().equals(1)){
            LOGGER.info("获取到来自医生： "+authRequest.getUserId()+" 向病人："+authRequest.getPatientId()+"发起的紧急请求");
            checkAndPut(authRequest);
        }else if (authRequest.getUserType().equals(2)){
            LOGGER.info("获取到来自其他用户： "+authRequest.getUserId()+" 向病人："+authRequest.getPatientId()+"发起的紧急请求");
            checkAndPut(authRequest);
        }

    }

    @RequestMapping(value = "/msg/leave",method = RequestMethod.POST)
    public FrontResult leaveSession(Integer userType,
                                    Integer userId,
                                    Integer patientId){
        LOGGER.info("userType: "+userType+" , userId: "+userId+" , patientId: "+patientId);
        if (emergMapCache.containsKey(patientId)){
            boolean remove = emergMapCache.get(patientId).remove(userType + ":" + userId);
            LOGGER.info("移除病人： "+patientId+" 请求队列中的： 医生"+userId+" remove:"+remove);
        }else {
            LOGGER.info("缓存中已没有该病人： "+patientId+"的紧急请求");
        }
        return new FrontResult(200,null,null);
    }

    @MessageMapping("/msg/riskRequest")
    public FrontResult riskRequest(Integer userType,
                            Integer userId,
                            Integer patientId,
                            Integer purpose,
                            Integer content,
                            Integer mode
                            ){
        /*获取事前义务并分配*/
        List<ProDuty> proDutyList = proDutyService.getProDutiesByChosen(true);
        List<Integer> fullfillStateList = DutyExecutor.executeProDuties(proDutyList);
        int calGrade = authHelper.calGrade(proDutyList, fullfillStateList);
        /*计算risk*/
        PatientWithTrust patient=patientService.getPatientById(patientId);
        Doctor doctor = null;
        Others others=null;
        List<Integer> sensitivityItems=null;
        if (userType.equals(1)){
            doctor=doctorService.getDoctorById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(doctor.getIsin()?1:0);
            sensitivityItems.add(doctor.getIsFree()?1:0);
            sensitivityItems.add(purpose);/*0:治病，1：科研，2：教学，3：其他*/
            sensitivityItems.add(content);/*0：基本信息，1：可信信息，2：病情相关信息，3：全部*/
            sensitivityItems.add(patient.getPatient().getRoleLevel());/*0:普通任务，1：公众人物，2：保密人物*/
            sensitivityItems.add(mode);/*0:读，1：写，2：修改*/
        }else if(userType.equals(2)){
            others=othersService.getOthersById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(others.getIsInHos()?1:0);
            sensitivityItems.add(others.getIsOnWork()?1:0);
            sensitivityItems.add(purpose);
            sensitivityItems.add(content);
            sensitivityItems.add(patient.getPatient().getRoleLevel());
            sensitivityItems.add(mode);
        }else {
            return new FrontResult(500,null,"用户类型错误");
        }

        /*资源敏感值*/
        float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);
        AuthRequest authRequest=new AuthRequest(userType, userId, patientId);
        float risk = authHelper.authCal(sensitivityItems, authRequest, calGrade);

        DutySensitivity dutySensitivity=new DutySensitivity(proDutyList,fullfillStateList,calGrade,sensitivity,risk);

        if (risk<=0){
            /*授权*/
            return new FrontResult(200,dutySensitivity,null);
        }else if (risk<= Constants.R_THS){
            /*二次评估*/
            int i = authHelper.reAuthCal(risk, authRequest, calGrade);
            if (i==0){
                return new FrontResult(200,dutySensitivity,null);
            }else{
                return new FrontResult(500,null,"未获得授权");
            }
        }else {
            /*拒绝*/
            return new FrontResult(500,null,"未获得授权");
        }


    }



    private void checkAndPut(AuthRequest authRequest) {
        boolean containsKey = emergMapCache.containsKey(authRequest.getPatientId());
        if (containsKey){
            emergMapCache.get(authRequest.getPatientId()).add(authRequest.getUserType()+":"+authRequest.getUserId());
        }else {
            ArrayList<String> arrayList=new ArrayList<>();
            arrayList.add(authRequest.getUserType()+":"+authRequest.getUserId());
            emergMapCache.put(authRequest.getPatientId(),arrayList);
        }
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
