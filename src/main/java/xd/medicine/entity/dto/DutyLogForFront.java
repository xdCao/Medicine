package xd.medicine.entity.dto;

/**
 * created by liubotao
 */
public class DutyLogForFront {
    private int objId;
    private String objName;
    private String dutyContent;
    private int type;
    private int fulfilledTime;
    private int state;

    public DutyLogForFront(int objId, String objName, String dutyContent, int type, int fulfilledTime, int state) {

        this.objId = objId;
        this.objName = objName;
        this.dutyContent = dutyContent;
        this.type = type;
        this.fulfilledTime = fulfilledTime;
        this.state = state;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getDutyContent() {
        return dutyContent;
    }

    public void setDutyContent(String dutyContent) {
        this.dutyContent = dutyContent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFulfilledTime() {
        return fulfilledTime;
    }

    public void setFulfilledTime(int fulfilledTime) {
        this.fulfilledTime = fulfilledTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
