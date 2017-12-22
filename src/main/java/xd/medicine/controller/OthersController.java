package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xd.medicine.entity.bo.Others;
import xd.medicine.service.OthersService;

import java.util.List;

/**
 * created by xdCao on 2017/12/21
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/others")
public class OthersController {

    private static final Logger LOGGER= LoggerFactory.getLogger(OthersController.class);

    @Autowired
    private OthersService othersService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password){
        List<Others> othersByAccount = othersService.getOthersByAccount(account);
        if (othersByAccount!=null&&(othersByAccount.size()==1)){
            if (password.equals(othersByAccount.get(0).getPassword())){
                return new FrontResult(200,othersByAccount.get(0),null);
            }else {
                return new FrontResult(500,null,"密码错误");
            }
        }else {
            return new FrontResult(500,null,"用户名错误");
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public FrontResult addNewOthers(String account,
                                    String password,
                                    String name,
                                    Boolean gender,
                                    Integer age,
                                    String job,
                                    String company,
                                    String address,
                                    String phone,
                                    Boolean isSendRequest){
        Others others=new Others();
        others.setAccount(account);
        others.setPassword(password);
        others.setName(name);
        others.setPhone(phone);
        others.setJob(Byte.valueOf(job));
        others.setGender(gender);
        others.setCompany(company);
        others.setAddress(address);
        others.setAge(age);
        others.setIsSendRequest(isSendRequest);
        othersService.insert(others);
        return new FrontResult(200,others,null);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public FrontResult updateOthers(Integer id,
                                    String name,
                                    Boolean gender,
                                    Integer age,
                                    String job,
                                    String company,
                                    String address,
                                    String phone,
                                    Boolean isSendRequest){
        Others others=new Others();
        others.setId(id);
        others.setName(name);
        others.setPhone(phone);
        others.setJob(Byte.valueOf(job));
        others.setGender(gender);
        others.setCompany(company);
        others.setAddress(address);
        others.setAge(age);
        others.setIsSendRequest(isSendRequest);
        othersService.update(others);
        return new FrontResult(200,others,null);
    }

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public FrontResult getOthers(Integer id){
        Others othersById = othersService.getOthersById(id);
        if (othersById!=null){
            return new FrontResult(200,othersById,null);
        }else {
            return new FrontResult(500,null,"找不到该用户");
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public FrontResult getAllOthers(){
        List<Others> allOthers = othersService.getAllOthers();
        if (allOthers!=null&&allOthers.size()>0){
            return new FrontResult(200,allOthers,null);
        }else{
            return new FrontResult(500,null,"其他用户列表为空");
        }
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public FrontResult getOthersByPage(Integer page,Integer rows){
        PageInfo<Others> othersByPage = othersService.getOthersByPage(page, rows);
        if (othersByPage!=null){
            return new FrontResult(200,othersByPage,null);
        }else {
            return new FrontResult(500,null,"分页信息为空");
        }
    }
}
