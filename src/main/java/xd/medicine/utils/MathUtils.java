package xd.medicine.utils;

import java.lang.reflect.Array;
import java.util.*;

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
        List<Integer> list = new ArrayList<>(Arrays.asList(results));
        return list;
    }

    /* 获得中位数 */
    public static float getMiddle(List<Integer> arr){
        Collections.sort(arr);
        if(arr.size()%2==0){
            return (float) (arr.get(arr.size()/2)+arr.get(arr.size()/2-1))/2;
        }else{
            return (float) arr.get(arr.size()/2);
        }
    }
}
