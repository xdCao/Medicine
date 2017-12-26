package xd.medicine.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.dto.OutMessage;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.websocket.SocketSessionRegistry;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List; /**
 * created by xdCao on 2017/12/26
 */

public class AuthTask implements Runnable {

    private PatientWithTrust patientWithTrust;
    private List<Doctor> doctors;
    private SimpMessagingTemplate template;
    private SocketSessionRegistry registry;

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
        for (Doctor doctor:doctors){
            Object o = mapCache.get(1 + "," + doctor.getId() + "," + patientWithTrust.getPatient().getId());
            if (o!=null){
                System.out.println("缓存中找到对象");
                userKeysForAuth.add(1+","+doctor.getId());
            }else {
                System.out.println("缓存中为空");
            }
        }
        for (String userKey:userKeysForAuth){
            System.out.println(userKey);
            String sessionId=registry.getSessionId(userKey);
            if (sessionId!=null){
                System.out.println(sessionId);
                template.convertAndSendToUser(sessionId,"/subject/info",
                        new OutMessage("获得授权哦亲"),createHeaders(sessionId));
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
