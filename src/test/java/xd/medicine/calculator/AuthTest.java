package xd.medicine.calculator;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.*;
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
    private ProDutyLogService proDutyLogService;

    @Test
    public void riskRequest() {
        int userType = 2;
        int userId = 1;
        int patientId = 1;
        int purpose = 1;
        int content = 1;
        int mode = 1;
        int time = 1;

        PatientWithTrust patient=patientService.getPatientById(patientId);
        AuthRequest authRequest=new AuthRequest(userType, userId, patientId);

        /*获取事前义务并分配*/
        boolean doPro ;
        int calGrade = -1;
        List<FulfilledProDuty> fulfilledProDutyList = null;
        List<ProDuty> allProDutyList = proDutyService.getProDutiesByChosen(true);
        float r = new Random().nextFloat();
        float riskThs = Constants.getrThs();
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
            fulfilledProDutyList = DutyExecutor.executeProDuties(proDutyList);
            calGrade = authHelper.calGrade(fulfilledProDutyList);
            System.out.println(fulfilledProDutyList.size()+"!!");
            authHelper.updateProDutyLog(fulfilledProDutyList,authRequest);
        }

        /*计算risk*/

        Doctor doctor = null;
        Others others=null;
        List<Integer> sensitivityItems=null;
        float poobTrustOld;
        if (userType==1){
            doctor=doctorService.getDoctorById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(doctor.getIsin()?1:0);
            //sensitivityItems.add(doctor.getIsFree()?1:0);
            sensitivityItems.add(time);
            sensitivityItems.add(purpose);/*0:治病，1：科研，2：教学，3：其他*/
            sensitivityItems.add(content);/*0：基本信息，1：可信信息，2：病情相关信息，3：全部*/
            sensitivityItems.add(patient.getPatient().getRoleLevel());/*0:普通任务，1：公众人物，2：保密人物*/
            sensitivityItems.add(mode);/*0:读，1：写，2：修改*/
            poobTrustOld = doctor.getPoobTrust();
        }else if(userType==2){
            others=othersService.getOthersById(userId);
            sensitivityItems=new ArrayList<>();
            sensitivityItems.add(others.getIsInHos()?1:0);
            //sensitivityItems.add(others.getIsOnWork()?1:0);
            sensitivityItems.add(time);
            sensitivityItems.add(purpose);
            sensitivityItems.add(content);
            sensitivityItems.add(patient.getPatient().getRoleLevel());
            sensitivityItems.add(mode);
            poobTrustOld = others.getPoobTrust();
        }else {

            System.out.println("用户类型错误");
            return;
        }

        /*资源敏感值*/
        float sensitivity = SensitivityCalculator.calSensitivity(sensitivityItems);

        /* 整体可信值 */
        float unTrust = authHelper.calUnTrust(authRequest);
        float risk = sensitivity - unTrust;


        DutySensitivity dutySensitivity=new DutySensitivity(fulfilledProDutyList,calGrade,sensitivity,unTrust,risk,0,
                null,0, 0 , 0, 0,0,
                0,Constants.getrThs(),0);

        /* [authFlag的含义] -1:grade是A级，0:一次授权失败，1：一次授权成功，2：二次授权失败，3：二次授权成功,4：是主治医生，无需授权 */

        if(calGrade == 1) {
            dutySensitivity.setAuthFlag(-1);
        }else if (risk<=0){
            /*授权*/
            dutySensitivity.setAuthFlag(1);
            //return new FrontResult(200,dutySensitivity,null);
        }else if (risk<= riskThs && calGrade>1){  //grade=1(A级)和grade=-1（没有分配事前义务）都不进入二次授权
            /*二次评估*/
            float probAward = authHelper.reAuthCal( authRequest, calGrade , riskThs);
            dutySensitivity.setProbAward(probAward);
            if (risk < probAward){

                dutySensitivity.setAuthFlag(3);
                //return new FrontResult(200,dutySensitivity,null);
            }else{
                dutySensitivity.setAuthFlag(2);
            }
        }


        if( dutySensitivity.getAuthFlag()==1|| dutySensitivity.getAuthFlag()==3 ){
            /* 获取事后义务并分配 */

            List<PostDuty> allPostDutyList = postDutyService.getPostDutiesByChosen(true);
            int postNum = getRandomInt( 5 , 7 ); //随机分配5-7个事后义务
            int postDutyIndex[] = getRandomArray( 0 , 6, postNum );
            List<PostDuty> postDutyList = new ArrayList<>();
            for(int i=0;i<postNum;i++){
                PostDuty postDuty = allPostDutyList.get(postDutyIndex[i]);
                postDutyList.add(postDuty);
            }

            List<FulfilledPostDuty> fulfilledPostDutyList = DutyExecutor.executePostDuties(postDutyList);
            dutySensitivity.setFulfilledPostDutyList(fulfilledPostDutyList);
            /* 计算基于事后义务的信任更新值 */
            List<Float> numList = authHelper.calNewPoobTrust( fulfilledPostDutyList,authRequest, risk, calGrade , riskThs);
            dutySensitivity.setPoobtp(numList.get(0));
            dutySensitivity.setPoobAward(numList.get(1));
            dutySensitivity.setPoobPenaltyDelay(numList.get(2));
            dutySensitivity.setPoobPenaltyViolate(numList.get(3));
            dutySensitivity.setPoobTrustOld(poobTrustOld);
            float poobTrustNew = poobTrustOld + numList.get(1) - numList.get(2) - numList.get(3);
            if(poobTrustNew<0) poobTrustNew = 0;
            if(poobTrustNew>1) poobTrustNew = 1;
            dutySensitivity.setPoobTrustNew(poobTrustNew);



            /* 完成状态写入数据库中的日志 */
            authHelper.updatePostDutyLog(fulfilledPostDutyList,authRequest);
                /* 根据事后义务的完成情况更新数据库中主体的poobTrust */
            authHelper.updatePoobTrust(authRequest,numList);

            System.out.println("200!");
            System.out.println(dutySensitivity.toString());
            System.out.println(dutySensitivity.getCalGrade());
            for (int i = 0; i < dutySensitivity.getFulFilledProdutyList().size(); i++) {
                System.out.print(dutySensitivity.getFulFilledProdutyList().get(i).getState() + "  ");
            }
            System.out.println();
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


    @Test
    public void getProdutylogTest(){
        int subType = 2; int subId = 1;
        List<ProDutyLog> proDutyLogList ;
        try {
            proDutyLogList = proDutyLogService.getProDutyLogsBySub((byte)subType,subId);
            if (proDutyLogList!=null&&proDutyLogList.size()>0){
                List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
                for(ProDutyLog proDutyLog : proDutyLogList){
                    String dutyContent = proDutyService.getProDutyById(proDutyLog.getDutyId()).getDutyContent();
                    String patientName = patientService.getPatientById(proDutyLog.getObjId()).getPatient().getName();
                    int state = proDutyLog.getState();
                    int type = proDutyLog.getState()>2?1:0;
                    if(state>2){
                        state = state - 3;
                    }
                    DutyLogForFront dutyLogForFront = new DutyLogForFront(proDutyLog.getObjId(),patientName,
                            dutyContent,  type,0, state);
                    dutyLogForFrontList.add(dutyLogForFront);
                }
                for(DutyLogForFront dutyLogForFront :dutyLogForFrontList){
                    System.out.println(dutyLogForFront.getType());
                    System.out.println(dutyLogForFront.getState());
                    System.out.println("--");
                }

            }else {
                System.out.println("!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getProLog2(){
        int subType =1;
        int subId =1;
        int page = 5;
        int rows = 5;
        try {
            PageInfo<DutyLogForFront> dutyLogForFrontPageInfo = proDutyLogService.getProDutyLogsByPage((byte)subType, subId, page, rows);
            if (dutyLogForFrontPageInfo != null) {
                List<DutyLogForFront> list = dutyLogForFrontPageInfo.getList();
                for(DutyLogForFront dutyLogForFront : list){
                    System.out.println(dutyLogForFront.toString());
                }
            } else {
                System.out.println("该用户暂时还没有事前义务日志记录");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getDoc(){
        PageInfo<AvaDoctor> doctorsByPage = doctorService.getDoctorsByPage(4, 10);
        System.out.println(doctorsByPage.getList().size());

    }
}