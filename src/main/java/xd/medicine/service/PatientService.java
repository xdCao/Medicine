package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.dto.PatientWithTrust;

import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */

public interface PatientService {

    double TEMPERATURE_LIMIT_HIGH=37.5;
    double TEMPERATURE_LIMIT_LOW=36.0;
    int HEARTBEAT_LIMIT_HIGH=100;
    int HEARTBEAT_LIMIT_LOW=60;
    double BLOOD_PPRESSURE_LIMIT_HIGH=140.0;
    double BLOOD_PRESSURE_LIMIT_LOW=60.0;

    Integer insertPatient(Patient patient);

    int deletePatient(int id);

    Integer updatePatient(Patient patient);

    List<PatientWithTrust> getAllPatients();

    PatientWithTrust getPatientById(int id);

    PageInfo<PatientWithTrust> getPatientByPage(int page, int rows);

    List<PatientWithTrust> getPatientsByDoctor(int doctorId);

    Integer countPatientByAccount(String account);

    List<PatientWithTrust> getPatientByAccount(String account);

    Patient updateEmergency(Integer patientId, Double temperature, Integer heartBeat, Double bloodPressure);

    Integer count();

    List<Doctor> getSisDoctorsByPatientId(int patientId);

    List<PatientWithTrust> sensePatientInEmergency();

}
