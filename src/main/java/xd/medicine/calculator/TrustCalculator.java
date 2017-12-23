package xd.medicine.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PatientService;
import xd.medicine.service.TrustAttrService;

import java.util.List;

import static xd.medicine.calculator.Constants.*;

/**
 * created by liubotao
 */

@Component
public class TrustCalculator {

    @Autowired
    private TrustAttrService trustAttrService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    /*
    计算匹配可信度MT
    输入一个病人id，输出匹配到的主体集，暂时仅打印出来
     */
    public void calMt( int patientId ){
        float mt ;
        Patient patient = patientService.getPatientById(patientId);
        TrustAttr trustAttr = trustAttrService.getTrustAttrById(patient.getTrustattrId());
        List<Doctor> doctorList = doctorService.getDoctorByDepartment(trustAttr.getDepartment());  //获得满足科室要求的所有医生，即候选主体集合SIS
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
            System.out.println("mt:"+mt);

        }


    }

    public int myTest(int id){
        if(trustAttrService == null){
            return -187;
        }
        TrustAttr trustAttr = trustAttrService.getTrustAttrById(id);
        return  trustAttr.getDemandTitle();
    }




}
