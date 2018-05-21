package xd.medicine.controller;

import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.AvaDoctor;
import xd.medicine.entity.dto.DutyLogForFront;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.entity.dto.PatientWithTrust;
import xd.medicine.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liubotao
 */

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/dutylog")
public class DutyLogController {
    @Autowired
    private ProDutyLogService proDutyLogService;
    @Autowired
    private PostDutyLogService postDutyLogService;
    @Autowired
    private ProDutyService proDutyService;
    @Autowired
    private PostDutyService postDutyService;
    @Autowired
    private PatientService patientService;


    @RequestMapping(value = "/pro",method = RequestMethod.GET)
    public FrontResult getProDutyLogsBySub(Integer subType , Integer subId){
        List<ProDutyLog> proDutyLogList ;
        try {
            proDutyLogList = proDutyLogService.getProDutyLogsBySub((byte)subType.intValue(),subId);
            if (proDutyLogList!=null&&proDutyLogList.size()>0){
                List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
                for(ProDutyLog proDutyLog : proDutyLogList){
                    ProDuty proDuty = proDutyService.getProDutyById(proDutyLog.getDutyId());
                    PatientWithTrust patient = patientService.getPatientById(proDutyLog.getObjId());
                    if(proDuty!=null&&patient!=null) {
                        String dutyContent = proDuty.getDutyContent();
                        String patientName = patient.getPatient().getName();
                        int state = proDutyLog.getState();
                        int type = proDutyLog.getState() > 2 ? 1 : 0;
                        if (state > 2) {
                            state = state - 3;
                        }
                        DutyLogForFront dutyLogForFront = new DutyLogForFront(proDutyLog.getObjId(), patientName,
                                dutyContent, type, 0, state);
                        dutyLogForFrontList.add(dutyLogForFront);
                    }
                }

                return new FrontResult(200,dutyLogForFrontList,null);
            }else {
                return new FrontResult(500,null,"该用户暂时还没有事前义务日志记录");
            }
        } catch (Exception e) {
            return new FrontResult(500,null,e.getMessage());
        }

    }



    @RequestMapping(value = "/post",method = RequestMethod.GET)
    public FrontResult getPostDutyLogsBySub(Integer subType , Integer subId){
        List<PostDutyLog> postDutyLogList ;
        try {
            postDutyLogList = postDutyLogService.getPostDutyLogsBySub((byte)subType.intValue(),subId);
            if (postDutyLogList!=null&&postDutyLogList.size()>0){
                List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
                for(PostDutyLog postDutyLog : postDutyLogList){
                    PostDuty postDuty = postDutyService.getPostDutyById(postDutyLog.getDutyId());
                    PatientWithTrust patientWithTrust = patientService.getPatientById(postDutyLog.getObjId());
                    if(postDuty!=null&&patientWithTrust!=null) {
                        String dutyContent = postDuty.getDutyContent();
                        String patientName = patientWithTrust.getPatient().getName();
                        DutyLogForFront dutyLogForFront = new DutyLogForFront(postDutyLog.getObjId(), patientName,
                                dutyContent, 0, postDutyLog.getFulfillTime(), postDutyLog.getState());
                        dutyLogForFrontList.add(dutyLogForFront);
                    }
                }

                return new FrontResult(200,dutyLogForFrontList,null);
            }else {
                return new FrontResult(500,null,"该用户暂时还没有事后义务日志记录");
            }
        } catch (Exception e) {
            return new FrontResult(500,null,e.getMessage());
        }

    }


    @RequestMapping(value = "/pro/page",method = RequestMethod.GET)
    public FrontResult getProDutyLogsByPage(Integer subType , Integer subId , Integer page , Integer rows){
        try {
            PageInfo<DutyLogForFront> dutyLogForFrontPageInfo = proDutyLogService.getProDutyLogsByPage((byte) subType.intValue(), subId, page, rows);
            if (dutyLogForFrontPageInfo != null) {
                return new FrontResult(200, dutyLogForFrontPageInfo, null);
            } else {
                return new FrontResult(500, null, "该用户暂时还没有事前义务日志记录");
            }
        }catch (Exception e) {
            return new FrontResult(500,null,e.getMessage());
        }

    }

    @RequestMapping(value = "/post/page",method = RequestMethod.GET)
    public FrontResult getPostDutyLogsByPage(Integer subType , Integer subId , Integer page , Integer rows){
        try {
            PageInfo<DutyLogForFront> dutyLogForFrontPageInfo = postDutyLogService.getPostDutyLogsByPage((byte) subType.intValue(), subId, page, rows);
            if (dutyLogForFrontPageInfo != null) {
                return new FrontResult(200, dutyLogForFrontPageInfo, null);
            } else {
                return new FrontResult(500, null, "该用户暂时还没有事后义务日志记录");
            }
        }catch (Exception e) {
            return new FrontResult(500,null,e.getMessage());
        }

    }



}
