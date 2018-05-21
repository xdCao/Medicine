package xd.medicine.controller;

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
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.*;
import xd.medicine.service.*;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static xd.medicine.utils.MathUtils.getRandomArray;
import static xd.medicine.utils.MathUtils.getRandomFloat;
import static xd.medicine.utils.MathUtils.getRandomInt;

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

    @Autowired
    private PostDutyService postDutyService;

    @Autowired
    private PostDutyLogService postDutyLogService;

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
//        LOGGER.info("userType: "+userType+" , userId: "+userId+" , patientId: "+patientId);
//        if (emergMapCache.containsKey(patientId)){
//            boolean remove = emergMapCache.get(patientId).remove(userType + ":" + userId);
//            LOGGER.info("移除病人： "+patientId+" 请求队列中的： 医生"+userId+" remove:"+remove);
//        }else {
//            LOGGER.info("缓存中已没有该病人： "+patientId+"的紧急请求");
//        }
//        return new FrontResult(200,null,null);

        LOGGER.info("userType: "+userType+" , userId: "+patientId+" , patientId: "+userId);
        if (emergMapCache.containsKey(userId)){
            boolean remove = emergMapCache.get(userId).remove(userType + ":" + patientId);
            LOGGER.info("移除病人： "+userId+" 请求队列中的： 医生"+patientId+" remove:"+remove);
        }else {
            LOGGER.info("缓存中已没有该病人： "+userId+"的紧急请求");
        }
        return new FrontResult(200,null,null);
    }

    @RequestMapping(value = "/msg/riskRequest",method = RequestMethod.POST)
    public FrontResult riskRequest(Integer userType,
                            Integer userId,
                            Integer patientId,
                            Integer purpose,
                            Integer content,
                            Integer mode,
                            Integer time,
                            Integer doPro
                            ){
        /* 如果是主治医生，则不需要执行下面的步骤，直接返回授权 */
        /*

        if( userType.equals(1) && patient.getPatient().getDoctorId() == userId.intValue() ){
            DutySensitivity dutySensitivity=new DutySensitivity(null, 0 ,0,0,0,4,
                    null,0, 0 , 0, 0,0,
                    0,Constants.getrThs(),0);
            return new FrontResult(200,dutySensitivity,null);
        }
        */
        PatientWithTrust patient=patientService.getPatientById(patientId);
        AuthRequest authRequest=new AuthRequest(userType, userId, patientId);
        /*获取事前义务并分配*/
        int calGrade = -1;
        List<FulfilledProDuty> fulfilledProDutyList = null;
        List<ProDuty> allProDutyList = proDutyService.getProDutiesByChosen(true);

        float riskThs = Constants.getrThs();

        if(doPro.equals(1)){
            int maxDuty = 9;
            if(allProDutyList.size()<9){
                maxDuty = allProDutyList.size();
            }
            int proNum = getRandomInt( 5, maxDuty ); //如果分配事前义务，则随机分配5-9个
            int proDutyIndex[] = getRandomArray( 0 , maxDuty-1, proNum );
            List<ProDuty> proDutyList = new ArrayList<>();
            for(int i=0;i<proNum;i++){
                ProDuty proDuty = allProDutyList.get(proDutyIndex[i]);
                float r = new Random().nextFloat();
                if(r < 0.5){  //随机设置是否强制，0为强制，1为不强制
                    proDuty.setType((byte)0);
                }else{
                    proDuty.setType((byte)1);
                }
                proDutyList.add(proDuty);
            }
            fulfilledProDutyList = DutyExecutor.executeProDuties(proDutyList);
            calGrade = authHelper.calGrade(fulfilledProDutyList);
            authHelper.updateProDutyLog(fulfilledProDutyList,authRequest);
        }

        /*计算risk*/

        Doctor doctor = null;
        Others others=null;
        List<Integer> sensitivityItems=null;
        float poobTrustOld;
        if (userType.equals(1)){
            doctor=doctorService.getDoctorById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(doctor.getIsin()?1:0);
            //sensitivityItems.add(doctor.getIsFree()?1:0);
            sensitivityItems.add(time);
            sensitivityItems.add(purpose);/*0:治病，1：科研，2：教学，3：其他*/
            sensitivityItems.add(content);/*0：基本信息，1：可信信息，2：病情相关信息，3：全部*/
            sensitivityItems.add(patient.getPatient().getRoleLevel());/*0:普通任务，1：公众人物，2：保密人物*/
            sensitivityItems.add(mode);/*0:读，1：写，2：修改*/
            poobTrustOld = doctor.getPoobTrust();
        }else if(userType.equals(2)){
            others=othersService.getOthersById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(others.getIsInHos()?1:0);
            //sensitivityItems.add(others.getIsOnWork()?1:0);
            sensitivityItems.add(time);
            sensitivityItems.add(purpose);
            sensitivityItems.add(content);
            sensitivityItems.add(patient.getPatient().getRoleLevel());
            sensitivityItems.add(mode);
            poobTrustOld = others.getPoobTrust();
        }else {
            return new FrontResult(500,null,"用户类型错误");
        }

        /*资源敏感值*/
        float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);

        /* 整体可信值 */
        float unTrust = authHelper.calUnTrust(authRequest);
        float risk = sensitivity - unTrust;

        DutySensitivity dutySensitivity=new DutySensitivity(fulfilledProDutyList,calGrade,sensitivity,unTrust,risk,0,
                null,0, 0 , 0, 0,
                0,0,Constants.getrThs(),0);

        /* [authFlag的含义] -1:grade是A级，0:一次授权失败，1：一次授权成功，2：二次授权失败，3：二次授权成功 */
        if(calGrade == 1) {
            dutySensitivity.setAuthFlag(5);
        }else if (risk<=0){
            /*授权*/
            dutySensitivity.setAuthFlag(1);
            //return new FrontResult(200,dutySensitivity,null);
        }else if (risk<= riskThs && calGrade>1){  //grade=1(A级)和grade=-1（没有分配事前义务）都不进入二次授权
            /*二次评估*/
            float probAward = authHelper.reAuthCal( authRequest,calGrade , riskThs);
            dutySensitivity.setProbAward(probAward);

            if(risk <= probAward) {
                dutySensitivity.setAuthFlag(3); //授权
            }else {
                dutySensitivity.setAuthFlag(2);  //拒绝
            }
        }

        if( dutySensitivity.getAuthFlag()==1|| dutySensitivity.getAuthFlag()==3 ){
            /* 获取事后义务并分配 */

            List<PostDuty> allPostDutyList = postDutyService.getPostDutiesByChosen(true);
            int maxPostDuty = 7;
            if(allPostDutyList.size()<7){
                maxPostDuty = allPostDutyList.size();
            }
            int postNum = getRandomInt( 5 , maxPostDuty ); //随机分配5-7个事后义务
            int postDutyIndex[] = getRandomArray( 0 , maxPostDuty-1 , postNum );
            List<PostDuty> postDutyList = new ArrayList<>();
            for(int i=0;i<postNum;i++){
                PostDuty postDuty = allPostDutyList.get(postDutyIndex[i]);
                postDutyList.add(postDuty);
            }

            List<FulfilledPostDuty> fulfilledPostDutyList = DutyExecutor.executePostDuties(postDutyList);
            dutySensitivity.setFulfilledPostDutyList(fulfilledPostDutyList);
            /* 计算基于事后义务的信任更新值 */
            List<Float> numList = authHelper.calNewPoobTrust( fulfilledPostDutyList,authRequest, risk, calGrade , riskThs);
            dutySensitivity.setPoobtp(numList.get(0));
            dutySensitivity.setPoobAward(numList.get(1));
            dutySensitivity.setPoobPenaltyDelay(numList.get(2));
            dutySensitivity.setPoobPenaltyViolate(numList.get(3));
            dutySensitivity.setPoobTrustOld(poobTrustOld);
            float poobTrustNew = poobTrustOld + numList.get(1) - numList.get(2) - numList.get(3);
            if(poobTrustNew<0) poobTrustNew = 0;
            if(poobTrustNew>1) poobTrustNew = 1;
            dutySensitivity.setPoobTrustNew(poobTrustNew);
            try{
                /* 完成状态写入数据库中的日志 */
                authHelper.updatePostDutyLog(fulfilledPostDutyList,authRequest);
                /* 根据事后义务的完成情况更新数据库中主体的poobTrust */
                authHelper.updatePoobTrust(authRequest,numList);
                return new FrontResult(200,dutySensitivity,null);
            }catch (Exception e){
                return new FrontResult(500,dutySensitivity,e.getMessage());
            }
        }else {
            return new FrontResult(500,dutySensitivity,"未获得授权");
        }
    }



    private void checkAndPut(AuthRequest authRequest) {
        boolean containsKey = emergMapCache.containsKey(authRequest.getPatientId());
        if (containsKey){
            emergMapCache.get(authRequest.getPatientId()).add(authRequest.getUserType()+":"+authRequest.getUserId());
        }else {
            List<String> arrayList=new ArrayList<>();
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
