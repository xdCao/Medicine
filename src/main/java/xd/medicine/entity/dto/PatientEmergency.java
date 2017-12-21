package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Patient;

/**
 * 带有紧急信息的病人实体
 * created by xdCao on 2017/12/21
 */

public class PatientEmergency {

    private Patient patient;

    private EmergencyAttr emergencyAttr;

    public PatientEmergency(Patient patient, EmergencyAttr emergencyAttr) {
        this.patient = patient;
        this.emergencyAttr = emergencyAttr;
    }

    public PatientEmergency() {

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EmergencyAttr getEmergencyAttr() {
        return emergencyAttr;
    }

    public void setEmergencyAttr(EmergencyAttr emergencyAttr) {
        this.emergencyAttr = emergencyAttr;
    }
}
