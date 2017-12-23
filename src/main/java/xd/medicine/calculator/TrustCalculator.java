package xd.medicine.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.service.TrustAttrService;

import static xd.medicine.calculator.Constants.*;

/**
 * created by liubotao
 */

@Component
public class TrustCalculator {
    private static TrustCalculator instance = null;

    @Autowired
    private TrustAttrService trustAttrService;

    private TrustCalculator() {
    }

    public static TrustCalculator getInstance() {

        if(instance==null) {
            synchronized (TrustCalculator.class) {
                if (instance == null) {
                    instance = new TrustCalculator();
                }
            }
        }
        return instance;

    }

    public int test(int a , int b){
        return a+b+ TEST;
    }

    /*
    计算匹配可信度MT

     */
    public int calMt(Patient patient , Doctor doctor){
        float mt=0;
        //TrustAttr trustAttr = trustAttrService.getTrustAttrById(patient.getTrustattrId());

        //if(trustAttr.getDepartment() == patientTrustAttr.getDepartment()) mt+=1; //判断科室是否匹配


        return 0;
    }

    public int test2(int id){
        if(trustAttrService == null){
            return -187;
        }
        TrustAttr trustAttr = trustAttrService.getTrustAttrById(id);
        return  trustAttr.getDepartment();
    }




}
