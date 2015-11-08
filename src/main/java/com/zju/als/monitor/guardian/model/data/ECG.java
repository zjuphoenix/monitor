package com.zju.als.monitor.guardian.model.data;

public class ECG {
	public int breathingrate;
	public int ST1;
	public int ST2;
	public int ST3;
	public int heartrate;
	public short[][] ecg = new short[3][500];
	public byte[] flag = new byte[500];
}
