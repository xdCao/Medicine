package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.TrustAttrMapper;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.service.TrustAttrService;

/**
 * created by xdCao on 2017/12/20
 */
@Service
public class TrustAttrServiceImpl implements TrustAttrService {

    @Autowired
    private TrustAttrMapper trustAttrMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertTrustAttr(TrustAttr trustAttr) {
        return trustAttrMapper.insert(trustAttr);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateTrustAttr(TrustAttr trustAttr) {
        return trustAttrMapper.updateByPrimaryKeySelective(trustAttr);
    }

    @Override
    public TrustAttr getTrustAttrById(int id) {
        return trustAttrMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer trustattrId) {
        trustAttrMapper.deleteByPrimaryKey(trustattrId);
    }

}
