package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Doctor;

/**
 * created by liubotao
 */
public class DoctorTrustResult {
    private int doctorId;
    private String name;
    private float mt;
    private float rcm;
    private float rep;
    private float hbt;
    private float trust;
    private Boolean isFree;
    private Boolean isIn;
    private Boolean ava;
    private int grade;

    public DoctorTrustResult() {
        this.trust=0;
    }

    public DoctorTrustResult(int doctorId, String name, float mt, float rcm, float rep, float hbt, float trust, Boolean isFree, Boolean isIn, Boolean ava, int grade) {
        this.doctorId = doctorId;
        this.name = name;
        this.mt = mt;
        this.rcm = rcm;
        this.rep = rep;
        this.hbt = hbt;
        this.trust = trust;
        this.isFree = isFree;
        this.isIn = isIn;
        this.ava = ava;
        this.grade = grade;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMt() {
        return mt;
    }

    public void setMt(float mt) {
        this.mt = mt;
    }

    public float getRcm() {
        return rcm;
    }

    public void setRcm(float rcm) {
        this.rcm = rcm;
    }

    public float getRep() {
        return rep;
    }

    public void setRep(float rep) {
        this.rep = rep;
    }

    public float getHbt() {
        return hbt;
    }

    public void setHbt(float hbt) {
        this.hbt = hbt;
    }

    public float getTrust() {
        return trust;
    }

    public void setTrust(float trust) {
        this.trust = trust;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Boolean getIn() {
        return isIn;
    }

    public void setIn(Boolean in) {
        isIn = in;
    }

    public Boolean getAva() {
        return ava;
    }

    public void setAva(Boolean ava) {
        this.ava = ava;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "DoctorTrustResult{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", mt=" + mt +
                ", rcm=" + rcm +
                ", rep=" + rep +
                ", hbt=" + hbt +
                ", trust=" + trust +
                ", isFree=" + isFree +
                ", isIn=" + isIn +
                ", ava=" + ava +
                ", grade=" + grade +
                '}';
    }
}
