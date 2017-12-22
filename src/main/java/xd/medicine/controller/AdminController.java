package xd.medicine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.dto.FrontResult;

/**
 * created by xdCao on 2017/12/22
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public FrontResult login(String account, String password){

        return new FrontResult(200,null,null);
    }


}
