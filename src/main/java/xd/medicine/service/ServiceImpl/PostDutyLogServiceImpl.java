package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PostDutyLogMapper;
import xd.medicine.entity.bo.*;
import xd.medicine.entity.dto.DutyLogForFront;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.PatientService;
import xd.medicine.service.PostDutyLogService;
import xd.medicine.service.PostDutyService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liubotao
 */

@Service
public class PostDutyLogServiceImpl implements PostDutyLogService{
    @Autowired
    private PostDutyLogMapper postDutyLogMapper;
    @Autowired
    private PostDutyService postDutyService;
    @Autowired
    private PatientService patientService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertNewPostDutyLog(PostDutyLog postDutyLog) {
        postDutyLogMapper.insert(postDutyLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePostDutyLog(int postDutyLogId) {
        postDutyLogMapper.deleteByPrimaryKey(postDutyLogId);
    }

    //重要方法，返回值为论文中的q,表示用户在历史访问中被分配的后验义务的总个数
    @Override
    public Integer countPostDutyLogsBySub(Byte subType, int subId) {
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        return postDutyLogMapper.countByExample(postDutyLogExample);
    }


    //重要方法，返回值为论文中的p，表示用户在历史访问中后验义务的有效期内实际完成义务的总个数
    @Override
    public Integer countFulfilledPostDutyLogsBySub(Byte subType, int subId) {
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId).andStateEqualTo((byte)2);
        return postDutyLogMapper.countByExample(postDutyLogExample);
    }

    @Override
    public List<PostDutyLog> getPostDutyLogsBySub(Byte subType, int subId) {
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        return postDutyLogMapper.selectByExample(postDutyLogExample);
    }

    @Override
    public PageInfo<DutyLogForFront> getPostDutyLogsByPage(Byte subType, int subId, int page, int rows) {
        PageHelper.startPage(page,rows);
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        List<PostDutyLog> postDutyLogList = postDutyLogMapper.selectByExample(postDutyLogExample);
        if (postDutyLogList!=null&&postDutyLogList.size()>0){
            List<DutyLogForFront> dutyLogForFrontList = new ArrayList<>();
            for(PostDutyLog postDutyLog : postDutyLogList){
                String dutyContent = postDutyService.getPostDutyById(postDutyLog.getDutyId()).getDutyContent();
                String patientName = patientService.getPatientById(postDutyLog.getObjId()).getPatient().getName();
                DutyLogForFront dutyLogForFront = new DutyLogForFront(postDutyLog.getObjId(),patientName,
                        dutyContent, 0, postDutyLog.getFulfillTime(), postDutyLog.getState());
                dutyLogForFrontList.add(dutyLogForFront);
            }
            PageInfo<DutyLogForFront> dutyLogForFrontPageInfo = new PageInfo<>(dutyLogForFrontList);

            return dutyLogForFrontPageInfo;
        }else {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePostDutyLogByPatient(int patientId) {
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andObjIdEqualTo(patientId);
        postDutyLogMapper.deleteByExample(postDutyLogExample);
    }
}
