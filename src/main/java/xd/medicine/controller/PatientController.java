package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.Patient;
import xd.medicine.service.PatientService;

import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    private static final Logger LOGGER= LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public Patient getSinglePatient(@RequestParam int patientId){
        return patientService.getPatientById(patientId);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public PageInfo<Patient> getPatientsByPage(@RequestParam int page,@RequestParam int rows){
        return patientService.getPatientByPage(page, rows);
    }

    @RequestMapping(value = "/doctor",method = RequestMethod.GET)
    public List<Patient> getPatientByDoctor(@RequestParam int doctorId){
        return patientService.getPatientsByDoctor(doctorId);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Integer deletePatient(@RequestParam int patientId){
        return patientService.deletePatient(patientId);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Integer updatePatient(
                                 @RequestParam int id,
                                 @RequestParam int doctorId,
                                 @RequestParam String phone,
                                 @RequestParam int trustAttrId,
                                 @RequestParam boolean senseAware,
                                 @RequestParam String illnessCondition){
        Patient patient=new Patient();
        //todo
    }

}
