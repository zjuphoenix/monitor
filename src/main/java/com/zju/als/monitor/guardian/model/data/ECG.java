package com.zju.als.monitor.guardian.model.data;

import java.util.Arrays;

public class ECG {
	public int breathingrate;//呼吸率
	public int ST1;
	public int ST2;
	public int ST3;
	public int heartrate;//心率
	public short[][] ecg = new short[3][500];
	public byte[] flag = new byte[500];

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ECG{");
		sb.append("breathingrate=").append(breathingrate);
		sb.append(", ST1=").append(ST1);
		sb.append(", ST2=").append(ST2);
		sb.append(", ST3=").append(ST3);
		sb.append(", heartrate=").append(heartrate);
		sb.append(", ecg=").append(Arrays.toString(ecg));
		sb.append(", flag=").append(Arrays.toString(flag));
		sb.append('}');
		return sb.toString();
	}
}
