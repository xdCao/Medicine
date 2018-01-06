package xd.medicine.entity.bo;

public class PostDutyLog {
    private Integer id;

    private Byte fulfillTime;

    private Byte state; //0为violate，1为delay-fulfill，2为fulfill

    private Integer dutyId;

    private Byte subType;  //主体类型，1:医生，2：其他

    private Integer subId; //主体id

    private Integer objId; //客体id

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