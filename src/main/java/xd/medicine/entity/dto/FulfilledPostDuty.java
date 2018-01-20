package xd.medicine.entity.dto;

import xd.medicine.entity.bo.PostDuty;

/**
 * created by liubotao
 */
public class FulfilledPostDuty {
    private PostDuty postDuty;
    private int fulFilledTime;

    public FulfilledPostDuty(PostDuty postDuty, int fulFilledTime) {
        this.postDuty = postDuty;
        this.fulFilledTime = fulFilledTime;
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
}
