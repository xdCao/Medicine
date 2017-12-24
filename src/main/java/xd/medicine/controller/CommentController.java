package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.UserLog;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.CommentService;

import java.util.Date;
import java.util.List;

/**
 * created by xdCao on 2017/12/24
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public FrontResult commentDoctor(Integer doctorId,
                                     String doctorName,
                                     String evaluateValue,
                                     String evaluateContent){
        UserLog userLog=new UserLog();
        userLog.setDoctorId(doctorId);
        userLog.setDoctorName(doctorName);
        userLog.setEvaluateValue(Byte.valueOf(evaluateValue));
        userLog.setEvaluateContent(evaluateContent);
        userLog.setEvaluateDate(new Date());
        try {
            commentService.insertNewUserLog(userLog);
            return new FrontResult(200,userLog,null);
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }
    }


    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    public FrontResult deleteUserCommentById(Integer commentId){
        try {
            commentService.deleteUserLog(commentId);
            return new FrontResult(200,null,null);
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }

    }


    @RequestMapping(value = "/user/doctor",method = RequestMethod.GET)
    public FrontResult getAllUserLogsByDoctor(Integer doctorId){
        List<UserLog> userLogs = commentService.getAllUserLogsByDoctor(doctorId);
        if (userLogs!=null&&userLogs.size()>0){
            return new FrontResult(200,userLogs,null);
        }else {
            return new FrontResult(500,null,"该医生暂时还没有评价");
        }
    }


    @RequestMapping(value = "/sys/add",method = RequestMethod.POST)
    public FrontResult addNewSysLog(Integer patientId,
                                    Integer doctorId,
                                    String doctorName,
                                    String evaluateValue,
                                    String operationContent
                                    ){
        SysLog sysLog=new SysLog();
        sysLog.setPatientId(patientId);
        sysLog.setDoctorId(doctorId);
        sysLog.setDoctorName(doctorName);
        sysLog.setEvaluateValue(Byte.valueOf(evaluateValue));
        sysLog.setOperationContent(operationContent);
        sysLog.setEvaluateDate(new Date());
        try {
            commentService.insertNewSysLog(sysLog);
            return new FrontResult(200,sysLog,null);
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }
    }


    @RequestMapping(value = "/sys/delete",method = RequestMethod.POST)
    public FrontResult deleteSysLog(Integer commentId){
        try {
            commentService.deleteSysLog(commentId);
            return new FrontResult(200,null,null);
        }catch (Exception e){
            return new FrontResult(500,null,e.getMessage());
        }
    }


    @RequestMapping(value = "/sys/doctor",method = RequestMethod.GET)
    public FrontResult getSysLogWithDoctor(Integer doctorId){
        List<SysLog> allSysLogsByDoctor = commentService.getAllSysLogsByDoctor(doctorId);
        if (allSysLogsByDoctor!=null&&allSysLogsByDoctor.size()>0){
            return new FrontResult(200,allSysLogsByDoctor,null);
        }else {
            return new FrontResult(500,null,"该医生暂时还没有评价");
        }
    }


}
