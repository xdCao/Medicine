package xd.medicine.calculator;

import xd.medicine.entity.bo.Doctor;

import java.util.List;

import static xd.medicine.calculator.Constants.*;
import static xd.medicine.utils.MathUtils.getRandomFloat;

/**
 * created by liubotao
 */
public class SensitivityCalculator {

    public static float calSensitivity(List<Integer> sensitivityItems){
        if(sensitivityItems.size()<6){
            System.out.println("Error!");
            return -1;
        }
        float sensitivity = 0;
        float s[] = new float[6];
        //地点敏感度
        s[0] = sensitivityItems.get(0)==0?getRandomFloat(0,(float)0.5):getRandomFloat((float)0.5,1);
        //访问时间敏感度
        //s[1] = sensitivityItems.get(1)==0?getRandomFloat(0,(float)0.5):getRandomFloat((float)0.5,1);
        if(sensitivityItems.get(1)==0){
            s[1] = getRandomFloat((float) 0.5,(float) 0.6);
        }else if(sensitivityItems.get(1)==1){
            s[1] = getRandomFloat((float) 0.6,(float) 0.7);
        }else if(sensitivityItems.get(1)==2){
            s[1] = getRandomFloat((float) 0.7,(float) 0.85);
        }else{
            s[1] = getRandomFloat((float) 0.85,1);
        }


        //访问目的敏感度
        //float s3 = sensitivityItems.get(1)==0?getRandom((float) 0.3,(float) 0.5):
        //        (sensitivityItems.get(1)==1?getRandom((float) 0.5,(float) 0.7):
        //            (sensitivityItems.get(1)==2?getRandom((float) 0.7,(float) 0.8):getRandom((float) 0.8,1)));
        if(sensitivityItems.get(2)==0){
            s[2] = getRandomFloat((float) 0.3,(float) 0.5);
        }else if(sensitivityItems.get(2)==1){
            s[2] = getRandomFloat((float) 0.5,(float) 0.7);
        }else if(sensitivityItems.get(2)==2){
            s[2] = getRandomFloat((float) 0.7,(float) 0.8);
        }else{
            s[2] = getRandomFloat((float) 0.8,1);
        }

        //访问内容敏感度
        if(sensitivityItems.get(3)==0){
            s[3] = getRandomFloat((float) 0.3,(float) 0.4);
        }else if(sensitivityItems.get(3)==1){
            s[3] = getRandomFloat((float) 0.4,(float) 0.6);
        }else if(sensitivityItems.get(3)==2){
            s[3] = getRandomFloat((float) 0.7,(float) 0.8);
        }else{
            s[3] = getRandomFloat((float) 0.8,1);
        }

        //资源类型敏感度
        if(sensitivityItems.get(4)==0){
            s[4] = getRandomFloat((float) 0.4,(float) 0.6);
        }else if(sensitivityItems.get(4)==1){
            s[4] = getRandomFloat((float) 0.6,(float) 0.8);
        }else{
            s[4] = getRandomFloat((float) 0.8,1);
        }

        //访问模式敏感度
        if(sensitivityItems.get(5)==0){
            s[5] = getRandomFloat((float) 0.3,(float) 0.6);
        }else if(sensitivityItems.get(5)==1){
            s[5] = getRandomFloat((float) 0.6,(float) 0.8);
        }else{
            s[5] = getRandomFloat((float) 0.8,1);
        }

        for(int i = 0; i<6; i++){
            sensitivity += s[i] * SENSITIVITY_W[i];
        }

        return sensitivity;
    }

}
