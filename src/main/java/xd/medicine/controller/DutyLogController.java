package xd.medicine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOGGER= LoggerFactory.getLogger(DutyLogController.class);

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
                    String dutyContent = proDutyService.getProDutyById(proDutyLog.getDutyId()).getDutyContent();
                    String patientName = patientService.getPatientById(proDutyLog.getObjId()).getPatient().getName();
                    int state = proDutyLog.getState();
                    int type = proDutyLog.getState()>2?1:0;
                    if(state>2){
                        state = state - 3;
                    }
                    DutyLogForFront dutyLogForFront = new DutyLogForFront(proDutyLog.getObjId(),patientName,
                            dutyContent,  type,0, state);
                    dutyLogForFrontList.add(dutyLogForFront);
                }

                LOGGER.info("返回日志数据： "+proDutyLogList.size()+"条");
                return new FrontResult(200,dutyLogForFrontList,null);
            }else {
                return new FrontResult(500,null,"该用户暂时还没有事前义务日志记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------------------------------------------------"+e.getMessage());
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
                    String dutyContent = postDutyService.getPostDutyById(postDutyLog.getDutyId()).getDutyContent();
                    String patientName = patientService.getPatientById(postDutyLog.getObjId()).getPatient().getName();
                    DutyLogForFront dutyLogForFront = new DutyLogForFront(postDutyLog.getObjId(),patientName,
                            dutyContent, 0, postDutyLog.getFulfillTime(), postDutyLog.getState());
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
