package xd.medicine.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.medicine.dao.autoMapper.AdminUserMapper;
import xd.medicine.entity.bo.AdminUser;
import xd.medicine.service.AdminService;

import java.util.List;

/**
 * created by xdCao on 2017/12/22
 */
@Service
public class AdminUserServiceImpl implements AdminService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public List<AdminUser> getAdminUserByAccount(String account) {
        return adminUserMapper.selectByExample();
    }
}
