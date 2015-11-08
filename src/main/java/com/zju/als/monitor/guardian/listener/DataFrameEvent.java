package com.zju.als.monitor.guardian.listener;

/**
 * Created by wuhaitao on 2015/5/16.
 */
public class DataFrameEvent {
	private String surgery_no;
	private int type;
	private int length;
	private byte[] data;

	public DataFrameEvent(String surgery_no, int type, int length, byte[] data) {
		this.surgery_no = surgery_no;
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

	public String getSurgery_no() {
		return surgery_no;
	}

	public void setSurgery_no(String surgery_no) {
		this.surgery_no = surgery_no;
	}
}
