package com.zju.als.monitor.guardian.domain;

public class HeartRateData {
	private String time_stamp;
	private String heart_rate;

	public String getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(String heart_rate) {
		this.heart_rate = heart_rate;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("HeartRateData{");
		sb.append("heart_rate='").append(heart_rate).append('\'');
		sb.append(", time_stamp='").append(time_stamp).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
