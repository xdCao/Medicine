package xd.medicine.service;

import xd.medicine.entity.bo.AdminUser;

import java.util.List;

/**
 * created by xdCao on 2017/12/22
 */

public interface AdminService {

    List<AdminUser> getAdminUserByAccount(String account);

}
