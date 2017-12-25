package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.AdminUser;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.AdminService;

import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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

}
