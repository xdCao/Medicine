package xd.medicine.service;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import xd.medicine.entity.dto.PatientWithTrust;

import java.util.List;

/**
 * created by xdCao on 2017/12/25
 */

public interface AuthService {

    List<PatientWithTrust> judgeBroadcast(Integer doctorId);

}
