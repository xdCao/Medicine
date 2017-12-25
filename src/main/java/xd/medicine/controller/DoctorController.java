package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.dto.AvaDoctor;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.DoctorService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 * 目前完成接口：
 * 1.查询医生总数
 * 2.医生登录
 * 3.注册/添加医生
 * 4.根据Id查询医生
 * 5.查询所有医生
 * 6.分页查询医生
 * 7.更新医生信息
 * 8.删除医生
 * 9.查询对应科室的医生以生成可行主体集
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/doctor")
@RestController
public class DoctorController {

    private static final Logger LOGGER= LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public FrontResult count(){
        return new FrontResult(200,doctorService.count(),null);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password, HttpServletRequest request, HttpServletResponse response){
        List<Doctor> doctorByAccount = doctorService.getDoctorByAccount(account);
        if (doctorByAccount!=null&&(doctorByAccount.size()==1)){
            if (password.equals(doctorByAccount.get(0).getPassword())){
                request.getSession().setAttribute("currentUser",doctorByAccount.get(0));
                response.addCookie(new Cookie("id",doctorByAccount.get(0).getId().toString()));
                return new FrontResult(200,doctorByAccount.get(0),null);
            }else {
                return new FrontResult(500,0,"密码错误");
            }
        }else {
            return new FrontResult(500,null,"用户名错误");
        }
    }

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public FrontResult getDoctorById(Integer doctorId){
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor!=null){
            AvaDoctor avaDoctor=new AvaDoctor(doctor,doctor.getIsFree()&&doctor.getIsin());
            return new FrontResult(200,avaDoctor,null);
        }else {
            return new FrontResult(500,null,"没有该医生");
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public FrontResult getAllDoctors(){
        List<Doctor> allDoctors = doctorService.getAllDoctors();

        if (allDoctors!=null&&allDoctors.size()>0){
            List<AvaDoctor> avaDoctors=new ArrayList<>();
            for (Doctor doctor:allDoctors){
                AvaDoctor avaDoctor=new AvaDoctor(doctor,doctor.getIsFree()&&doctor.getIsin());
                avaDoctors.add(avaDoctor);
            }
            return new FrontResult(200,avaDoctors,null);
        }else {
            return new FrontResult(500,null,"医生列表为空");
        }
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public FrontResult getDoctorByPage(Integer page,Integer rows){
        PageInfo<AvaDoctor> doctorsByPage = doctorService.getDoctorsByPage(page, rows);
        return new FrontResult(200,doctorsByPage,null);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public FrontResult addNewDoctor(String name,
                                    String account,
                                    String password,
                                    String position,
                                    String officeLocation,
                                    String officePhone,
                                    String department,
                                    String title,
                                    String workage,
                                    String degree,
                                    String isFree){

        Integer countDoctorsByAccount = doctorService.countDoctorsByAccount(account);
        if (countDoctorsByAccount>0){
            return new FrontResult(500,null,"该账户名已存在");
        }
        Doctor doctor=new Doctor();
        doctor.setName(name);
        doctor.setAccount(account);
        doctor.setPassword(password);
        doctor.setPosition(Byte.valueOf(position));
        doctor.setOfficeLocation(officeLocation);
        doctor.setOfficePhone(officePhone);
        doctor.setDepartment(Byte.valueOf(department));
        doctor.setDegree(Byte.valueOf(degree));
        doctor.setTitle(Byte.valueOf(title));
        doctor.setWorkage(Byte.valueOf(workage));
        doctor.setIsFree(Boolean.valueOf(isFree));
        doctor.setRegistryDate(new Date());
        doctor.setIsin(false);
        doctorService.insertDoctor(doctor);
        return new FrontResult(200,doctor,null);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public FrontResult updateDoctor(Integer id,
                                    String registryDate,
                                    String name,
                                    String account,
                                    String password,
                                    String position,
                                    String officeLocation,
                                    String officePhone,
                                    String department,
                                    String title,
                                    String workage,
                                    String degree,
                                    String isFree,
                                    String isIn){
        Doctor doctor=new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setAccount(account);
        doctor.setPassword(password);
        doctor.setPosition(Byte.valueOf(position));
        doctor.setOfficeLocation(officeLocation);
        doctor.setOfficePhone(officePhone);
        doctor.setDepartment(Byte.valueOf(department));
        doctor.setDegree(Byte.valueOf(degree));
        doctor.setTitle(Byte.valueOf(title));
        doctor.setWorkage(Byte.valueOf(workage));
        doctor.setIsFree(Boolean.valueOf(isFree));
        doctor.setIsin(Boolean.valueOf(isIn));
        doctorService.updateDoctor(doctor);
        return new FrontResult(200,doctor,null);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public FrontResult deleteDoctor(Integer id){
        doctorService.deleteDoctorById(id);
        return new FrontResult(200,null,null);
    }

    @RequestMapping(value = "/department",method = RequestMethod.GET)
    public FrontResult getDoctorByDepartment(String department){

        List<Doctor> doctorByDepartment = doctorService.getDoctorByDepartment(Byte.valueOf(department));
        if (doctorByDepartment!=null&&doctorByDepartment.size()>0){
            return new FrontResult(200,doctorByDepartment,null);
        }else {
            return new FrontResult(500,null,"该科室没有医生");
        }
    }


}
