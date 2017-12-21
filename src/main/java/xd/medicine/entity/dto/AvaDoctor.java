package xd.medicine.entity.dto;

import xd.medicine.entity.bo.Doctor;

import javax.print.Doc;

/**
 * created by xdCao on 2017/12/21
 */

public class AvaDoctor {

    private Doctor doctor;

    private boolean availability;

    public AvaDoctor(Doctor doctor, boolean availability) {
        this.doctor = doctor;
        this.availability = availability;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
