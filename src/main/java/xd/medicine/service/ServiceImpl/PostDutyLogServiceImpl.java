package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PostDutyLogMapper;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.PostDutyLogExample;
import xd.medicine.service.PostDutyLogService;

import java.util.List;

/**
 * created by liubotao
 */

@Service
public class PostDutyLogServiceImpl implements PostDutyLogService{
    @Autowired
    private PostDutyLogMapper postDutyLogMapper;

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
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId).andStateEqualTo((byte)0);
        return postDutyLogMapper.countByExample(postDutyLogExample);
    }

    @Override
    public List<PostDutyLog> getPostDutyLogsBySub(Byte subType, int subId) {
        PostDutyLogExample postDutyLogExample = new PostDutyLogExample();
        postDutyLogExample.createCriteria().andSubTypeEqualTo(subType).andSubIdEqualTo(subId);
        return postDutyLogMapper.selectByExample(postDutyLogExample);
    }
}
