package xd.medicine.entity.dto;

import xd.medicine.calculator.DutyExecutor;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;

import java.util.List; /**
 * created by xdCao on 2018/1/8
 */

public class DutySensitivity {

    private List<FulfilledProDuty> fulFilledProdutyList;
    private int calGrade;
    private float sensitivity;
    private float unTrust;
    private float risk;
    private int authFlag; // 0:一次授权失败，1：一次授权成功，2：二次授权失败，3：二次授权成功
    private List<FulfilledPostDuty> fulfilledPostDutyList;
    private float poobtp;
    private float poobAward;
    private float poobPenaltyDelay;
    private float poobPenaltyViolate;
    private float poobTrustOld;
    private float poobTrustNew;
    private float riskThs;
    private float probAward;

    public DutySensitivity(List<FulfilledProDuty> fulFilledProdutyList, int calGrade, float sensitivity, float unTrust,
                           float risk, int authFlag, List<FulfilledPostDuty> fulfilledPostDutyList, float poobtp,
                           float poobAward, float poobPenaltyDelay, float poobPenaltyViolate, float poobTrustOld,
                           float poobTrustNew,float riskThs, float probAward) {
        this.fulFilledProdutyList = fulFilledProdutyList;
        this.calGrade = calGrade;
        this.sensitivity = sensitivity;
        this.unTrust = unTrust;
        this.risk = risk;
        this.authFlag = authFlag;
        this.fulfilledPostDutyList = fulfilledPostDutyList;
        this.poobtp = poobtp;
        this.poobAward = poobAward;
        this.poobPenaltyDelay = poobPenaltyDelay;
        this.poobPenaltyViolate = poobPenaltyViolate;
        this.poobTrustOld = poobTrustOld;
        this.poobTrustNew = poobTrustNew;
        this.riskThs = riskThs;
        this.probAward = probAward;
    }

    @Override
    public String toString() {
        return "DutySensitivity{" +
                "fulFilledProdutyList=" + fulFilledProdutyList +
                ", calGrade=" + calGrade +
                ", sensitivity=" + sensitivity +
                ", unTrust=" + unTrust +
                ", risk=" + risk +
                ", authFlag=" + authFlag +
                ", fulfilledPostDutyList=" + fulfilledPostDutyList +
                ", poobtp=" + poobtp +
                ", poobAward=" + poobAward +
                ", poobPenaltyDelay=" + poobPenaltyDelay +
                ", poobPenaltyViolate=" + poobPenaltyViolate +
                ", poobTrustOld=" + poobTrustOld +
                ", poobTrustNew=" + poobTrustNew +
                ", riskThs=" + riskThs +
                ", probAward=" + probAward +
                '}';
    }

    public List<FulfilledProDuty> getFulFilledProdutyList() {
        return fulFilledProdutyList;
    }

    public void setFulFilledProdutyList(List<FulfilledProDuty> fulFilledProdutyList) {
        this.fulFilledProdutyList = fulFilledProdutyList;
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

    public float getUnTrust() {
        return unTrust;
    }

    public void setUnTrust(float unTrust) {
        this.unTrust = unTrust;
    }

    public float getRisk() {
        return risk;
    }

    public void setRisk(float risk) {
        this.risk = risk;
    }

    public int getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(int authFlag) {
        this.authFlag = authFlag;
    }

    public List<FulfilledPostDuty> getFulfilledPostDutyList() {
        return fulfilledPostDutyList;
    }

    public void setFulfilledPostDutyList(List<FulfilledPostDuty> fulfilledPostDutyList) {
        this.fulfilledPostDutyList = fulfilledPostDutyList;
    }

    public float getPoobtp() {
        return poobtp;
    }

    public void setPoobtp(float poobtp) {
        this.poobtp = poobtp;
    }

    public float getPoobAward() {
        return poobAward;
    }

    public void setPoobAward(float poobAward) {
        this.poobAward = poobAward;
    }

    public float getPoobPenaltyDelay() {
        return poobPenaltyDelay;
    }

    public void setPoobPenaltyDelay(float poobPenaltyDelay) {
        this.poobPenaltyDelay = poobPenaltyDelay;
    }

    public float getPoobPenaltyViolate() {
        return poobPenaltyViolate;
    }

    public void setPoobPenaltyViolate(float poobPenaltyViolate) {
        this.poobPenaltyViolate = poobPenaltyViolate;
    }

    public float getPoobTrustOld() {
        return poobTrustOld;
    }

    public void setPoobTrustOld(float poobTrustOld) {
        this.poobTrustOld = poobTrustOld;
    }

    public float getPoobTrustNew() {
        return poobTrustNew;
    }

    public void setPoobTrustNew(float poobTrustNew) {
        this.poobTrustNew = poobTrustNew;
    }

    public float getRiskThs() {
        return riskThs;
    }

    public void setRiskThs(float riskThs) {
        this.riskThs = riskThs;
    }

    public float getProbAward() {
        return probAward;
    }

    public void setProbAward(float probAward) {
        this.probAward = probAward;
    }
}
