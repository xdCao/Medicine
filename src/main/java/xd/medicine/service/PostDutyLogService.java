package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.SysLog;
import xd.medicine.entity.bo.UserLog;
import xd.medicine.entity.dto.DutyLogForFront;

import java.util.List;

/**
 * created by liubotao
 */
public interface PostDutyLogService {

    void insertNewPostDutyLog(PostDutyLog postDutyLog);

    void deletePostDutyLog(int postDutyLogId);

    Integer countPostDutyLogsBySub(Byte subType , int subId );

    Integer countFulfilledPostDutyLogsBySub(Byte subType , int subId );

    List<PostDutyLog> getPostDutyLogsBySub(Byte subType, int subId);

    PageInfo<DutyLogForFront> getPostDutyLogsByPage(Byte subType, int subId , int page, int rows);

    void deletePostDutyLogByPatient(int patientId);
}
