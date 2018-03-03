package xd.medicine.entity.bo;

public class PostDutyLog {
    private Integer id;

    private Byte fulfillTime;

    private Byte state;

    private Integer dutyId;

    private Byte subType;

    private Integer subId;

    private Integer objId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getFulfillTime() {
        return fulfillTime;
    }

    public void setFulfillTime(Byte fulfillTime) {
        this.fulfillTime = fulfillTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public Byte getSubType() {
        return subType;
    }

    public void setSubType(Byte subType) {
        this.subType = subType;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }
}