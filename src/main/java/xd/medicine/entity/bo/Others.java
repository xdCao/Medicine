package xd.medicine.entity.bo;

public class Others {
    private Integer id;

    private String name;

    private Boolean gender;

    private Integer age;

    private Byte job;

    private String company;

    private String address;

    private String phone;

    private Boolean isSendRequest;

    private String password;

    private String account;

    private Float poobTrust;

    private Boolean isInHos;

    private Boolean isOnWork;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getJob() {
        return job;
    }

    public void setJob(Byte job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getIsSendRequest() {
        return isSendRequest;
    }

    public void setIsSendRequest(Boolean isSendRequest) {
        this.isSendRequest = isSendRequest;
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

    public Float getPoobTrust() {
        return poobTrust;
    }

    public void setPoobTrust(Float poobTrust) {
        this.poobTrust = poobTrust;
    }

    public Boolean getIsInHos() {
        return isInHos;
    }

    public void setIsInHos(Boolean isInHos) {
        this.isInHos = isInHos;
    }

    public Boolean getIsOnWork() {
        return isOnWork;
    }

    public void setIsOnWork(Boolean isOnWork) {
        this.isOnWork = isOnWork;
    }
}