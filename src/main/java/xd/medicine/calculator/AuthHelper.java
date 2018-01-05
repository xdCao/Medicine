package xd.medicine.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;

import java.util.List;

import static xd.medicine.calculator.Constants.OTHER_BS_TRUST;
import static xd.medicine.calculator.Constants.R_THS;
import static xd.medicine.calculator.Constants.TRUST_U2;

/**
 * created by liubotao
 */

@Component
public class AuthHelper {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private OthersService othersService;
    @Autowired
    private TrustCalculator trustCalculator;

    public int authCal(List<Integer> sensitivityItems, AuthRequest authRequest){
        float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);
        float unTrust;
        PatientWithTrust patientWithTrust = patientService.getPatientById(authRequest.getPatientId());
        if(authRequest.getUserType()==0){
            Doctor doctor = doctorService.getDoctorById(authRequest.getUserId());
            DoctorTrustResult doctorTrustResult = trustCalculator.calDocBsTrust(patientWithTrust,doctor);
            unTrust = doctorTrustResult.getTrust() * TRUST_U2 + doctor.getPoobTrust() * (1 - TRUST_U2);
        }else{
            Others others = othersService.getOthersById(authRequest.getUserId());
            unTrust = OTHER_BS_TRUST * TRUST_U2 + others.getPoobTrust() * (1 - TRUST_U2);
        }
        float risk = sensitivity - unTrust;
        if(risk <= 0){
            return 0; //授权
        }else if(risk <= R_THS){
            return 1; //二次评估
        }else{
            return 2; //拒绝
        }
    }

    public int reAuthCal(float risk , AuthRequest authRequest){
        return 0;
    }

}
