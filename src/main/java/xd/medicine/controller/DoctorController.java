package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.service.DoctorService;

import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */
@RequestMapping("/doctor")
@RestController
public class DoctorController {

    private static final Logger LOGGER= LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public Doctor getDoctorById(@RequestParam int doctorId){
        return doctorService.getDoctorById(doctorId);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public PageInfo<Doctor> getDoctorByPage(@RequestParam Integer page,@RequestParam Integer rows){
        return doctorService.getDoctorsByPage(page,rows);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Integer addNewDoctor(@RequestParam String name,
                                @RequestParam byte position,
                                @RequestParam String officeLocation,
                                @RequestParam String officePhone,
                                @RequestParam byte department,
                                @RequestParam byte title,
                                @RequestParam byte workage,
                                @RequestParam byte degree,
                                @RequestParam boolean isFree){
        Doctor doctor=new Doctor();
        doctor.setName(name);
        doctor.setPosition(position);
        doctor.setOfficeLocation(officeLocation);
        doctor.setOfficePhone(officePhone);
        doctor.setDepartment(department);
        doctor.setDegree(degree);
        doctor.setTitle(title);
        doctor.setWorkage(workage);
        doctor.setIsFree(isFree);
        doctor.setRegistryDate(new Date());
        return doctorService.insertDoctor(doctor);
    }


}
