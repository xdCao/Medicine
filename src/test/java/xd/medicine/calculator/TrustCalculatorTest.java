package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.service.PatientService;
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

    @Test
    public void trustCalculatorTest(){
        //TrustCalculator trustCalculator = new TrustCalculator();
        //int m= trustCalculator.myTest(11);
        //System.out.println(m);
        for(int i = 1 ; i<7 ; i++) {
            System.out.println("病人id:"+ i + "病人姓名："+ patientService.getPatientById(i).getPatient().getName());
            System.out.println("可信主体集合：");
            List<DoctorTrustResult> doctorTrustResultList = trustCalculator.getAvaTs(i);
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



}
