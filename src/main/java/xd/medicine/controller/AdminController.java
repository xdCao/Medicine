package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xd.medicine.calculator.Constants;
import xd.medicine.entity.bo.AdminUser;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.AdminService;
import xd.medicine.service.ProDutyLogService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProDutyLogService proDutyLogService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password){
        try {
            List<AdminUser> user = adminService.getAdminUserByAccount(account);
            if (user!=null&&user.size()>0){
                if (user.get(0).getPassword().equals(password)){
                    return new FrontResult(200,user.get(0),null);
                }else{
                    return new FrontResult(500,null,"密码错误");
                }
            }else {
                return new FrontResult(500,null,"用户名错误");
            }
        }catch (Exception e){
            return new FrontResult(500,null,"登陆失败");
        }
    }

    @RequestMapping(value = "/paras/check",method = RequestMethod.GET)
    public FrontResult getParas(){
        List<Float> list = new ArrayList<>();
        list.add(Constants.getrThs());
        list.add(Constants.getBeta1());
        list.add(Constants.getBeta2());
        return new FrontResult(200,list,null);
    }


    @RequestMapping(value = "/paras/update",method = RequestMethod.POST)
    public FrontResult updateParas(float rThs, float beta1, float beta2){
        Constants.setrThs(rThs);
        Constants.setBeta1(beta1);
        Constants.setBeta2(beta2);
        List<Float> list = new ArrayList<>();
        list.add(rThs);
        list.add(beta1);
        list.add(beta2);
        return new FrontResult(200,list,null);
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void insert(){
        for (int i = 0; i < 10000; i++) {
            ProDutyLog proDutyLog=new ProDutyLog();
            proDutyLog.setDutyId(1);
            proDutyLog.setObjId(1);
            proDutyLog.setState((byte) 1);
            proDutyLog.setSubId(1);
            proDutyLog.setSubType((byte) 1);
            proDutyLogService.insertNewProDutyLog(proDutyLog);
        }
    }

}
