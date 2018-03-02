package xd.medicine.utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * created by liubotao
 */
public class MathUtils {
    public static float getRandomFloat(float min , float max){
        return min + new Random().nextFloat() * (max - min);
    }

    public static int getRandomInt(int min , int max){
        return (int) (min + new Random().nextFloat() * (max - min +1));
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

    /* 在min-max范围内随机取n个不重复的数 */
    public static int[] getRandomArray(int min,int max,int n){
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }
}
