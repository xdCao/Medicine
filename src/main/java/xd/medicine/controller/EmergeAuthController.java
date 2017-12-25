package xd.medicine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by xdCao on 2017/12/25
 */
@RestController
@RequestMapping(value = "/auth/emerg/")
public class EmergeAuthController {

    @RequestMapping(value = "/sense",method = RequestMethod.GET)
    public void sense(){

    }


}
