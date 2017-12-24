package xd.medicine.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;

import java.util.List;

//import static xd.medicine.calculator.Constants.*;

/**
 * created by liubotao
 */

@Component
public class TrustCalculator {

    @Autowired
    private PatientService patientService;


    /*
    计算匹配可信度MT
    输入一个病人id，输出匹配到的主体集，暂时仅打印出来
     */
    public void calMt( int patientId ){
        float mt ;
        Patient patient = patientService.getPatientById(patientId);
        List<Doctor> doctorList = patientService.getSisDoctorsByPatientId(patientId);  //获得满足科室要求的所有医生，即候选主体集合SIS
        for(Doctor doctor : doctorList) {
            mt=0;
            System.out.print(doctor.getId()+"---");
            System.out.print(doctor.getName()+"---");
            if(patient.getDoctorId()==doctor.getId()){
                /* 如果是主治医生，匹配可信度直接为1 */
                mt=1;
            }else {
                /* 根据职称、工龄、学位计算MT，权值暂时设为定值 */

                mt += 1 ; //科室已经匹配，科室的匹配值恒为1
                mt += 0.25 * (doctor.getTitle() & 0xFF);//计算职称的匹配值
                mt += 0.25 * (doctor.getWorkage() & 0xFF);//计算工作年龄的匹配值
                mt += (float)1/2 * (doctor.getDegree() & 0xFF);//计算学历信息的匹配值
                mt/=4;
            }
            System.out.println("MT:"+mt);
        }
    }
}
