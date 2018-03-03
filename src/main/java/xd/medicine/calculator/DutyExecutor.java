package xd.medicine.calculator;

import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.dto.FulfilledPostDuty;
import xd.medicine.entity.dto.FulfilledProDuty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xd.medicine.utils.MathUtils.getRandomInt;

/**
 * created by liubotao
 */
public class DutyExecutor {

    /*
     *执行事前义务集，随机方式，返回执行状态list
     * 返回list中，0:violate，1：fulfill.common，2：fulfill.good
     */
    public static List<FulfilledProDuty> executeProDuties(List<ProDuty> proDutyList){
        List<FulfilledProDuty> fulfilledProDutyList = new ArrayList<>();
        for(ProDuty proDuty: proDutyList){
            FulfilledProDuty fulfilledProDuty = new FulfilledProDuty(proDuty, 0 );
            float r = new Random().nextFloat();
            // 0.95的概率完成，0.2的概率优秀地完成
            if(proDuty.getType() == 0 ){
                if(r>0.8) {
                    fulfilledProDuty.setState(2);
                }else if(r>0.05){
                    fulfilledProDuty.setState(1);
                }else{
                    //System.out.println(r+"==");
                    fulfilledProDuty.setState(0);
                }
            }else{ //0.5的概率完成
                if(r>0.5){
                    fulfilledProDuty.setState(1);
                }else{
                    fulfilledProDuty.setState(0);
                }
            }
            fulfilledProDutyList.add(fulfilledProDuty);
        }
        return fulfilledProDutyList;
    }

    /*
     *执行事后义务集，随机方式，返回执行完成时间list
     * 返回list中，int值代表实际完成的时间（天数）
     */
    public static List<FulfilledPostDuty> executePostDuties(List<PostDuty> postDutyList){
        List<FulfilledPostDuty> fulfilledPostDutyList = new ArrayList<>();
        int t;
        for(PostDuty postDuty : postDutyList){
            //state:0为violate，1为delay-fulfill，2为fulfill
            FulfilledPostDuty fulfilledPostDuty = new FulfilledPostDuty(postDuty,9999,-1);
            float r = new Random().nextFloat();
            if(r>0.75){ //0.25的概率超过宽限时间
                t = getRandomInt(postDuty.getGraceTime()+1,postDuty.getGraceTime()+10);
                fulfilledPostDuty.setState(0);
            }else if(r>0.5){ //0.25的概率在规定时间和宽限时间之间
                t = getRandomInt(postDuty.getPresetTime()+1,postDuty.getGraceTime());
                fulfilledPostDuty.setState(1);
            }else{ //0.5的概率按时完成
                t = getRandomInt(1,postDuty.getPresetTime());
                fulfilledPostDuty.setState(2);
            }
            fulfilledPostDuty.setFulFilledTime(t);
            fulfilledPostDutyList.add(fulfilledPostDuty);
        }
        return fulfilledPostDutyList;
    }


}
