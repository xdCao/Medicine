package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.dto.DutyLogForFront;

import java.util.List;

/**
 * created by liubotao
 */
public interface ProDutyLogService {
    void insertNewProDutyLog(ProDutyLog proDutyLog);

    void deleteProDutyLog(int proDutyLogId);

    List<ProDutyLog> getProDutyLogsBySub(Byte subType, int subId);

    PageInfo<DutyLogForFront> getProDutyLogsByPage(Byte subType, int subId, int page, int rows);

    void deleteProDutyLogByPatient(int patientId);
}
