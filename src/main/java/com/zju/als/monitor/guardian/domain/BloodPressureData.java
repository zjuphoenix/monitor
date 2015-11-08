package com.zju.als.monitor.guardian.domain;

public class BloodPressureData {
	private String time_stamp;
	private String systolic_pressure;
    private String diastolic_pressure;
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getSystolic_pressure() {
		return systolic_pressure;
	}
	public void setSystolic_pressure(String systolic_pressure) {
		this.systolic_pressure = systolic_pressure;
	}
	public String getDiastolic_pressure() {
		return diastolic_pressure;
	}
	public void setDiastolic_pressure(String diastolic_pressure) {
		this.diastolic_pressure = diastolic_pressure;
	}
    
    

}
