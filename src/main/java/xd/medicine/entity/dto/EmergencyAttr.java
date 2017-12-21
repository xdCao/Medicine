package xd.medicine.entity.dto;

import xd.medicine.service.ContextService;

/**
 * 病人的紧急感知属性
 * created by xdCao on 2017/12/21
 */

public class EmergencyAttr {

    private double temperature;
    private int heartBeat;
    private double bloodPressure;
    private boolean isInEmergency;

    public EmergencyAttr(double temperature, int heartBeat, double bloodPressure) {
        this.temperature = temperature;
        this.heartBeat = heartBeat;
        this.bloodPressure = bloodPressure;
    }

    public boolean isInEmergency() {
        return isInEmergency;
    }

    public void setInEmergency(boolean inEmergency) {
        isInEmergency = inEmergency;
    }

    public EmergencyAttr() {
        this.temperature = Math.random()*3+35.0;
        this.heartBeat = (int) (Math.random()*150);
        this.bloodPressure = Math.random()*60+Math.random()*140;
        if (temperature>ContextService.TEMPERATURE_LIMIT_LOW&&temperature<ContextService.TEMPERATURE_LIMIT_HIGH){
            if (heartBeat>ContextService.HEARTBEAT_LIMIT_LOW&&heartBeat<ContextService.HEARTBEAT_LIMIT_HIGH){
                if (bloodPressure>ContextService.BLOOD_PRESSURE_LIMIT_LOW&&bloodPressure<ContextService.BLOOD_PPRESSURE_LIMIT_HIGH){
                    isInEmergency=true;
                }

            }
        }else {
            isInEmergency=false;
        }
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
