package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.dao.autoMapper.PatientMapper;
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.*;
import xd.medicine.service.PatientService;
import xd.medicine.service.PostDutyService;
import xd.medicine.service.ProDutyService;
import xd.medicine.service.ServiceImpl.PatientServiceImpl;
import xd.medicine.service.TrustAttrService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * created by liubotao
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrustCalculatorTest {

    @Autowired
    private TrustCalculator trustCalculator;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ProDutyService proDutyService;
    @Autowired
    private AuthHelper authHelper;
    @Autowired
    private PostDutyService postDutyService;


    @Test
    public void trustCalculatorTest(){
        //TrustCalculator trustCalculator = new TrustCalculator();
        //int m= trustCalculator.myTest(11);
        //System.out.println(m);
        for(int i = 1 ; i<7 ; i++) {
            System.out.println("病人id:"+ i + "病人姓名："+ patientService.getPatientById(i).getPatient().getName());
            System.out.println("可信主体集合：");
            List<DoctorTrustResult> doctorTrustResultList = trustCalculator.getTs(i);
            for(DoctorTrustResult doctorTrustResult : doctorTrustResultList){
                System.out.println(doctorTrustResult.toString());
            }
        }
        /*List<Integer> p = new ArrayList<>();
        p.add(3);
        p.add(4);
        p.add(0);
        List<Integer> d = new ArrayList<>();
        d.add(4);
        d.add(1);
        d.add(2);

        List<Float> fs =   trustCalculator.calDocMtHelper(p,d);
        for(float f : fs){
            System.out.println(f);
        }*/

    }

    @Test
    public void proDutyTest(){
        List<ProDuty> proDuties = proDutyService.getProDutiesByChosen(true);

        for(int i=0;i<30;i++) {
            List<FulfilledProDuty> list = DutyExecutor.executeProDuties(proDuties);
            //System.out.println(proDuties.size());
            //for(Integer i : list){
            //    System.out.println(i);
            //}
            System.out.println("lambda:" + authHelper.calGrade( list));
        }

    }

    @Test
    public void postDutyTest(){
        List<PostDuty> postDuties = postDutyService.getPostDutiesByChosen(true);

        List<FulfilledPostDuty> list = DutyExecutor.executePostDuties(postDuties);
        for(FulfilledPostDuty fulfilledPostDuty: list) {

            //System.out.println(proDuties.size());
            //for(Integer i : list){
            //    System.out.println(i);
            //}
            System.out.println(fulfilledPostDuty.getFulFilledTime());
        }

    }



    @Test
    public void postDutyTest222(){
        List<PostDuty> postDuties = postDutyService.getPostDutiesByChosen(true);

    }




}
