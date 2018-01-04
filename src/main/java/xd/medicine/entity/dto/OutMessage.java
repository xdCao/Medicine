package xd.medicine.entity.dto;

/**
 * created by xdCao on 2017/12/25
 */

public class OutMessage {

    private int code;

    private String msg;

    public OutMessage(int code,String s) {
        this.code=code;
        this.msg=s;
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
}

