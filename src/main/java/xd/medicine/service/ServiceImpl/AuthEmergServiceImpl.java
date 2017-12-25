package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.medicine.dao.autoMapper.PatientMapper;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.PatientExample;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.AuthService;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */
@Service
public class AuthEmergServiceImpl implements AuthService {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientWithTrust> judgeBroadcast(Integer doctorId) {
        Doctor doctor=doctorService.getDoctorById(doctorId);
        List<PatientWithTrust> patientWithTrusts=new ArrayList<>();
        List<PatientWithTrust> sensePatientInEmergency = patientService.sensePatientInEmergency();
        for (PatientWithTrust patientWithTrust:sensePatientInEmergency){
            if (patientWithTrust.getTrustAttr().getDepartment().equals(doctor.getDepartment())){
                patientWithTrusts.add(patientWithTrust);
            }
        }
        return patientWithTrusts;

    }





}
