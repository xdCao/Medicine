package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.service.TrustAttrService;

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
        trustCalculator.calMts(10);

    }


}
