package xd.medicine.entity.bo;

public class PostDutyLog {
    private Integer id;

    private Integer visitN;

    private Byte fulfillTime;

    private Byte state;

    private Integer dutyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitN() {
        return visitN;
    }

    public void setVisitN(Integer visitN) {
        this.visitN = visitN;
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
}