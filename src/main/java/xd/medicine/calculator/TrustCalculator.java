package xd.medicine.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.UserLog;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.CommentService;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;

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
    输入一个病人id，输出匹配到的主体集及其匹配可信度，暂时仅打印出来
     */
    public void calMts( int patientId ) {
        float mt;
        PatientWithTrust patient = patientService.getPatientById(patientId);
        List<Doctor> doctorList = patientService.getSisDoctorsByPatientId(patientId);  //获得满足科室要求的所有医生，即候选主体集合SIS
        for (Doctor doctor : doctorList) {
            System.out.print(doctor.getId() + "---");
            System.out.print(doctor.getName() + "---");
            if (patient.getPatient().getDoctorId() == doctor.getId()) {
                /* 如果是主治医生，匹配可信度直接为1 */
                mt = 1;
            } else {
                mt = calDocMt(doctor);
            }
            System.out.print("MT:" + mt + "--");
            System.out.println("free?:" + (doctor.getIsFree() ? "yes" : "no"));
        }
    }

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

    public void calDocHbt(Doctor doctor){
        List<UserLog> userLogList = commentService.getAllUserLogsByDoctor(doctor.getId());
        if(userLogList.size()< HBTN){
            System.out.println("error!");
        }
        for(int i=0; i<HBTM; i++){

        }


    }
}
