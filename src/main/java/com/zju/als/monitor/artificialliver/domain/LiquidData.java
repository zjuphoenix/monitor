package com.zju.als.monitor.artificialliver.domain;

public class LiquidData {
	private String time_stamp;
	private String blood_pump_t;
	private String tripe_pump_t;
	private String filtration_pump_t;
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getBlood_pump_t() {
		return blood_pump_t;
	}
	public void setBlood_pump_t(String blood_pump_t) {
		this.blood_pump_t = blood_pump_t;
	}
	public String getTripe_pump_t() {
		return tripe_pump_t;
	}
	public void setTripe_pump_t(String tripe_pump_t) {
		this.tripe_pump_t = tripe_pump_t;
	}
	public String getFiltration_pump_t() {
		return filtration_pump_t;
	}
	public void setFiltration_pump_t(String filtration_pump_t) {
		this.filtration_pump_t = filtration_pump_t;
	}
	
}
