package xd.medicine.service;

import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.ProDutyLog;

import java.util.List;

/**
 * created by liubotao
 */
public interface ProDutyLogService {
    void insertNewProDutyLog(ProDutyLog proDutyLog);

    void deleteProDutyLog(int proDutyLogId);

    List<ProDutyLog> getProDutyLogsBySub(Byte subType, int subId);
}
