package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.ProDutyMapper;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.bo.ProDutyExample;
import xd.medicine.service.ProDutyService;

import java.util.List;

/**
 * created by liubotao
 */
@Service
public class ProDutyServiceImpl implements ProDutyService {
    private static final Logger LOGGER= LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private ProDutyMapper proDutyMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertProDuty(ProDuty proDuty) {
        return proDutyMapper.insert(proDuty);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteProDuty(int id) {
        return proDutyMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateProDuty(ProDuty proDuty) {
        return proDutyMapper.updateByPrimaryKeySelective(proDuty);
    }

    @Override
    public List<ProDuty> getAllProDuties() {
        return proDutyMapper.selectByExample(new ProDutyExample());
    }

    @Override
    public ProDuty getProDutyById(int id) {
        return proDutyMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo<ProDuty> getProDutiesByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<ProDuty> proDuties = proDutyMapper.selectByExample(new ProDutyExample());
        PageInfo<ProDuty> proDutyPageInfo=new PageInfo<>(proDuties);
        return proDutyPageInfo;
    }

    @Override
    public List<ProDuty> getProDutiesByType(Byte type) {
        ProDutyExample proDutyExample = new ProDutyExample();
        proDutyExample.createCriteria().andTypeEqualTo(type);
        return proDutyMapper.selectByExample(proDutyExample);
    }

    @Override
    public Integer countProDutiesByType(Byte type) {
        ProDutyExample proDutyExample = new ProDutyExample();
        proDutyExample.createCriteria().andTypeEqualTo(type);
        return proDutyMapper.countByExample(proDutyExample);
    }


    @Override
    public List<ProDuty> getProDutiesByChosen(boolean chosen) {
        ProDutyExample proDutyExample = new ProDutyExample();
        proDutyExample.createCriteria().andChooseEqualTo(chosen);
        return proDutyMapper.selectByExample(proDutyExample);
    }

    @Override
    public Integer countProDutiesByChosen(boolean chosen) {
        ProDutyExample proDutyExample = new ProDutyExample();
        proDutyExample.createCriteria().andChooseEqualTo(chosen);
        return proDutyMapper.countByExample(proDutyExample);
    }

    @Override
    public Integer count() {
        return proDutyMapper.countByExample(new ProDutyExample());
    }
}
