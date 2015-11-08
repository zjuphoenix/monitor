package com.zju.als.monitor.guardian.model.data;

public class BloodOxygen {
	public int pulserate;//脉率
	public int saturation;//饱和度
	public int pulseintensity;//脉搏强度
	public byte[] waveform = new byte[125];//波形标志
	public byte[] oxygenmark = new byte[125];//血氧标志
}
