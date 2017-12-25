package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Doctor;

/**
 * created by liubotao
 */
public class DoctorTrustResult {
    private Doctor doctor;
    private float mt;
    private float rcm;
    private float rep;
    private float hbt;
    private float trust;

    public DoctorTrustResult(Doctor doctor, float mt, float rcm, float rep, float hbt, float trust) {
        this.doctor = doctor;
        this.mt = mt;
        this.rcm = rcm;
        this.rep = rep;
        this.hbt = hbt;
        this.trust = trust;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public float getMt() {
        return mt;
    }

    public float getRcm() {
        return rcm;
    }

    public float getRep() {
        return rep;
    }

    public float getTrust() {
        return trust;
    }

    public float getHbt() {
        return hbt;
    }

    public void setHbt(float hbt) {
        this.hbt = hbt;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setMt(float mt) {
        this.mt = mt;
    }

    public void setRcm(float rcm) {
        this.rcm = rcm;
    }

    public void setRep(float rep) {
        this.rep = rep;
    }

    public void setTrust(float trust) {
        this.trust = trust;
    }

    @Override
    public String toString() {
        return "DoctorTrustResult{" +
                "doctor=" + doctor.getId() + ":" +doctor.getName() +
                ", mt=" + mt +
                ", rcm=" + rcm +
                ", rep=" + rep +
                ", hbt=" + hbt +
                ", trust=" + trust +
                '}';
    }
}
