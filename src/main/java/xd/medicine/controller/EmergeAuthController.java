package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.dto.AuthRequest;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.AuthService;
import xd.medicine.service.DoctorService;
import xd.medicine.service.OthersService;
import xd.medicine.service.PatientService;

import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */
@RestController
@RequestMapping(value = "/auth/emerg/")
public class EmergeAuthController {

    public static final int DOCTOR=1;
    public static final int OTHERS=0;

    private MapCache cache=MapCache.getInstance();

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OthersService othersService;

    @Autowired
    private AuthService authService;

    //这里是获取紧急情况下的病人列表
    @RequestMapping(value = "/sense",method = RequestMethod.GET)
    public FrontResult sense(){
        List<PatientWithTrust> patients = null;
        try {
            patients = patientService.sensePatientInEmergency();
            if (patients!=null&&patients.size()>0){
                return new FrontResult(200,patients,null);
            }else {
                return new FrontResult(500,null,"暂无处于紧急状态的病人");
            }
        } catch (Exception e) {
            return new FrontResult(500,null,"出错");
        }

    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public FrontResult getBroadCast(Integer doctodId){
        try {
            List<PatientWithTrust> list = authService.judgeBroadcast(doctodId);
            return new FrontResult(200,list,null);
        } catch (Exception e) {
            return new FrontResult(500,null,"暂未收到紧急通知");
        }
    }


    /*得用websocket*/
    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public FrontResult access(  Integer userType,
                                Integer userId,
                                Integer patientId){
        PatientWithTrust patient = patientService.getPatientById(patientId);
        Date emergtime = patient.getPatient().getEmergtime();
        if (System.currentTimeMillis()<emergtime.getTime()){
            /*此时请求未超时，应该进入缓存*/
            AuthRequest authRequest=new AuthRequest();
        }
        /*
         * 更改医生的数据库表，增加emergStart字段，记录病人进入紧急情况的时间，在主体调用access接口时比对时间，
         * 如果未超时，进入缓存，等到时限将缓存中的请求取出做计算
         */
        if (userType.equals(DOCTOR)){
            //医生的请求处理逻辑，分为可信主体集中和非可信主体集

        }else if (userType.equals(OTHERS)){
            //其他用户的请求处理逻辑
        }else {
            return new FrontResult(500,null,"不合法的用户类型");
        }
        return null;
    }


}
