package xd.medicine.entity.dto;

/**
 * 病人的紧急感知属性
 * created by xdCao on 2017/12/21
 */

public class EmergencyAttr {

    private double temperature;
    private int heartBeat;
    private double bloodPressure;

    public EmergencyAttr(double temperature, int heartBeat, double bloodPressure) {
        this.temperature = temperature;
        this.heartBeat = heartBeat;
        this.bloodPressure = bloodPressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
}
