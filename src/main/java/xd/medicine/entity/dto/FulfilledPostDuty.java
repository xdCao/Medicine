package xd.medicine.entity.dto;

import xd.medicine.entity.bo.PostDuty;

/**
 * created by liubotao
 */
public class FulfilledPostDuty {
    private PostDuty postDuty;
    private int fulFilledTime;
    private int state;

    public FulfilledPostDuty(PostDuty postDuty, int fulFilledTime , int state) {
        this.postDuty = postDuty;
        this.fulFilledTime = fulFilledTime;
        this.state = state;
    }

    public PostDuty getPostDuty() {
        return postDuty;
    }

    public void setPostDuty(PostDuty postDuty) {
        this.postDuty = postDuty;
    }

    public int getFulFilledTime() {
        return fulFilledTime;
    }

    public void setFulFilledTime(int fulFilledTime) {
        this.fulFilledTime = fulFilledTime;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
    }

}
