package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.dto.PatientWithTrust;

import java.util.List;

/**
 * created by liubotao
 */


public interface ProDutyService {
    Integer insertProDuty(ProDuty proDuty);

    int deleteProDuty(int id);

    Integer updateProDuty(ProDuty proDuty);

    List<ProDuty> getAllProDuties();

    ProDuty getProDutyById(int id);

    PageInfo<ProDuty> getProDutiesByPage(int page, int rows);

    List<ProDuty> getProDutiesByType(Byte type);

    Integer countProDutiesByType(Byte type);

    List<ProDuty> getProDutiesByChosen(boolean chosen);

    Integer countProDutiesByChosen(boolean chosen);

    Integer count();

}
