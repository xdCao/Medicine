package xd.medicine.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import xd.medicine.cache.EmergMapCache;
import xd.medicine.calculator.TrustCalculator;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.websocket.SocketSessionRegistry;

import java.util.List;

/**
 * created by xdCao on 2017/12/26
 */
/*201:获得授权*/
/*401:授权失效*/
/*200:收到病人通知*/
public class EmergAuthTask implements Runnable {

    private static final Logger LOGGER= LoggerFactory.getLogger("------紧急请求授权处理模块：");

    private PatientWithTrust patientWithTrust;
    private List<Doctor> doctors;
    private SimpMessagingTemplate template;
    private SocketSessionRegistry registry;
    private DoctorTrustResult currentDoctor=new DoctorTrustResult();
    private TrustCalculator trustCalculator;

    private EmergMapCache emergMapCache = EmergMapCache.getInstance();

    public EmergAuthTask(PatientWithTrust patient, List<Doctor> doctors, SimpMessagingTemplate template, SocketSessionRegistry webAgentSessionRegistry, TrustCalculator trustCalculator) {
        this.patientWithTrust=patient;
        this.doctors=doctors;
        this.template=template;
        this.registry=webAgentSessionRegistry;
        this.trustCalculator=trustCalculator;
    }

    @Override
    public void run() {

        LOGGER.info("开始执行授权任务");

        String authUserKey=null;

        List<DoctorTrustResult> avaTs = trustCalculator.getAvaTs(patientWithTrust.getPatient().getId());

        /*查找对应该病人在一份钟内是否有人访问*/
        List<String> userKeys = emergMapCache.get(patientWithTrust.getPatient().getId());
        if (userKeys!=null&&userKeys.size()>0){

            LOGGER.info("1min时间段内有人访问");

            for (DoctorTrustResult doctor:avaTs) {
                for (String userKey : userKeys) {
                    String[] split = userKey.split(":");
                    Integer userType = Integer.valueOf(split[0]);
                    Integer userId = Integer.valueOf(split[1]);
                    if (userType.equals(1) && userId.equals(doctor.getDoctorId())&&doctor.getTrust()>currentDoctor.getTrust()) {

                        LOGGER.info("缓存命中对象 ,UserType: " + userType + " , UserId: " + userId);
                        authUserKey=userKey;
                        currentDoctor=doctor;
                    }
                }
            }

            if (authUserKey!=null){

                LOGGER.info("开始授权");

                String sessionId=registry.getSessionId(authUserKey);
                if (sessionId!=null){
                    System.out.println(sessionId);
                    template.convertAndSendToUser(sessionId,"/subject/info",
                            new OutMessage(201,patientWithTrust.getPatient().getId(),"获得授权哦亲"),createHeaders(sessionId));
                }
            }else {

                LOGGER.info("该病人缓存中没有可信主体");

                /*这个时候后面先来的人直接授权*/
                authUserKey = authorizeKeyByRob(authUserKey, avaTs);
                giveAuth(authUserKey);

            }

        }else {

            LOGGER.info("该病人缓存中为空");

            /*这个时候后面先来的人直接授权*/
            authUserKey = authorizeKeyByRob(authUserKey, avaTs);
            giveAuth(authUserKey);
        }




        /*把之前的缓存清除掉，留给后面准备抢占的用户使用*/
        if (emergMapCache.containsKey(patientWithTrust.getPatient().getId())){
            emergMapCache.remove(patientWithTrust.getPatient().getId());
        }


        /*这里进入下一阶段，当有新的请求到来且信用等级更高，将当前授权对象取消授权，并转移给新来的*/

        LOGGER.info("进入抢占阶段");

        while (true) {

            boolean containsKey = emergMapCache.containsKey(patientWithTrust.getPatient().getId());
            if (containsKey){
                List<String> list = emergMapCache.get(patientWithTrust.getPatient().getId());
                for (String userKey:list){
                    for (DoctorTrustResult doctorTrustResult:avaTs){
                        String[] split = userKey.split(":");
                        Integer userType = Integer.valueOf(split[0]);
                        Integer userId = Integer.valueOf(split[1]);
                        if (userType.equals(1) && userId.equals(doctorTrustResult.getDoctorId())&&doctorTrustResult.getTrust()>currentDoctor.getTrust()) {

                            LOGGER.info("缓存命中对象 ,UserType: " + userType + " , UserId: " + userId);

                            authUserKey=userKey;
                            /*向新对象和就对象分别通知*/
                            String sessionId=registry.getSessionId(1+":"+currentDoctor.getDoctorId());
                            if (sessionId!=null){
                                System.out.println(sessionId);
                                template.convertAndSendToUser(sessionId,"/subject/info",
                                        new OutMessage(401,patientWithTrust.getPatient().getId(),"你的授权已失效"),createHeaders(sessionId));
                            }
                            String newSessionId=registry.getSessionId(authUserKey);
                            if (newSessionId!=null){
                                System.out.println(newSessionId);
                                template.convertAndSendToUser(newSessionId,"/subject/info",
                                        new OutMessage(201,patientWithTrust.getPatient().getId(),"获得授权哦亲"),createHeaders(newSessionId));
                            }
                            currentDoctor=doctorTrustResult;
                        }
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

    private void giveAuth(String authUserKey) {
        if (authUserKey!=null){
            LOGGER.info("开始授权");
            String sessionId=registry.getSessionId(authUserKey);
            if (sessionId!=null){
                System.out.println(sessionId);
                template.convertAndSendToUser(sessionId,"/subject/info",
                        new OutMessage(201,patientWithTrust.getPatient().getId(),"获得授权哦亲"),createHeaders(sessionId));
            }
        }else {
            LOGGER.error("EmergAuthTask.run error: line: "+97);
        }
    }

    private String authorizeKeyByRob(String authUserKey, List<DoctorTrustResult> avaTs) {
        boolean flag=true;
        while (flag){
            /*检查缓存中是否有用户到达，如果到达直接授权并跳出循环，进入抢占阶段*/
            if (emergMapCache.containsKey(patientWithTrust.getPatient().getId())){
                List<String> list = emergMapCache.get(patientWithTrust.getPatient().getId());
                for (String userKey:list){
                    for (DoctorTrustResult doctorTrustResult:avaTs){
                        String[] split = userKey.split(":");
                        Integer userType = Integer.valueOf(split[0]);
                        Integer userId = Integer.valueOf(split[1]);
                        if (userType.equals(1) && userId.equals(doctorTrustResult.getDoctorId())) {

                            LOGGER.info("缓存命中对象 ,UserType: " + userType + " , UserId: " + userId);

                            authUserKey=userKey;
                            currentDoctor=doctorTrustResult;
                            flag=false;
                            break;
                        }
                    }
                    if (!flag){
                        break;
                    }
                }
                emergMapCache.remove(patientWithTrust.getPatient().getId());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return authUserKey;
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
