package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xd.medicine.cache.EmergMapCache;
import xd.medicine.calculator.TrustCalculator;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;
import xd.medicine.service.TrustAttrService;
import xd.medicine.tasks.EmergAuthTask;
import xd.medicine.utils.GsonUtils;
import xd.medicine.websocket.SocketSessionRegistry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 * okokookookookok
 * 目前完成的接口：
 * 1.计算病人总数
 * 2.病人登录
 * 3.根据id查询单个病人
 * 4.查询所有病人
 * 5.分页查询病人
 * 6.查询某个医生的所有病患
 * 7.删除病人
 * 8.更新病人基本信息
 * 9.更新病人信任信息
 * 10.更新病人紧急信息
 * 11.注册/添加病人
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {


    private static final Logger LOGGER= LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private TrustAttrService trustAttrService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private TrustCalculator trustCalculator;

    private EmergMapCache emergMapCache = EmergMapCache.getInstance();


    /**session操作类*/
    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public FrontResult count(){
        return new FrontResult(200,patientService.count(),null);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password, HttpServletRequest request, HttpServletResponse response){
        List<PatientWithTrust> patientByAccount = null;
        try {
            patientByAccount = patientService.getPatientByAccount(account);
            if (password.equals(patientByAccount.get(0).getPatient().getPassword())){
                request.getSession().setAttribute("currentUser",patientByAccount.get(0));
                response.addCookie(new Cookie("id",patientByAccount.get(0).getPatient().getId().toString()));
                return new FrontResult(200,patientByAccount.get(0),null);
            }else {
                return new FrontResult(500,0,"密码错误");
            }
        } catch (Exception e) {
            return new FrontResult(500,null,"用户名错误");
        }
    }


    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public FrontResult getSinglePatient(@RequestParam int patientId){
        try{
            PatientWithTrust patient = patientService.getPatientById(patientId);
            return new FrontResult(200,patient,null);
        }catch (Exception e){
            return new FrontResult(500,null,"找不到该患者");
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public FrontResult getAllPatients(){
        List<PatientWithTrust> allPatients = null;
        try {
            allPatients = patientService.getAllPatients();
            return new FrontResult(200,allPatients,null);
        } catch (Exception e) {
            return new FrontResult(500,null,"患者列表为空");
        }
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public FrontResult getPatientsByPage(@RequestParam int page,@RequestParam int rows){
        PageInfo<PatientWithTrust> patientByPage = patientService.getPatientByPage(page, rows);
        return new FrontResult(200,patientByPage,null);
    }

    @RequestMapping(value = "/doctor",method = RequestMethod.GET)
    public FrontResult getPatientByDoctor(@RequestParam int doctorId){
        List<PatientWithTrust> patientsByDoctor = null;
        try {
            patientsByDoctor = patientService.getPatientsByDoctor(doctorId);
            return new FrontResult(200,patientsByDoctor,null);
        } catch (Exception e) {
            return new FrontResult(500,null,"该医生没有病患");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public FrontResult deletePatient(@RequestParam int patientId){
        PatientWithTrust patientById = null;
        try {
            patientById = patientService.getPatientById(patientId);
            trustAttrService.deleteById(patientById.getPatient().getTrustattrId());
            patientService.deletePatient(patientId);
            return new FrontResult(200,null,null);
        } catch (Exception e) {
            return new FrontResult(500,null,"删除病人出错");
        }

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public FrontResult updatePatient(
                                 @RequestParam Integer id,
                                 @RequestParam Integer doctorId,
                                 @RequestParam String phone,
                                 @RequestParam boolean senseAware,
                                 @RequestParam String illnessCondition,
                                 @RequestParam Integer roleLevel){
        Patient patient= null;
        try {
            patient = new Patient();
            patient.setId(id);
            patient.setDoctorId(doctorId);
            patient.setPhone(phone);
            patient.setSenseAware(senseAware);
            patient.setIllnessCondition(illnessCondition);
            patient.setRoleLevel(roleLevel);
            patientService.updatePatient(patient);
            return new FrontResult(200,patient,null);
        } catch (Exception e) {
            return new FrontResult(500,null,"更新病人失败");
        }

    }

    /*应该在此处发出广播*/
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateEmerg",method = RequestMethod.POST)
    public FrontResult updateEmergency(Integer patientId,
                                       Double temperature,
                                       Integer heartBeat,
                                       Double bloodPressure){
        LOGGER.info("病人紧急信息发生变更");
        patientService.updateEmergency(patientId, temperature, heartBeat, bloodPressure);
        PatientWithTrust patient=patientService.getPatientById(patientId);
        if ((patient.getPatient().getIsInEmergency())&&(patient.getPatient().getSenseAware())){
            //向所有可信主体集进行广播
            //此刻开启一分钟的定时任务
            List<Doctor> doctors = doctorService.getDoctorsByDepartment(patient.getTrustAttr().getDepartment());
            for (Doctor doctor:doctors){
//                if (doctor.getIsin()&&doctor.getIsFree()){
                /*这里注释掉的原因是要求即使不可用的医生也能够收到广播通知*/
                    String userKey=1+":"+doctor.getId();

                    LOGGER.info("向可信主体集成员： "+doctor.getId()+" 进行广播");

                    String sessionId = webAgentSessionRegistry.getSessionId(userKey);
                    if (sessionId!=null){
                        template.convertAndSendToUser(sessionId,"/subject/info",
                                new OutMessage(200,GsonUtils.toJsonString(patient)),createHeaders(sessionId));
                        // todo 这里可能要加上病人可信主体集的信息
                    }
//                }
            }
//            emergMapCache.set(String.valueOf(patientId),System.currentTimeMillis());
            //此处应开启一个新的定时任务
            threadPoolTaskScheduler.schedule(new EmergAuthTask(patient,doctors,template,webAgentSessionRegistry,trustCalculator),new Date(System.currentTimeMillis()+20000));
        }
        return new FrontResult(200,patient,null);
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

    @RequestMapping(value = "/updateTrust",method = RequestMethod.POST)
    public FrontResult updateTrustAttr(Integer trustId,
                                       String demandDegree,
                                       String demandTitle,
                                       String department,
                                       String demandWorkage){
        TrustAttr trustAttr=new TrustAttr();
        trustAttr.setId(trustId);
        trustAttr.setDemandWorkage(Byte.valueOf(demandWorkage));
        trustAttr.setDemandTitle(Byte.valueOf(demandTitle));
        trustAttr.setDemandDegree(Byte.valueOf(demandDegree));
        trustAttr.setDepartment(Byte.valueOf(department));
        trustAttrService.updateTrustAttr(trustAttr);
        return new FrontResult(200,trustAttr,null);

    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public FrontResult addNewPatient(@RequestParam String name,
                                     @RequestParam String account,
                                     @RequestParam String password,
                                     @RequestParam Integer doctorId,
                                     @RequestParam String phone,
                                     @RequestParam boolean senseAware,
                                     @RequestParam String illnessCondition,
                                     @RequestParam String department,
                                     @RequestParam String demandTitle,
                                     @RequestParam String demandWorkage,
                                     @RequestParam String demandDegree,
                                     @RequestParam Integer roleLevel){

        Integer countPatientByAccount = patientService.countPatientByAccount(account);
        if (countPatientByAccount>0){
            return new FrontResult(500,null,"该账户名已存在");
        }
        TrustAttr trustAttr= null;
        Patient patient= null;

        try {
        /*
        病人注册时将其信任信息与个人信息分两部分插入
        因此在注册时先提交信任信息并将trustAttrId返回给前端，再作为参数进行病人注册
         */
            trustAttr = new TrustAttr();
            trustAttr.setDepartment(Byte.valueOf(department));
            trustAttr.setDemandTitle(Byte.valueOf(demandTitle));
            trustAttr.setDemandWorkage(Byte.valueOf(demandWorkage));
            trustAttr.setDemandDegree(Byte.valueOf(demandDegree));
            trustAttrService.insertTrustAttr(trustAttr);


            patient = new Patient();
            patient.setName(name);
            patient.setAccount(account);
            patient.setPassword(password);
            patient.setTrustattrId(trustAttr.getId());
            patient.setDoctorId(doctorId);
            patient.setPhone(phone);
            patient.setSenseAware(senseAware);
            patient.setIllnessCondition(illnessCondition);
            patient.setRegistryDate(new Date());
            patient.setTemperature(0.0);
            patient.setBloodPressure(0.0);
            patient.setHeartBeat(0);
            patient.setIsInEmergency(false);
            patient.setRoleLevel(roleLevel);
            patientService.insertPatient(patient);
            return new FrontResult(200,new PatientWithTrust(patient,trustAttr),null);
        } catch (Exception e) {
            return new FrontResult(500,null,"注册失败");
        }
    }




}
