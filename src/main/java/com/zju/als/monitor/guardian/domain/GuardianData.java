package com.zju.als.monitor.guardian.domain;

public class GuardianData {
	private String dev_no;
	private String surgery_no;
	private long time_stamp;
	private String heart_rate;
	private String systolic_pressure;
	private String diastolic_pressure;
	private String blood_oxygen;

	public GuardianData() {
	}

	public GuardianData(String dev_no, String surgery_no, long time_stamp, String heart_rate, String systolic_pressure, String diastolic_pressure, String blood_oxygen) {
		this.blood_oxygen = blood_oxygen;
		this.dev_no = dev_no;
		this.diastolic_pressure = diastolic_pressure;
		this.heart_rate = heart_rate;
		this.surgery_no = surgery_no;
		this.systolic_pressure = systolic_pressure;
		this.time_stamp = time_stamp;
	}

	public String getBlood_oxygen() {
		return blood_oxygen;
	}

	public void setBlood_oxygen(String blood_oxygen) {
		this.blood_oxygen = blood_oxygen;
	}

	public String getDev_no() {
		return dev_no;
	}

	public void setDev_no(String dev_no) {
		this.dev_no = dev_no;
	}

	public String getDiastolic_pressure() {
		return diastolic_pressure;
	}

	public void setDiastolic_pressure(String diastolic_pressure) {
		this.diastolic_pressure = diastolic_pressure;
	}

	public String getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(String heart_rate) {
		this.heart_rate = heart_rate;
	}

	public String getSurgery_no() {
		return surgery_no;
	}

	public void setSurgery_no(String surgery_no) {
		this.surgery_no = surgery_no;
	}

	public String getSystolic_pressure() {
		return systolic_pressure;
	}

	public void setSystolic_pressure(String systolic_pressure) {
		this.systolic_pressure = systolic_pressure;
	}

	public long getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(long time_stamp) {
		this.time_stamp = time_stamp;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("GuardianData{");
		sb.append("blood_oxygen='").append(blood_oxygen).append('\'');
		sb.append(", dev_no='").append(dev_no).append('\'');
		sb.append(", surgery_no='").append(surgery_no).append('\'');
		sb.append(", time_stamp=").append(time_stamp);
		sb.append(", heart_rate='").append(heart_rate).append('\'');
		sb.append(", systolic_pressure='").append(systolic_pressure).append('\'');
		sb.append(", diastolic_pressure='").append(diastolic_pressure).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
