package com.edu.zju.lab508.artificialliver.monitor.monitor.listener;

/**
 * Created by wuhaitao on 2015/5/16.
 */
public class DataFrameEvent {
	private int type;
	private int length;
	private byte[] data;

	public DataFrameEvent(int type, int length, byte[] data) {
		this.type = type;
		this.length = length;
		this.data = data;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
