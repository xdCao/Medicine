package xd.medicine.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.websocket.SocketSessionRegistry;

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
        List<Doctor> readyForAuth=new ArrayList<>();



    }


}
