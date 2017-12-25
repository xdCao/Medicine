package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.cache.MapCache;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.AuthService;
import xd.medicine.service.PatientService;

import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */
@RestController
@RequestMapping(value = "/auth/emerg/")
public class EmergeAuthController {

    private MapCache cache=MapCache.getInstance();

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthService authService;

    //这里是获取紧急情况下的病人列表
    @RequestMapping(value = "/sense",method = RequestMethod.GET)
    public FrontResult sense(){
        List<PatientWithTrust> patients = patientService.sensePatientInEmergency();
        if (patients!=null&&patients.size()>0){
            return new FrontResult(200,patients,null);
        }else {
            return new FrontResult(500,null,"暂无处于紧急状态的病人");
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

    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public FrontResult access(  Integer userType,
                                Integer userId,
                                Integer patientId){
        //todo 缓存请求
        return null;
    }


}
