package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.PatientMapper;
import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.PatientExample;
import xd.medicine.service.PatientService;

import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */
@Service
public class PatientServiceImpl implements PatientService{

    private static final Logger LOGGER= LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientMapper patientMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertPatient(Patient patient) {
        return patientMapper.insert(patient);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deletePatient(int id) {
        return patientMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updatePatient(Patient patient) {
        return patientMapper.updateByPrimaryKeySelective(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientMapper.selectByExample(new PatientExample());
    }

    @Override
    public Patient getPatientById(int id) {
        return patientMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Patient> getPatientByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Patient> patients = patientMapper.selectByExample(new PatientExample());
        PageInfo<Patient> patientPageInfo=new PageInfo<>(patients);
        return patientPageInfo;
    }

    @Override
    public List<Patient> getPatientsByDoctor(int doctorId) {
        PatientExample patientExample=new PatientExample();
        patientExample.createCriteria().andDoctorIdEqualTo(doctorId);
        return patientMapper.selectByExample(patientExample);
    }

    @Override
    public Integer countPatientByAccount(String account) {
        PatientExample example=new PatientExample();
        example.createCriteria().andAccountEqualTo(account);
        return patientMapper.countByExample(example);
    }

    @Override
    public List<Patient> getPatientByAccount(String account) {
        PatientExample example=new PatientExample();
        example.createCriteria().andAccountEqualTo(account);
        return patientMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Patient updateEmergency(Integer patientId, Double temperature, Integer heartBeat, Double bloodPressure) {
        Patient patient=new Patient();
        patient.setId(patientId);
        patient.setTemperature(temperature);
        patient.setBloodPressure(bloodPressure);
        patient.setHeartBeat(heartBeat);
        patient.setIsInEmergency(judgeEmergency(temperature,heartBeat,bloodPressure));
        patientMapper.updateByPrimaryKeySelective(patient);
        return patient;
    }

    @Override
    public Integer count() {
        return patientMapper.countByExample(new PatientExample());
    }

    private Boolean judgeEmergency(Double temperature, Integer heartBeat, Double bloodPressure) {
        if (temperature<=TEMPERATURE_LIMIT_HIGH&&temperature>=TEMPERATURE_LIMIT_LOW)
            if (heartBeat<=HEARTBEAT_LIMIT_HIGH&&heartBeat>=HEARTBEAT_LIMIT_LOW)
                if (bloodPressure<=BLOOD_PPRESSURE_LIMIT_HIGH&&bloodPressure>=BLOOD_PRESSURE_LIMIT_LOW)
                    return false;
        return true;
    }


}
