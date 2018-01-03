package xd.medicine.calculator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static xd.medicine.utils.MathUtils.*;

/**
 * created by liubotao
 */

/* MathUtil测试 */
public class MathUtilTest {

    /*@Test
    public void test1() {
        float a = (float) 1 / 12;
        System.out.println(a);
        for (int i = 0; i < 10; i++)
            System.out.println((0.3 - a) + new Random().nextFloat() * (2 * a));
    }*/

    @Test
    public void test2(){
        for(int i = 0; i<10; i++) {
            List<Integer> list = sampling(10000, 5);
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }




}
