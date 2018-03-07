package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PostDutyLogMapper;
import xd.medicine.dao.autoMapper.ProDutyLogMapper;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.PostDutyLogExample;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.bo.ProDutyLogExample;
import xd.medicine.entity.dto.AvaDoctor;
import xd.medicine.entity.dto.DutyLogForFront;
import xd.medicine.entity.dto.FrontResult;
import xd.medicine.service.PatientService;
import xd.medicine.service.ProDutyLogService;
import xd.medicine.service.ProDutyService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by liubotao
 */
@Service
public class ProDutyLogServiceImpl implements ProDutyLogService {
    @Autowired
    private ProDutyLogMapper proDutyLogMapper;
    @Autowired
    private ProDutyService proDutyService;
    @Autowired
    private PatientService patientService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertNewProDutyLog(ProDutyLog proDutyLog) {
        proDutyLogMapper.insert(proDutyLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProDutyLog(int proDutyLogId) {
        proDutyLogMapper.deleteByPrimaryKey(proDutyLogId);
    }

    @Override
    public List<ProDutyLog> getProDutyLogsBySub(Byte subType, int subId) {
        ProDutyLogExample proDutyLogExample = new ProDutyLogExample();
        proDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        return proDutyLogMapper.selectByExample(proDutyLogExample);
    }

    @Override
    public PageInfo<DutyLogForFront> getProDutyLogsByPage(Byte subType, int subId, int page, int rows) {
        PageHelper.startPage(page,rows);
        ProDutyLogExample proDutyLogExample = new ProDutyLogExample();
        proDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        List<ProDutyLog> proDutyLogList = proDutyLogMapper.selectByExample(proDutyLogExample);


        if (proDutyLogList!=null){
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
            PageInfo<DutyLogForFront> dutyLogForFrontPageInfo =new PageInfo<>(dutyLogForFrontList);
            return dutyLogForFrontPageInfo;
        }else{
            return null;
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProDutyLogByPatient(int patientId) {
        ProDutyLogExample proDutyLogExample = new ProDutyLogExample();
        proDutyLogExample.createCriteria().andObjIdEqualTo(patientId);
        proDutyLogMapper.deleteByExample(proDutyLogExample);
    }
}
