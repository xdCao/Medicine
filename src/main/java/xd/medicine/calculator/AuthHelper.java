package xd.medicine.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xd.medicine.calculator.Constants.*;
import static xd.medicine.calculator.DutyExecutor.executePostDuties;

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
    @Autowired
    private PostDutyService postDutyService;

    /*
    * 未知情况下的授权计算
    * 返回值是risk
     */
    public float authCal(List<Integer> sensitivityItems, AuthRequest authRequest, int grade){
        if(grade<2)
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
        return risk;
    }


    /*
    *根据事前义务的完成情况，计算等级差lambda
     */
    public int calGrade(List<ProDuty> proDutyList, List<Integer> fulfilledStateList){
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
        return grade;

    }

    /*
    *二次评估计算
     */
    public int reAuthCal(float risk , AuthRequest authRequest, int grade){
        int lambda = grade - 2;
        int p = postDutyLogService.countFulfilledPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        int q = postDutyLogService.countPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        float prob_award = (float) p/q * R_THS * (lambda * D_AWARD + (lambda==0?0:1));
        if(risk <= prob_award) {
            return 0;  //授权
        }else {
            return 1;  //拒绝
        }
    }

    /*
    *执行事后义务，并更新事后义务的日志
     */
    private void updatePostDutyLog(int subType, int subId, Patient patient){
        List<PostDuty> postDutyList = postDutyService.getPostDutiesByChosen(true);
        List<Integer> teList = DutyExecutor.executePostDuties(postDutyList);
        for(int i=0;i<postDutyList.size();i++){
            PostDutyLog postDutyLog = new PostDutyLog();
            postDutyLog.setSubType((byte)subType);
            postDutyLog.setSubId(subId);
            postDutyLog.setObjId(patient.getId());
            postDutyLog.setFulfillTime((byte)teList.get(i).intValue());
            postDutyLog.setDutyId(postDutyList.get(i).getId());
            if(teList.get(i)<= postDutyList.get(i).getPresetTime()){
                postDutyLog.setState((byte)2);
            }else if(teList.get(i)<=postDutyList.get(i).getGraceTime()){
                postDutyLog.setState((byte)1);
            }else{
                postDutyLog.setState((byte)0);
            }
            postDutyLogService.insertNewPostDutyLog(postDutyLog);
        }
    }

}
