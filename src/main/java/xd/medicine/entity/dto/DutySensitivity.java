package xd.medicine.entity.dto;

import xd.medicine.calculator.DutyExecutor;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;

import java.util.List; /**
 * created by xdCao on 2018/1/8
 */

public class DutySensitivity {

    private List<ProDuty> proDutyList;
    private List<Integer> fullfillStateList;
    private int calGrade;
    private float sensitivity;
    private float unTrust;
    private float risk;
    private int authFlag; // 0:一次授权失败，1：一次授权成功，2：二次授权失败，3：二次授权成功
    private List<PostDuty> postDutyList;
    private List<Integer> postDutyFulfilledTimeList;
    private float poobtp;
    private float poobAward;
    private float poobPenaltyDelay;
    private float poobPenaltyViolate;
    private float poobTrustOld;
    private float poobTrustNew;


    public float getRisk() {
        return risk;
    }

    public void setRisk(float risk) {
        this.risk = risk;
    }

    public DutySensitivity(List<ProDuty> proDutyList, List<Integer> fullfillStateList, int calGrade, float sensitivity, float unTrust, float risk, int authFlag, List<PostDuty> postDutyList, List<Integer> postDutyFulfilledTimeList, float poobtp, float poobAward, float poobPenaltyDelay, float poobPenaltyViolate, float poobTrustOld, float poobTrustNew) {
        this.proDutyList = proDutyList;
        this.fullfillStateList = fullfillStateList;
        this.calGrade = calGrade;
        this.sensitivity = sensitivity;
        this.unTrust = unTrust;
        this.risk = risk;
        this.authFlag = authFlag;
        this.postDutyList = postDutyList;
        this.postDutyFulfilledTimeList = postDutyFulfilledTimeList;
        this.poobtp = poobtp;
        this.poobAward = poobAward;
        this.poobPenaltyDelay = poobPenaltyDelay;
        this.poobPenaltyViolate = poobPenaltyViolate;
        this.poobTrustOld = poobTrustOld;
        this.poobTrustNew = poobTrustNew;
    }

    public List<ProDuty> getProDutyList() {
        return proDutyList;
    }



    public void setProDutyList(List<ProDuty> proDutyList) {
        this.proDutyList = proDutyList;
    }

    public List<Integer> getFullfillStateList() {
        return fullfillStateList;
    }

    public void setFullfillStateList(List<Integer> fullfillStateList) {
        this.fullfillStateList = fullfillStateList;
    }

    public int getCalGrade() {
        return calGrade;
    }

    public void setCalGrade(int calGrade) {
        this.calGrade = calGrade;
    }

    public float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }

    public List<PostDuty> getPostDutyList() {
        return postDutyList;
    }

    public List<Integer> getPostDutyFulfilledTimeList() {
        return postDutyFulfilledTimeList;
    }

    public float getPoobtp() {
        return poobtp;
    }

    public float getPoobAward() {
        return poobAward;
    }

    public float getPoobPenaltyDelay() {
        return poobPenaltyDelay;
    }

    public float getPoobPenaltyViolate() {
        return poobPenaltyViolate;
    }

    public float getPoobTrustOld() {
        return poobTrustOld;
    }

    public void setPostDutyList(List<PostDuty> postDutyList) {
        this.postDutyList = postDutyList;
    }

    public void setPostDutyFulfilledTimeList(List<Integer> postDutyFulfilledTimeList) {
        this.postDutyFulfilledTimeList = postDutyFulfilledTimeList;
    }

    public void setPoobtp(float poobtp) {
        this.poobtp = poobtp;
    }

    public void setPoobAward(float poobAward) {
        this.poobAward = poobAward;
    }

    public void setPoobPenaltyDelay(float poobPenaltyDelay) {
        this.poobPenaltyDelay = poobPenaltyDelay;
    }

    public void setPoobPenaltyViolate(float poobPenaltyViolate) {
        this.poobPenaltyViolate = poobPenaltyViolate;
    }

    public void setPoobTrustOld(float poobTrustOld) {
        this.poobTrustOld = poobTrustOld;
    }

    public int getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(int authFlag) {
        this.authFlag = authFlag;
    }

    public float getUnTrust() {
        return unTrust;
    }

    public void setUnTrust(float unTrust) {
        this.unTrust = unTrust;
    }

    public float getPoobTrustNew() {
        return poobTrustNew;
    }

    public void setPoobTrustNew(float poobTrustNew) {
        this.poobTrustNew = poobTrustNew;
    }

    @Override
    public String toString() {
        return "DutySensitivity{" +
                "proDutyList=" + proDutyList +
                ", fullfillStateList=" + fullfillStateList +
                ", calGrade=" + calGrade +
                ", sensitivity=" + sensitivity +
                ", unTrust=" + unTrust +
                ", risk=" + risk +
                ", authFlag=" + authFlag +
                ", postDutyList=" + postDutyList +
                ", postDutyFulfilledTimeList=" + postDutyFulfilledTimeList +
                ", poobtp=" + poobtp +
                ", poobAward=" + poobAward +
                ", poobPenaltyDelay=" + poobPenaltyDelay +
                ", poobPenaltyViolate=" + poobPenaltyViolate +
                ", poobTrustOld=" + poobTrustOld +
                ", poobTrustNew=" + poobTrustNew +
                '}';
    }
}
