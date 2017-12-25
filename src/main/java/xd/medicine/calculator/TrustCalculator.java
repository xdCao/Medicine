package xd.medicine.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.UserLog;
import xd.medicine.entity.dto.DoctorTrustResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.CommentService;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xd.medicine.calculator.Constants.*;
import static xd.medicine.utils.MathUtils.*;

/**
 * created by liubotao
 */

@Component
public class TrustCalculator {

    @Autowired
    private PatientService patientService;

    @Autowired
    private CommentService commentService;


    /*
    计算匹配可信度MT
    输入一个病人id，输出匹配到的主体集及其匹配可信度、历史行为可信度、总体可信度，暂时仅打印出来
     */
    public void calTrust( int patientId ) {
        float mt,hbt,rcm,rep,trust;
        PatientWithTrust patient = patientService.getPatientById(patientId);
        List<Doctor> doctorList = patientService.getSisDoctorsByPatientId(patientId);  //获得满足科室要求的所有医生，即候选主体集合SIS
        for (Doctor doctor : doctorList) {

            if (patient.getPatient().getDoctorId() == doctor.getId()) {
                /* 如果是主治医生，所有可信度直接为1 */
                mt = 1;
                hbt = 1;
                trust = 1;
                rcm = 1;
                rep = 1;
            } else {
                mt = calDocMt(doctor);
                rcm = calDocRCM(doctor);
                rep = calDocREP(doctor);
                if(rcm<0||rcm>1||rep<0||rep>1){
                    System.out.println("ERROR RCM OR REP!医生id："+ doctor.getId()+"医生姓名："+doctor.getName());
                    continue;
                }
                hbt = rcm<rep?rcm:rep;     //HBT = min(RCM,REP)
                trust = mt*TRUSTU + hbt*(1-TRUSTU);
            }

            DoctorTrustResult doctorTrustResult = new DoctorTrustResult(doctor, mt, rcm, rep, hbt, trust);
            System.out.println(doctorTrustResult.toString());
        }
    }

    /*
    *给定一个医生，计算MT
     */
    public float calDocMt(Doctor doctor){
        float mt = 0;
        float[] wi = new float[4];
        /* 计算随机波动的权值wi */
        for (int i = 0; i < 3; i++) {
            wi[i] = getRandom((float) 0.25 - MTALPHA, (float) 0.25 + MTALPHA);
        }
        wi[3] = 1 - wi[0] - wi[1] - wi[2];

        //for(float f:wi)
        //    System.out.println(f);

        /* 根据职称、工龄、学位计算MT */
        mt += 1 * wi[0]; //科室已经匹配，科室的匹配值恒为1
        mt += 0.25 * (doctor.getTitle() & 0xFF) * wi[1];//计算职称的匹配值
        mt += 0.25 * (doctor.getWorkage() & 0xFF) * wi[2];//计算工作年龄的匹配值
        mt += (float) 1 / 2 * (doctor.getDegree() & 0xFF) * wi[3];//计算学历信息的匹配值
        return mt;
    }

    /*
    *给定一个医生，通过用户日志计算其RCM
    */
    public float calDocRCM(Doctor doctor){
        List<UserLog> userLogList = commentService.getAllUserLogsByDoctor(doctor.getId());
        if(userLogList.size()< HBTN){
            System.out.println("ERROR！！用户日志样本数量不足！！!医生id："+ doctor.getId()+"医生姓名："+doctor.getName());
            return -1;
        }

        List<Float> samplingAveValue = new ArrayList<Float>(); //用来存放每次抽样后计算出来的平均值
        for(int i=0; i<HBTM; i++){
            int sum = 0;
            List<Integer> list = sampling(userLogList.size(),HBTN); //进行抽样
            for(int j=0 ; j < HBTN; j++){
                sum += (userLogList.get(list.get(j)-1).getEvaluateValue())&0xFF;
            }
            samplingAveValue.add((float)sum/HBTN/100); //评价值为0-100，计算时以1为最大值进行量化
        }

        /* 已经获得所有抽样值，下面计算HBT */
        float deno = 0; float rcm = 0;
        /* 先计算公式中的分母 */
        for(float i : samplingAveValue){
            deno += 1-i;
        }
        for(float i : samplingAveValue){
            rcm += i* (1-i) / deno;
        }
        return rcm;
    }


    /*
    *给定一个医生，通过系统日志计算其REP
    */
    public float calDocREP(Doctor doctor){
        List<SysLog> sysLogList = commentService.getAllSysLogsByDoctor(doctor.getId());
        if(sysLogList.size()==0){
            System.out.println("ERROR！！系统日志样本数量不足！！!医生id："+ doctor.getId()+"医生姓名："+doctor.getName());
            return -1;
        }
        List<Integer> list = new ArrayList<>(sysLogList.size());
        for(SysLog sysLog : sysLogList){
            list.add(sysLog.getEvaluateValue()&0xFF);
        }
        return getMiddle(list)/100;
    }

    /*
    *给定一个医生，计算其HBT
     */
    /*public float calDocHbt(Doctor doctor){
        float rcm = calDocRCM(doctor);
        float rep = calDocREP(doctor);
        if(rcm<0||rcm>1||rep<0||rep>1){
            System.out.println("ERROR RCM OR REP");
            return -1;
        }
        return rcm<rep?rcm:rep;     //HBT = min(RCM,REP)
    }*/

}
