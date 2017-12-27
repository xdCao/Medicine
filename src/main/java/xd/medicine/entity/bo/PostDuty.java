package xd.medicine.entity.bo;

public class PostDuty {
    private Integer id;

    private String dutyContent;

    private Byte presetTime;

    private Byte graceTime;

    private Float emer;

    private Byte type;

    private Boolean choose;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyContent() {
        return dutyContent;
    }

    public void setDutyContent(String dutyContent) {
        this.dutyContent = dutyContent == null ? null : dutyContent.trim();
    }

    public Byte getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(Byte presetTime) {
        this.presetTime = presetTime;
    }

    public Byte getGraceTime() {
        return graceTime;
    }

    public void setGraceTime(Byte graceTime) {
        this.graceTime = graceTime;
    }

    public Float getEmer() {
        return emer;
    }

    public void setEmer(Float emer) {
        this.emer = emer;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }
}