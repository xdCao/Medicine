package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.Patient;

import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */

public interface PatientService {

    Integer insertPatient(Patient patient);

    int deletePatient(int id);

    Integer updatePatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatientById(int id);

    PageInfo<Patient> getPatientByPage(int page,int rows);

    List<Patient> getPatientsByDoctor(int doctorId);

    Integer countPatientByAccount(String account);

    List<Patient> getPatientByAccount(String account);

}
