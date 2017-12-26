package xd.medicine.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.websocket.SocketSessionRegistry;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * created by xdCao on 2017/12/26
 */

public class AuthTask implements Runnable {

    private PatientWithTrust patientWithTrust;
    private List<Doctor> doctors;
    private SimpMessagingTemplate template;
    private SocketSessionRegistry registry;
    private DoctorTrustResult currentDoctor;

    private MapCache mapCache=MapCache.getInstance();

    public AuthTask(PatientWithTrust patient, List<Doctor> doctors, SimpMessagingTemplate template, SocketSessionRegistry webAgentSessionRegistry) {
        this.patientWithTrust=patient;
        this.doctors=doctors;
        this.template=template;
        this.registry=webAgentSessionRegistry;
    }

    @Override
    public void run() {
        System.out.println("开始执行授权任务");
        List<String> userKeysForAuth=new ArrayList<>();


        /*查找对应该病人在一份钟内是否有人访问*/
        List<String> userKeys = mapCache.get(patientWithTrust.getPatient().getId());
        if (userKeys!=null&&userKeys.size()>0){
            for (Doctor doctor:doctors) {
                for (String userKey : userKeys) {
                    String[] split = userKey.split(":");
                    Integer userType = Integer.valueOf(split[0]);
                    Integer userId = Integer.valueOf(split[1]);
                    if (userType.equals(1) && userId.equals(doctor.getId())) {
                        System.out.println("UserType: " + userType + " , UserId: " + userId);
                        System.out.println("缓存中找到对象");
                        userKeysForAuth.add(userKey);
                    }
                }
            }
        }else {
            System.out.println("该病人缓存中为空");
            /*这个时候后面先来的人直接授权*/
            while (true){

                /*检查缓存中是否有用户到达，如果到达直接授权并跳出循环，进入抢占阶段*/

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        for (String userKey:userKeysForAuth){
            System.out.println(userKey);
            String sessionId=registry.getSessionId(userKey);
            if (sessionId!=null){
                /*这里要对userKey做判断，是否是准备授权的对象，不是的话返回失败信息*/
                System.out.println(sessionId);
                template.convertAndSendToUser(sessionId,"/subject/info",
                        new OutMessage("获得授权哦亲"),createHeaders(sessionId));
            }
        }

        /*把之前的缓存清除掉，留给后面准备抢占的用户使用*/
        mapCache.remove(patientWithTrust.getPatient().getId());

        while (true){
            /*这里进入下一阶段，当有新的请求到来且信用等级更高，将当前授权对象取消授权，并转移给新来的*/


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
