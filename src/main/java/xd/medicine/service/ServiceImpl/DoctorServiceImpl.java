package xd.medicine.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xd.medicine.dao.autoMapper.DoctorMapper;
import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.DoctorExample;
import xd.medicine.entity.dto.AvaDoctor;
import xd.medicine.service.DoctorService;

import java.util.ArrayList;
import java.util.List;

/**
 * created by xdCao on 2017/12/19
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    private static final Logger LOGGER= LoggerFactory.getLogger(DoctorServiceImpl.class);

    /*
     * 0:普通员工
     * 1:主任
     * 2:副主任
     * 3:科长
     * 4:组长
     * 5:院长
     */
    public static final int NORMAL_EMPLOYEE=0;
    public static final int DIRECTOR=1;
    public static final int SUB_DIRECTOR=2;
    public static final int CHIEF=3;
    public static final int GROUP_LEADER=4;
    public static final int DEAN=5;


    @Autowired
    private DoctorMapper doctorMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertDoctor(Doctor doctor) {
        return doctorMapper.insert(doctor);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateDoctor(Doctor doctor) {
        return doctorMapper.updateByPrimaryKeySelective(doctor);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteDoctorById(int id) {
        return doctorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorMapper.selectByExample(new DoctorExample());
    }

    @Override
    public PageInfo<AvaDoctor> getDoctorsByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Doctor> doctors = doctorMapper.selectByExample(new DoctorExample());
        List<AvaDoctor> avaDoctors=new ArrayList<>();
        for (Doctor doctor:doctors){
            AvaDoctor avaDoctor=new AvaDoctor(doctor,doctor.getIsFree()&&doctor.getIsin());
            avaDoctors.add(avaDoctor);
        }
        PageInfo<AvaDoctor> doctorPageInfo=new PageInfo<>(avaDoctors);
        return doctorPageInfo;
    }

    @Override
    public Integer countDoctorsByAccount(String account) {
        DoctorExample example=new DoctorExample();
        example.createCriteria().andAccountEqualTo(account);
        return doctorMapper.countByExample(example);
    }

    @Override
    public List<Doctor> getDoctorByAccount(String account) {
        DoctorExample example=new DoctorExample();
        example.createCriteria().andAccountEqualTo(account);
        return doctorMapper.selectByExample(example);
    }

    @Override
    public Integer count() {
        return doctorMapper.countByExample(new DoctorExample());
    }


}
