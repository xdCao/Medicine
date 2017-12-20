package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.OthersMapper;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.OthersExample;
import xd.medicine.service.OthersService;

import java.util.List;

/**
 * created by xdCao on 2017/12/20
 */
@Service
public class OthersServiceImpl implements OthersService {

    @Autowired
    private OthersMapper othersMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(Others others) {
        return othersMapper.insert(others);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer update(Others others) {
        return othersMapper.updateByPrimaryKeySelective(others);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer delete(int id) {
        return othersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Others getOthersById(int id) {
        return othersMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Others> getAllOthers() {
        return othersMapper.selectByExample(new OthersExample());
    }

    @Override
    public PageInfo<Others> getOthersByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Others> others = othersMapper.selectByExample(new OthersExample());
        PageInfo<Others> pageInfo=new PageInfo<>(others);
        return pageInfo;
    }

    @Override
    public List<Others> getOthersByAccount(String account) {
        OthersExample example=new OthersExample();
        example.createCriteria().andAccountEqualTo(account);
        return othersMapper.selectByExample(example);
    }
}
