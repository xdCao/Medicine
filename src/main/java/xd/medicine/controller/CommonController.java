package xd.medicine.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * created by xdCao on 2017/12/21
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class CommonController {

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public FrontResult logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("currentUser");
        response.addCookie(new Cookie("id",null));
        return new FrontResult(200,null,null);
    }

}
