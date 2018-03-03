package xd.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.dto.DutyLogForFront;
import xd.medicine.entity.dto.FrontResult;
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


    @RequestMapping(value = "/pro",method = RequestMethod.GET)
    public FrontResult getProDutyLogsBySub(Integer subType , Integer subId){
        List<ProDutyLog> proDutyLogList = null;
        try {
            proDutyLogList = proDutyLogService.getProDutyLogsBySub((byte)subType.intValue(),subId);
            if (proDutyLogList!=null&&proDutyLogList.size()>0){
                List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
                for(ProDutyLog proDutyLog : proDutyLogList){
                    String dutyContent = proDutyService.getProDutyById(proDutyLog.getDutyId()).getDutyContent();
                    DutyLogForFront dutyLogForFront = new DutyLogForFront(proDutyLog.getObjId(),
                            dutyContent, 0, proDutyLog.getState());
                    dutyLogForFrontList.add(dutyLogForFront);
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
        List<PostDutyLog> postDutyLogList = null;
        try {
            postDutyLogList = postDutyLogService.getPostDutyLogsBySub((byte)subType.intValue(),subId);
            if (postDutyLogList!=null&&postDutyLogList.size()>0){
                List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
                for(PostDutyLog postDutyLog : postDutyLogList){
                    String dutyContent = postDutyService.getPostDutyById(postDutyLog.getDutyId()).getDutyContent();
                    DutyLogForFront dutyLogForFront = new DutyLogForFront(postDutyLog.getObjId(),
                            dutyContent, postDutyLog.getFulfillTime(), postDutyLog.getState());
                    dutyLogForFrontList.add(dutyLogForFront);
                }

                return new FrontResult(200,dutyLogForFrontList,null);
            }else {
                return new FrontResult(500,null,"该用户暂时还没有事后义务日志记录");
            }
        } catch (Exception e) {
            return new FrontResult(500,null,e.getMessage());
        }

    }



}