package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.TrustAttr;
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

    @Test
    public void trustCalculatorTest(){
        //TrustCalculator trustCalculator = new TrustCalculator();
        //int m= trustCalculator.myTest(11);
        //System.out.println(m);
        trustCalculator.calTrust(10);
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
