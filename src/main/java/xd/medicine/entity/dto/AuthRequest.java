package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Doctor;
import xd.medicine.entity.bo.Patient;

import javax.print.Doc;
import java.io.Serializable;
import java.util.Date;

/**
 * created by xdCao on 2017/12/25
 */

public class AuthRequest implements Serializable{

    private Integer userType;
    private Integer userId;
    private Integer patientId;

    public AuthRequest() {
    }

    public AuthRequest(Integer userType, Integer userId, Integer patientId) {
        this.userType = userType;
        this.userId = userId;
        this.patientId = patientId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "userType:   "+userType+",   userId:    "+userId+",  patientId:  "+patientId;
    }
}
