package xd.medicine.entity.dto;

/**
 * created by xdCao on 2017/12/25
 */

public class OutMessage {

    private int code;

    private int patientId;

    private String msg;

    public OutMessage(int code,String s) {
        this.code=code;
        this.msg=s;
    }

    public OutMessage(int code, int patientId, String msg) {
        this.code = code;
        this.patientId = patientId;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}

