package xd.medicine.entity.dto;

import xd.medicine.entity.bo.ProDuty;

import java.util.List; /**
 * created by xdCao on 2018/1/8
 */

public class DutySensitivity {

    private List<ProDuty> proDutyList;
    private List<Integer> fullfillStateList;
    private int calGrade;
    private float sensitivity;
    private float risk;


    public float getRisk() {
        return risk;
    }

    public void setRisk(float risk) {
        this.risk = risk;
    }

    public DutySensitivity(List<ProDuty> proDutyList, List<Integer> fullfillStateList, int calGrade, float sensitivity, float risk) {
        this.proDutyList = proDutyList;
        this.fullfillStateList = fullfillStateList;
        this.calGrade = calGrade;
        this.sensitivity = sensitivity;
        this.risk=risk;
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
}
