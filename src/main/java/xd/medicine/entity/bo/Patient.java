package xd.medicine.entity.bo;

import java.util.Date;

public class Patient {
    private Integer id;

    private String name;

    private Integer doctorId;

    private String phone;

    private Date registryDate;

    private Integer trustattrId;

    private Boolean senseAware;

    private String illnessCondition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    public Integer getTrustattrId() {
        return trustattrId;
    }

    public void setTrustattrId(Integer trustattrId) {
        this.trustattrId = trustattrId;
    }

    public Boolean getSenseAware() {
        return senseAware;
    }

    public void setSenseAware(Boolean senseAware) {
        this.senseAware = senseAware;
    }

    public String getIllnessCondition() {
        return illnessCondition;
    }

    public void setIllnessCondition(String illnessCondition) {
        this.illnessCondition = illnessCondition == null ? null : illnessCondition.trim();
    }
}