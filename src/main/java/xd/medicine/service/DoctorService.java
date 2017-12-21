package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.medicine.entity.bo.Doctor;

import javax.jnlp.IntegrationService;
import javax.print.Doc;
import java.util.List;


/**
 * created by xdCao on 2017/12/19
 */
public interface DoctorService {

    Integer insertDoctor(Doctor doctor);

    Integer updateDoctor(Doctor doctor);

    Integer deleteDoctorById(int id);

    Doctor getDoctorById(int id);

    List<Doctor> getAllDoctors();

    PageInfo<Doctor> getDoctorsByPage(int page,int rows);

    Integer countDoctorsByAccount(String account);

    List<Doctor> getDoctorByAccount(String account);
}
