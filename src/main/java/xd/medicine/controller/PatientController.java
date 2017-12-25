package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.PatientService;
import xd.medicine.service.TrustAttrService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 * 目前完成的接口：
 * 1.计算病人总数
 * 2.病人登录
 * 3.根据id查询单个病人
 * 4.查询所有病人
 * 5.分页查询病人
 * 6.查询某个医生的所有病患
 * 7.删除病人
 * 8.更新病人基本信息
 * 9.更新病人信任信息
 * 10.更新病人紧急信息
 * 11.注册/添加病人
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

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public FrontResult count(){
        return new FrontResult(200,patientService.count(),null);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password, HttpServletRequest request, HttpServletResponse response){
        List<PatientWithTrust> patientByAccount = patientService.getPatientByAccount(account);
        if (patientByAccount!=null&&(patientByAccount.size()==1)){
            if (password.equals(patientByAccount.get(0).getPatient().getPassword())){
                request.getSession().setAttribute("currentUser",patientByAccount.get(0));
                response.addCookie(new Cookie("id",patientByAccount.get(0).getPatient().getId().toString()));
                return new FrontResult(200,patientByAccount.get(0),null);
            }else {
                return new FrontResult(500,0,"密码错误");
            }
        }else {
            return new FrontResult(500,null,"用户名错误");
        }
    }


    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public FrontResult getSinglePatient(@RequestParam int patientId){
        PatientWithTrust patient = patientService.getPatientById(patientId);
        if (patient!=null){
            return new FrontResult(200,patient,null);
        }else {
            return new FrontResult(500,null,"找不到该患者");
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public FrontResult getAllPatients(){
        List<PatientWithTrust> allPatients = patientService.getAllPatients();
        if (allPatients!=null&&allPatients.size()>0){
            return new FrontResult(200,allPatients,null);
        }else {
            return new FrontResult(500,null,"患者列表为空");
        }
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public FrontResult getPatientsByPage(@RequestParam int page,@RequestParam int rows){
        PageInfo<PatientWithTrust> patientByPage = patientService.getPatientByPage(page, rows);
        return new FrontResult(200,patientByPage,null);
    }

    @RequestMapping(value = "/doctor",method = RequestMethod.GET)
    public FrontResult getPatientByDoctor(@RequestParam int doctorId){
        List<PatientWithTrust> patientsByDoctor = patientService.getPatientsByDoctor(doctorId);
        if (patientsByDoctor!=null&&patientsByDoctor.size()>0){
            return new FrontResult(200,patientsByDoctor,null);
        }else {
            return new FrontResult(500,null,"该医生没有病患");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public FrontResult deletePatient(@RequestParam int patientId){
        PatientWithTrust patientById = patientService.getPatientById(patientId);
        trustAttrService.deleteById(patientById.getPatient().getTrustattrId());
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
        return new FrontResult(200,patient,null);
    }

    @RequestMapping(value = "/updateEmerg",method = RequestMethod.POST)
    public FrontResult updateEmergency(Integer patientId,
                                       Double temperature,
                                       Integer heartBeat,
                                       Double bloodPressure){
        Patient patient = patientService.updateEmergency(patientId, temperature, heartBeat, bloodPressure);
        return new FrontResult(200,patient,null);
    }

    @RequestMapping(value = "/updateTrust",method = RequestMethod.POST)
    public FrontResult updateTrustAttr(Integer trustId,
                                       String demandDegree,
                                       String demandTitle,
                                       String department,
                                       String demandWorkage){
        TrustAttr trustAttr=new TrustAttr();
        trustAttr.setId(trustId);
        trustAttr.setDemandWorkage(Byte.valueOf(demandWorkage));
        trustAttr.setDemandTitle(Byte.valueOf(demandTitle));
        trustAttr.setDemandDegree(Byte.valueOf(demandDegree));
        trustAttr.setDepartment(Byte.valueOf(department));
        trustAttrService.updateTrustAttr(trustAttr);
        return new FrontResult(200,trustAttr,null);

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
                                 @RequestParam String department,
                                 @RequestParam String demandTitle,
                                 @RequestParam String demandWorkage,
                                 @RequestParam String demandDegree){

        Integer countPatientByAccount = patientService.countPatientByAccount(account);
        if (countPatientByAccount>0){
            return new FrontResult(500,null,"该账户名已存在");
        }

        /*
        病人注册时将其信任信息与个人信息分两部分插入
        因此在注册时先提交信任信息并将trustAttrId返回给前端，再作为参数进行病人注册
         */
        TrustAttr trustAttr=new TrustAttr();
        trustAttr.setDepartment(Byte.valueOf(department));
        trustAttr.setDemandTitle(Byte.valueOf(demandTitle));
        trustAttr.setDemandWorkage(Byte.valueOf(demandWorkage));
        trustAttr.setDemandDegree(Byte.valueOf(demandDegree));
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
        return new FrontResult(200,new PatientWithTrust(patient,trustAttr),null);
    }




}
