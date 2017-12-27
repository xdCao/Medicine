package xd.medicine.entity.bo;

public class ProDuty {
    private Integer id;

    private String dutyContent;

    private Byte presetTime;

    private Byte type; //0为强制，1为附加

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