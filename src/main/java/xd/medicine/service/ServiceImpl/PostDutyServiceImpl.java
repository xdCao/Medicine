package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PostDutyMapper;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.PostDutyExample;
import xd.medicine.service.PostDutyService;

import java.util.List;

/**
 * created by liubotao
 */

@Service
public class PostDutyServiceImpl implements PostDutyService{
    private static final Logger LOGGER= LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PostDutyMapper postDutyMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertPostDuty(PostDuty postDuty) {
        return postDutyMapper.insert(postDuty);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deletePostDuty(int id) {
        return postDutyMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updatePostDuty(PostDuty postDuty) {
        return postDutyMapper.updateByPrimaryKeySelective(postDuty);
    }

    @Override
    public List<PostDuty> getAllPostDuties() {
        return postDutyMapper.selectByExample(new PostDutyExample());
    }

    @Override
    public PostDuty getPostDutyById(int id) {
        return postDutyMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo<PostDuty> getPostDutiesByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<PostDuty> postDuties = postDutyMapper.selectByExample(new PostDutyExample());
        PageInfo<PostDuty> postDutyPageInfo=new PageInfo<>(postDuties);
        return postDutyPageInfo;
    }



    @Override
    public List<PostDuty> getPostDutiesByChosen(boolean chosen) {
        PostDutyExample postDutyExample = new PostDutyExample();
        postDutyExample.createCriteria().andChooseEqualTo(chosen);
        return postDutyMapper.selectByExample(postDutyExample);
    }

    @Override
    public Integer countPostDutiesByChosen(boolean chosen) {
        PostDutyExample postDutyExample = new PostDutyExample();
        postDutyExample.createCriteria().andChooseEqualTo(chosen);
        return postDutyMapper.countByExample(postDutyExample);
    }

    @Override
    public Integer count() {
        return postDutyMapper.countByExample(new PostDutyExample());
    }
}
