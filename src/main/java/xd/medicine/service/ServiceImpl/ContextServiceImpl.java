package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.medicine.dao.autoMapper.PatientMapper;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.PatientExample;
import xd.medicine.entity.dto.EmergencyAttr;
import xd.medicine.entity.dto.PatientEmergency;
import xd.medicine.service.ContextService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created by xdCao on 2017/12/21
 */
@Service
public class ContextServiceImpl implements ContextService{

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientEmergency> getAllPatientsWithEmerg() {
        PatientExample example=new PatientExample();
        example.createCriteria().andSenseAwareEqualTo(true);
        List<Patient> patientList = patientMapper.selectByExample(example);
        List<PatientEmergency> list=new ArrayList<>();
        for (Patient patient:patientList){
            PatientEmergency patientEmergency=new PatientEmergency();
            patientEmergency.setPatient(patient);
            EmergencyAttr emergencyAttr=new EmergencyAttr();
            patientEmergency.setEmergencyAttr(emergencyAttr);
            list.add(patientEmergency);
        }
        return list;
    }

    @Override
    public EmergencyAttr getEmergencyAttrPeriodly() {
        EmergencyAttr emergencyAttr=new EmergencyAttr();
        return emergencyAttr;
    }





}
