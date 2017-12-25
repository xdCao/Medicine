package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Patient;
import xd.medicine.entity.bo.TrustAttr;

/**
 * created by xdCao on 2017/12/25
 */

public class PatientWithTrust {

    private Patient patient;
    private TrustAttr trustAttr;

    public PatientWithTrust(Patient patient, TrustAttr trustAttr) {
        this.patient = patient;
        this.trustAttr = trustAttr;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TrustAttr getTrustAttr() {
        return trustAttr;
    }

    public void setTrustAttr(TrustAttr trustAttr) {
        this.trustAttr = trustAttr;
    }
}
