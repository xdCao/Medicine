package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.dto.AvaDoctor;
import xd.medicine.entity.dto.DutyLogForFront;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.PostDutyService;
import xd.medicine.service.ProDutyService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liubotao
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/duty")
public class DutyController {

    @Autowired
    private ProDutyService proDutyService;
    @Autowired
    private PostDutyService postDutyService;

    @RequestMapping(value = "/pro",method = RequestMethod.GET)
    public FrontResult getAllProDuties(){
        try {
            List<ProDuty> allProDuties = proDutyService.getAllProDuties();
            if (allProDuties != null && allProDuties.size() > 0) {
                return new FrontResult(200, allProDuties, null);
            } else {
                return new FrontResult(500, null, "事前义务列表为空");
            }
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }
    }


    @RequestMapping(value = "/post",method = RequestMethod.GET)
    public FrontResult getAllPostDuties(){
        try {
            List<PostDuty> allPostDuties = postDutyService.getAllPostDuties();
            if (allPostDuties != null && allPostDuties.size() > 0) {
                return new FrontResult(200, allPostDuties, null);
            } else {
                return new FrontResult(500, null, "事前义务列表为空");
            }
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }
    }




}
