package com.zju.als.monitor.guardian.domain;
public class BloodOxygenData {
	private String time_stamp;
	private String blood_oxygen;
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getBlood_oxygen() {
		return blood_oxygen;
	}
	public void setBlood_oxygen(String blood_oxygen) {
		this.blood_oxygen = blood_oxygen;
	}
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("GuardianData{");
		sb.append("time_stamp=").append(time_stamp);
		sb.append(",blood_oxygen='").append(blood_oxygen).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
