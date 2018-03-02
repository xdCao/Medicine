package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.dto.*;
import xd.medicine.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xd.medicine.utils.MathUtils.getRandomArray;
import static xd.medicine.utils.MathUtils.getRandomInt;

/**
 * created by liubotao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private OthersService othersService;

    @Autowired
    private ProDutyService proDutyService;

    @Autowired
    private AuthHelper authHelper;

    @Autowired
    private PostDutyService postDutyService;

    @Autowired
    private PostDutyLogService postDutyLogService;

    @Test
    public void riskRequest() {
        int userType = 1;
        int userId = 1;
        int patientId = 1;
        int purpose = 1;
        int content = 1;
        int mode = 1;
        /*获取事前义务并分配*/
        List<ProDuty> proDutyList = proDutyService.getProDutiesByChosen(true);
        List<FulfilledProDuty> fulfilledProDutyList = DutyExecutor.executeProDuties(proDutyList);

        int calGrade = authHelper.calGrade(fulfilledProDutyList);
        /*计算risk*/
        PatientWithTrust patient = patientService.getPatientById(patientId);
        Doctor doctor = null;
        Others others = null;
        List<Integer> sensitivityItems = null;
        float poobTrustOld = 0;
        if (userType == 1) {
            doctor = doctorService.getDoctorById(userId);
            sensitivityItems = new ArrayList<>();
            sensitivityItems.add(doctor.getIsin() ? 1 : 0);
            sensitivityItems.add(doctor.getIsFree() ? 1 : 0);
            sensitivityItems.add(purpose);/*0:治病，1：科研，2：教学，3：其他*/
            sensitivityItems.add(content);/*0：基本信息，1：可信信息，2：病情相关信息，3：全部*/
            sensitivityItems.add(patient.getPatient().getRoleLevel());/*0:普通任务，1：公众人物，2：保密人物*/
            sensitivityItems.add(mode);/*0:读，1：写，2：修改*/
            poobTrustOld = doctor.getPoobTrust();
        } else if (userType == 2) {
            others = othersService.getOthersById(userId);
            sensitivityItems = new ArrayList<>();
            sensitivityItems.add(others.getIsInHos() ? 1 : 0);
            sensitivityItems.add(others.getIsOnWork() ? 1 : 0);
            sensitivityItems.add(purpose);
            sensitivityItems.add(content);
            sensitivityItems.add(patient.getPatient().getRoleLevel());
            sensitivityItems.add(mode);
            poobTrustOld = others.getPoobTrust();
        } else {
            System.out.println("用户类型错误！");
        }

        /*资源敏感值*/
        float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);
        AuthRequest authRequest = new AuthRequest(userType, userId, patientId);
        /* 整体可信值 */
        float unTrust = authHelper.calUnTrust(authRequest);
        float risk = sensitivity - unTrust;

        DutySensitivity dutySensitivity = new DutySensitivity(fulfilledProDutyList, calGrade, sensitivity, unTrust, risk, 0,
                null, 0, 0, 0, 0, 0, 0);

        /* [authFlag的含义] 0:一次授权失败，1：一次授权成功，2：二次授权失败，3：二次授权成功 */

        if (risk <= 0 && calGrade > 1) { //如果是A级，直接拒绝
            /*授权*/
            dutySensitivity.setAuthFlag(1);
            //return new FrontResult(200,dutySensitivity,null);
        } else if (risk <= Constants.R_THS && calGrade > 1) {
            /*二次评估*/
            int i = authHelper.reAuthCal(authRequest, risk, calGrade);
            if (i == 0) {
                dutySensitivity.setAuthFlag(3);
                //return new FrontResult(200,dutySensitivity,null);
            } else {
                dutySensitivity.setAuthFlag(2);
            }
        }


        if (dutySensitivity.getAuthFlag() == 1 || dutySensitivity.getAuthFlag() == 3) {
            /* 获取事后义务并分配 */
            List<PostDuty> postDutyList = postDutyService.getPostDutiesByChosen(true);
            List<FulfilledPostDuty> fulfilledPostDutyList = DutyExecutor.executePostDuties(postDutyList);
            dutySensitivity.setFulfilledPostDutyList(fulfilledPostDutyList);
            /* 计算基于事后义务的信任更新值 */
            List<Float> numList = authHelper.calNewPoobTrust(fulfilledPostDutyList, authRequest, risk, calGrade);
            dutySensitivity.setPoobtp(numList.get(0));
            dutySensitivity.setPoobAward(numList.get(1));
            dutySensitivity.setPoobPenaltyDelay(numList.get(2));
            dutySensitivity.setPoobPenaltyViolate(numList.get(3));
            dutySensitivity.setPoobTrustOld(poobTrustOld);
            float poobTrustNew = poobTrustOld + numList.get(1) - numList.get(2) - numList.get(3);
            if (poobTrustNew > 1) poobTrustNew = 1;
            if (poobTrustNew < 0) poobTrustNew = 0;
            dutySensitivity.setPoobTrustNew(poobTrustNew);
            //try{
                /* 完成状态写入数据库中的日志 */
            //    authHelper.updatePostDutyLog(postDutyList,teList,authRequest);
                /* 根据事后义务的完成情况更新数据库中主体的poobTrust */
            //    authHelper.updatePoobTrust(authRequest,numList);
            System.out.println("200!");
            System.out.println(dutySensitivity.toString());
            System.out.println(dutySensitivity.getCalGrade());
            for (int i = 0; i < dutySensitivity.getFulFilledProdutyList().size(); i++) {
                System.out.print(dutySensitivity.getFulFilledProdutyList().get(i).getState() + "  ");
            }
            System.out.println();
            //}catch (Exception e){
            //     System.out.println("501!");
            //    System.out.println(dutySensitivity.toString());
            // }
        } else if(dutySensitivity.getAuthFlag() == 2 ){
            System.out.println("502!");
            System.out.println(dutySensitivity.toString());
            System.out.println(dutySensitivity.getCalGrade());
            for (int i = 0; i < dutySensitivity.getFulFilledProdutyList().size(); i++) {
                System.out.print(dutySensitivity.getFulFilledProdutyList().get(i).getState() + "  ");
            }
            System.out.println();
        } else{
            System.out.println("503!");
            System.out.println(dutySensitivity.toString());
            System.out.println(dutySensitivity.getCalGrade());
            for (int i = 0; i < dutySensitivity.getFulFilledProdutyList().size(); i++) {
                System.out.print(dutySensitivity.getFulFilledProdutyList().get(i).getState() + "  ");
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        List<PostDuty> postDutyList = postDutyService.getPostDutiesByChosen(true);
        for (PostDuty postDuty : postDutyList) {
            System.out.println("{");

            System.out.println("},");
        }
    }


    @Test
    public void gradeTest() {
        int userType = 1;
        int userId = 1;
        int patientId = 1;
        int purpose = 1;
        int content = 1;
        int mode = 1;
        /*获取事前义务并分配*/
        List<ProDuty> proDutyList = proDutyService.getProDutiesByChosen(true);
        int m = 0;
        while (m < 100) {
            System.out.println(authHelper.calGrade(DutyExecutor.executeProDuties(proDutyList)));
            m++;
        }
    }

    @Test
    public void test11(){
        boolean doPro ;
        List<ProDuty> allProDutyList = proDutyService.getProDutiesByChosen(true);
        float r = new Random().nextFloat();
        if(r<0.3){  //0.3的概率不分配事前义务，0.7的概率分配事前义务
            doPro = false;
        }else{
            doPro = true;
        }
        if(doPro){
            int proNum = getRandomInt( 5, 9 ); //如果分配事前义务，则随机分配5-9个
            int proDutyIndex[] = getRandomArray( 0 , 8, proNum );
            List<ProDuty> proDutyList = new ArrayList<>();
            for(int i=0;i<proNum;i++){
                ProDuty proDuty = allProDutyList.get(proDutyIndex[i]);
                r = new Random().nextFloat();
                if(r < 0.5){  //随机设置是否强制，0为强制，1为不强制
                    proDuty.setType((byte)0);
                }else{
                    proDuty.setType((byte)1);
                }
                proDutyList.add(proDuty);
            }
            for(ProDuty pd : proDutyList){
                System.out.println(pd.getDutyContent());
                System.out.println(pd.getType());
            }
        }else{
            System.out.println("!!!");
        }
    }
}