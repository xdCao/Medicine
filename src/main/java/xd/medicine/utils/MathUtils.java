package xd.medicine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * created by liubotao
 */
public class MathUtils {
    public static float getRandom(float min , float max){
        return min + new Random().nextFloat() * (max - min);
    }

    /* 抽样辅助函数，从1-total中随机取n个数 */
    public static List<Integer> sampling(int total , int n) {
        if(n>total)
            return null;
        int[] numbers = new int[total];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        Integer[] results = new Integer[n];
        for (int i = 0; i < results.length; i++) {
            // 取出一个随机数
            int r = (int) (Math.random() * total);
            results[i] = numbers[r];
            // 排除已经取过的值
            numbers[r] = numbers[total - 1];
            total--;
        }
        Arrays.sort(results);
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(results));
        return list;
    }
}
