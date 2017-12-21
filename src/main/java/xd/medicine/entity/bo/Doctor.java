package xd.medicine.entity.bo;

import java.util.Date;

public class Doctor {
    private Integer id;

    private String name;

    private Byte position;

    private String officeLocation;

    private String officePhone;

    private Date registryDate;

    private Byte department;

    private Byte title;

    private Byte workage;

    private Byte degree;

    private Boolean isFree;

    private String password;

    private String account;

    private Boolean isin;

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

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation == null ? null : officeLocation.trim();
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    public Byte getDepartment() {
        return department;
    }

    public void setDepartment(Byte department) {
        this.department = department;
    }

    public Byte getTitle() {
        return title;
    }

    public void setTitle(Byte title) {
        this.title = title;
    }

    public Byte getWorkage() {
        return workage;
    }

    public void setWorkage(Byte workage) {
        this.workage = workage;
    }

    public Byte getDegree() {
        return degree;
    }

    public void setDegree(Byte degree) {
        this.degree = degree;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Boolean getIsin() {
        return isin;
    }

    public void setIsin(Boolean isin) {
        this.isin = isin;
    }
}