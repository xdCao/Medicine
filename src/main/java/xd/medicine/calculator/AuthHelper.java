package xd.medicine.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.*;

import javax.print.attribute.standard.MediaSize;
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
    public float calUnTrust( AuthRequest authRequest){

        //float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);
        float unTrust;
        PatientWithTrust patientWithTrust = patientService.getPatientById(authRequest.getPatientId());
        if(authRequest.getUserType()==1){
            Doctor doctor = doctorService.getDoctorById(authRequest.getUserId());
            DoctorTrustResult doctorTrustResult = trustCalculator.calDocBsTrust(patientWithTrust,doctor);
            unTrust = doctorTrustResult.getTrust() * TRUST_U2 + doctor.getPoobTrust() * (1 - TRUST_U2);

        }else{
            Others others = othersService.getOthersById(authRequest.getUserId());
            unTrust = OTHER_BS_TRUST * TRUST_U2 + others.getPoobTrust() * (1 - TRUST_U2);
        }

        return unTrust;
    }


    /*
    *根据事前义务的完成情况，计算等级grade
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
    public int reAuthCal( AuthRequest authRequest,float risk , int grade){
        int lambda = grade - 2;
        int p = postDutyLogService.countFulfilledPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        int q = postDutyLogService.countPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        float m = (float) p/q;
        if(q==0){
            System.out.println("postDutyLog信息不足！");
            m = (float) 0.8;
        }
        float probAward = m * R_THS * (lambda * D_AWARD + (lambda==0?0:1));
        if(risk <= probAward) {
            return 0;  //授权
        }else {
            return 1;  //拒绝
        }
    }

    /*
    *根据完成情况更新事后义务的日志
     */
    public void updatePostDutyLog(List<PostDuty> postDutyList,List<Integer> teList,AuthRequest authRequest){
        int subType = authRequest.getUserId();
        int subId = authRequest.getUserId();
        int patientId = authRequest.getPatientId();
        for(int i=0;i<postDutyList.size();i++){
            PostDutyLog postDutyLog = new PostDutyLog();
            postDutyLog.setSubType((byte)subType);
            postDutyLog.setSubId(subId);
            postDutyLog.setObjId(patientId);
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

    public void updatePoobTrust(AuthRequest authRequest, List<Float> numList){
        float poobAward = numList.get(1);
        float poobPenaltyDelay = numList.get(2);
        float poobPenaltyViolate = numList.get(3);
        if(authRequest.getUserType()==1) {
            Doctor doctor = doctorService.getDoctorById(authRequest.getUserId());
            float poobTrustNew = doctor.getPoobTrust() + poobAward - poobPenaltyDelay - poobPenaltyViolate;
            if (poobTrustNew > 1) poobTrustNew = 1;
            if (poobTrustNew < 0) poobTrustNew = 0;
            doctor.setPoobTrust(poobTrustNew);
            doctorService.updateDoctor(doctor);
        }else{
            Others other = othersService.getOthersById(authRequest.getUserId());
            float poobTrustNew = other.getPoobTrust() + poobAward - poobPenaltyDelay - poobPenaltyViolate;
            if (poobTrustNew > 1) poobTrustNew = 1;
            if (poobTrustNew < 0) poobTrustNew = 0;
            other.setPoobTrust(poobTrustNew);
            othersService.update(other);
        }
    }



    public List<Float> calNewPoobTrust( List<PostDuty> postDutyList, List<Integer> teList, AuthRequest authRequest, float risk, int grade){
        List<Float> numList = new ArrayList<>();
        if(postDutyList.size()!=teList.size()){
            System.out.println("Error!!");
            return null;
        }

        int lambda = grade - 2;
        int p = postDutyLogService.countFulfilledPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        int q = postDutyLogService.countPostDutyLogsBySub((byte)authRequest.getUserType().intValue(),authRequest.getUserId());
        float m = (float) p/q;
        if(q==0){
            System.out.println("postDutyLog信息不足！");
            m = (float) 0.8;
        }
        float probAward = m * R_THS * (lambda * D_AWARD + (lambda==0?0:1));
        float poobTp = (-risk) > probAward? (-risk) : probAward;
        List<Integer> list1 = new ArrayList<>(); //存放按时完成的义务编号
        List<Integer> list2 = new ArrayList<>(); //存放延期完成的义务编号
        List<Integer> list3 = new ArrayList<>(); //存放违反状态的义务编号
        float alphaSum1 =0 ,alphaSum2 =0 , alphaSum3 =0;
        for(int i=0;i< postDutyList.size(); i++){
            if(teList.get(i)<= postDutyList.get(i).getPresetTime()){
                list1.add(i);
                alphaSum1 += postDutyList.get(i).getEmer();
            }else if(teList.get(i)<=postDutyList.get(i).getGraceTime()){
                list2.add(i);
                alphaSum2 += postDutyList.get(i).getEmer();
            }else{
                list3.add(i);
                alphaSum3 += postDutyList.get(i).getEmer();
            }
        }
        float poobAward = 0 , poobPenaltyDelay = 0 , poobPenaltyViolate = 0;
        for(Integer index : list1){
            poobAward += poobTp * (postDutyList.get(index).getGraceTime() - teList.get(index))
                    / postDutyList.get(index).getGraceTime() * postDutyList.get(index).getEmer()
                    / alphaSum1;
        }
        if(list1.size()>0) {
            poobAward /= list1.size();
        }

        for(Integer index : list2){
            poobPenaltyDelay += poobTp * (teList.get(index) - postDutyList.get(index).getPresetTime())
                    / postDutyList.get(index).getGraceTime() * postDutyList.get(index).getEmer()
                    / alphaSum2;
        }
        if(list2.size()>0) {
            poobPenaltyDelay /= list2.size();
        }

        for(Integer index : list3){
            poobPenaltyViolate += poobTp * teList.get(index) / postDutyList.get(index).getGraceTime()
                    * postDutyList.get(index).getEmer() / alphaSum3;
        }
        if(list3.size()>0) {
            poobPenaltyViolate /= list3.size();
        }

        numList.add(poobTp);
        numList.add(poobAward);
        numList.add(poobPenaltyDelay);
        numList.add(poobPenaltyViolate);
        return numList;
    }

}
