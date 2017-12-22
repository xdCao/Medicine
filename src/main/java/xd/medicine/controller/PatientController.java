package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.PatientService;
import xd.medicine.service.TrustAttrService;

import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {

    private static final Logger LOGGER= LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private TrustAttrService trustAttrService;

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public FrontResult getSinglePatient(@RequestParam int patientId){
        Patient patient = patientService.getPatientById(patientId);
        if (patient!=null){
            return new FrontResult(200,patient,null);
        }else {
            return new FrontResult(500,null,"找不到该患者");
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public FrontResult getAllPatients(){
        List<Patient> allPatients = patientService.getAllPatients();
        if (allPatients!=null&&allPatients.size()>0){
            return new FrontResult(200,allPatients,null);
        }else {
            return new FrontResult(500,null,"患者列表为空");
        }
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public FrontResult getPatientsByPage(@RequestParam int page,@RequestParam int rows){
        PageInfo<Patient> patientByPage = patientService.getPatientByPage(page, rows);
        return new FrontResult(200,patientByPage,null);
    }

    @RequestMapping(value = "/doctor",method = RequestMethod.GET)
    public FrontResult getPatientByDoctor(@RequestParam int doctorId){
        List<Patient> patientsByDoctor = patientService.getPatientsByDoctor(doctorId);
        if (patientsByDoctor!=null&&patientsByDoctor.size()>0){
            return new FrontResult(200,patientsByDoctor,null);
        }else {
            return new FrontResult(500,null,"该医生没有病患");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public FrontResult deletePatient(@RequestParam int patientId){
        Patient patientById = patientService.getPatientById(patientId);
        trustAttrService.deleteById(patientById.getTrustattrId());
        patientService.deletePatient(patientId);
        return new FrontResult(200,null,null);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public FrontResult updatePatient(
                                 @RequestParam Integer id,
                                 @RequestParam Integer doctorId,
                                 @RequestParam String phone,
                                 @RequestParam boolean senseAware,
                                 @RequestParam String illnessCondition){
        Patient patient=new Patient();
        patient.setId(id);
        patient.setDoctorId(doctorId);
        patient.setPhone(phone);
        patient.setSenseAware(senseAware);
        patient.setIllnessCondition(illnessCondition);
        Integer integer = patientService.updatePatient(patient);
        return new FrontResult(200,integer,null);
    }

    @RequestMapping(value = "/updateEmerg",method = RequestMethod.POST)
    public FrontResult updateEmergency(Integer patientId,
                                       Double temperature,
                                       Integer heartBeat,
                                       Double bloodPressure){
        patientService.updateEmergency(patientId,temperature,heartBeat,bloodPressure);
        return new FrontResult(200,null,null);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public FrontResult addNewPatient(@RequestParam String name,
                                 @RequestParam String account,
                                 @RequestParam String password,
                                 @RequestParam Integer doctorId,
                                 @RequestParam String phone,
                                 @RequestParam boolean senseAware,
                                 @RequestParam String illnessCondition,
                                 @RequestParam byte department,
                                 @RequestParam byte demandTitle,
                                 @RequestParam byte demandWorkage,
                                 @RequestParam byte demandDegree){

        Integer countPatientByAccount = patientService.countPatientByAccount(account);
        if (countPatientByAccount>0){
            return new FrontResult(500,null,"该账户名已存在");
        }

        /*
        病人注册时将其信任信息与个人信息分两部分插入
        因此在注册时先提交信任信息并将trustAttrId返回给前端，再作为参数进行病人注册
         */
        TrustAttr trustAttr=new TrustAttr();
        trustAttr.setDepartment(department);
        trustAttr.setDemandTitle(demandTitle);
        trustAttr.setDemandWorkage(demandWorkage);
        trustAttr.setDemandDegree(demandDegree);
        trustAttrService.insertTrustAttr(trustAttr);


        Patient patient=new Patient();
        patient.setName(name);
        patient.setAccount(account);
        patient.setPassword(password);
        patient.setTrustattrId(trustAttr.getId());
        patient.setDoctorId(doctorId);
        patient.setPhone(phone);
        patient.setSenseAware(senseAware);
        patient.setIllnessCondition(illnessCondition);
        patient.setRegistryDate(new Date());
        patient.setTemperature(0.0);
        patient.setBloodPressure(0.0);
        patient.setHeartBeat(0);
        patient.setIsInEmergency(false);
        patientService.insertPatient(patient);
        return new FrontResult(200,patient,null);
    }



}
