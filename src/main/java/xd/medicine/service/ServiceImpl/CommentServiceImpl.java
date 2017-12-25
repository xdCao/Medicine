package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.SysLogMapper;
import xd.medicine.dao.autoMapper.UserLogMapper;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.SysLogExample;
import xd.medicine.entity.bo.UserLog;
import xd.medicine.entity.bo.UserLogExample;
import xd.medicine.service.CommentService;

import java.util.List;

/**
 * created by xdCao on 2017/12/24
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public List<UserLog> getAllUserLogsByDoctor(int doctorId) {
        UserLogExample example=new UserLogExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId);
        return userLogMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertNewUserLog(UserLog userLog) {
        userLogMapper.insertSelective(userLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserLog(int userLogId) {
        userLogMapper.deleteByPrimaryKey(userLogId);
    }

    @Override
    public List<SysLog> getAllSysLogsByDoctor(int doctorId) {
        SysLogExample example=new SysLogExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId);
        return sysLogMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertNewSysLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSysLog(int sysLogId) {
        sysLogMapper.deleteByPrimaryKey(sysLogId);
    }

    @Override
    public List<UserLog> getUserLogsByType(Integer doctorId, Integer userType) {
        UserLogExample example=new UserLogExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId).andUserTypeEqualTo(userType);
        return userLogMapper.selectByExample(example);
    }
}
