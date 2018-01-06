package xd.medicine.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;
import xd.medicine.service.PostDutyLogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xd.medicine.calculator.Constants.*;

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
    @Autowired
    private PostDutyLogService postDutyLogService;

    /*
    * 未知情况下的授权计算
     */
    public int authCal(List<Integer> sensitivityItems, AuthRequest authRequest, int lambda){
        if(lambda<0)
            return 2; //A级，直接拒绝
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


    /*
    *根据事前义务的完成情况，计算等级差lambda
     */
    public int calLamda(List<ProDuty> proDutyList, List<Integer> fulfilledStateList){
        /* a,b,c分别是完成的强制性事前义务数，完成且优秀的强制性事前义务数，完成的非强制先验义务数 */
        int a = 0, b = 0 , c=0;
        /* m,n分别是本次分配的强制和非强制义务的个数 */
        int m = 0, n=0;
        /*统计按时完成的强制义务数和非强制义务数*/
        if(proDutyList.size()!=fulfilledStateList.size()){
            System.out.println("Error! 义务完成时间信息不足！");
        }
        /* fulfilledStateList中0为violate，1为fulfill.common, 2为fulfill.good */
        for(int i = 0; i < proDutyList.size(); i++){
            if(proDutyList.get(i).getType()==0){
                m++;
                if(fulfilledStateList.get(i)==2){
                    a++;
                    b++;
                }else if(fulfilledStateList.get(i)==1) {
                    a++;
                }
            }else{
                n++;
                if(fulfilledStateList.get(i)==1 || fulfilledStateList.get(i)==2) {
                    c++;
                }
            }
        }

        int grade;
        if(a==m && c>0 && b>0){
            grade = 5;
        }else if(a==m && b>0){
            grade = 4;
        }else if(a==m && c>0){
            grade = 3;
        }else if(a==m){
            grade = 2;
        }else{
            grade = 1;
        }
        return grade - 2;

    }

    /*
    *二次评估计算
     */
    public int reAuthCal(float risk , AuthRequest authRequest, int lambda){
        int p = postDutyLogService.countFulfilledPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        int q = postDutyLogService.countPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        float prob_award = (float) p/q * R_THS * (lambda * D_AWARD + (lambda==0?0:1));
        if(risk <= prob_award) {
            return 0;  //授权
        }else {
            return 1;  //拒绝
        }
    }

}
