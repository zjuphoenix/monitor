package com.zju.als.monitor.artificialliver.domain;

public class PressureData {
    private String machine_no;
    private String surgery_no;
    private long time_stamp;
    private String in_blood_pressure;
    private String plasma_inlet_pressure;
    private String arterial_pressure;
    private String venous_pressure;
    private String plasma_pressure;
    private String transmembrane_pressure;

    public PressureData() {
    }

    public PressureData(String machine_no, String surgery_no,long time_stamp,String in_blood_pressure,String plasma_inlet_pressure, String arterial_pressure, String venous_pressure, String plasma_pressure,String transmembrane_pressure) {
        this.venous_pressure = venous_pressure;
        this.transmembrane_pressure = transmembrane_pressure;
        this.arterial_pressure = arterial_pressure;
        this.in_blood_pressure = in_blood_pressure;
        this.machine_no = machine_no;
        this.plasma_inlet_pressure = plasma_inlet_pressure;
        this.plasma_pressure = plasma_pressure;
        this.surgery_no = surgery_no;
        this.time_stamp = time_stamp;
    }

    public String getArterial_pressure() {
        return arterial_pressure;
    }

    public void setArterial_pressure(String arterial_pressure) {
        this.arterial_pressure = arterial_pressure;
    }

    public String getIn_blood_pressure() {
        return in_blood_pressure;
    }

    public void setIn_blood_pressure(String in_blood_pressure) {
        this.in_blood_pressure = in_blood_pressure;
    }

    public String getMachine_no() {
        return machine_no;
    }

    public void setMachine_no(String machine_no) {
        this.machine_no = machine_no;
    }

    public String getPlasma_inlet_pressure() {
        return plasma_inlet_pressure;
    }

    public void setPlasma_inlet_pressure(String plasma_inlet_pressure) {
        this.plasma_inlet_pressure = plasma_inlet_pressure;
    }

    public String getPlasma_pressure() {
        return plasma_pressure;
    }

    public void setPlasma_pressure(String plasma_pressure) {
        this.plasma_pressure = plasma_pressure;
    }

    public String getSurgery_no() {
        return surgery_no;
    }

    public void setSurgery_no(String surgery_no) {
        this.surgery_no = surgery_no;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getTransmembrane_pressure() {
        return transmembrane_pressure;
    }

    public void setTransmembrane_pressure(String transmembrane_pressure) {
        this.transmembrane_pressure = transmembrane_pressure;
    }

    public String getVenous_pressure() {
        return venous_pressure;
    }

    public void setVenous_pressure(String venous_pressure) {
        this.venous_pressure = venous_pressure;
    }
}
