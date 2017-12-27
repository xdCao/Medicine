package xd.medicine.service;

import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.UserLog;

import java.util.List;

/**
 * created by liubotao
 */
public interface PostDutyLogService {

    void insertNewPostDutyLog(PostDutyLog postDutyLog);

    void deletePostDutyLog(int postDutyLogId);

    Integer countPostDutyLogsBySub(Byte subType , int subId );

    Integer countFulfilledPostDutyLogsBySub(Byte subType , int subId );
}
