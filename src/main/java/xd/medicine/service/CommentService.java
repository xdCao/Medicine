package xd.medicine.service;

import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.UserLog;

import java.util.List;

/**
 * created by xdCao on 2017/12/24
 */

public interface CommentService {

    List<UserLog> getAllUserLogsByDoctor(int doctorId);

    void insertNewUserLog(UserLog userLog);

    void deleteUserLog(int userLogId);

    List<SysLog> getAllSysLogsByDoctor(int doctorId);

    void insertNewSysLog(SysLog sysLog);

    void deleteSysLog(int sysLogId);


}
