package xd.medicine.entity.dto;

import xd.medicine.entity.bo.ProDuty;

/**
 * created by liubotao
 */
public class FulfilledProDuty {
    private ProDuty proDuty;
    private int state;

    public FulfilledProDuty(ProDuty proDuty, int state) {
        this.proDuty = proDuty;
        this.state = state;
    }

    public ProDuty getProDuty() {
        return proDuty;
    }

    public void setProDuty(ProDuty proDuty) {
        this.proDuty = proDuty;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
