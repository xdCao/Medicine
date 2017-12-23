package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by liubotao
 */


public class TrustCalculatorTest {
    @Test
    public void mytest(){
        int y =TrustCalculator.getInstance().test(1,2);
        System.out.println(y);
    }
}
