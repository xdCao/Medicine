package xd.medicine.controller;

/**
 * created by xdCao on 2017/12/20
 */

public class FrontResult {

    private int statusCode;
    private Object entity;
    private String msg;

    public FrontResult(int statusCode, Object entity, String msg) {
        this.statusCode = statusCode;
        this.entity = entity;
        this.msg = msg;
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
