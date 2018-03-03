package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PostDutyLogMapper;
import xd.medicine.dao.autoMapper.ProDutyLogMapper;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.PostDutyLogExample;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.bo.ProDutyLogExample;
import xd.medicine.service.ProDutyLogService;

import java.util.List;

/**
 * created by liubotao
 */
@Service
public class ProDutyLogServiceImpl implements ProDutyLogService {
    @Autowired
    private ProDutyLogMapper proDutyLogMapper;


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
}
