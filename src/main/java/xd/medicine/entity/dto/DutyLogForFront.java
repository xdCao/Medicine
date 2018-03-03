package xd.medicine.entity.dto;

/**
 * created by liubotao
 */
public class DutyLogForFront {
    private int objId;
    private String dutyContent;
    private int fulfilledTime;
    private int state;

    public DutyLogForFront(int objId, String dutyContent, int fulfilledTime, int state) {
        this.objId = objId;
        this.dutyContent = dutyContent;
        this.fulfilledTime = fulfilledTime;
        this.state = state;
    }

    public int getObjId() {
        return objId;
    }

    public String getDutyContent() {
        return dutyContent;
    }

    public int getFulfilledTime() {
        return fulfilledTime;
    }

    public int getState() {
        return state;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public void setDutyContent(String dutyContent) {
        this.dutyContent = dutyContent;
    }

    public void setFulfilledTime(int fulfilledTime) {
        this.fulfilledTime = fulfilledTime;
    }

    public void setState(int state) {
        this.state = state;
    }
}
