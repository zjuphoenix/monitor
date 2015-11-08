package com.zju.als.monitor.reporter;

/**
 * Created by Administrator on 2015/11/4.
 */
public class Surgeryinfo {
    private String patientName;
    private String gender;
    private String age;
    private String treatMethod;
    private String doctor;
    private String time;
    private String surgeryNo;
    private String operationTime;
    public Surgeryinfo(String patientName,String gender,String age, String treatMethod,String doctor,String surgeryNo,String time,String operationTime) {
        this.patientName=patientName;
        this.gender=gender;
        this.age=age;
        this.treatMethod=treatMethod;
        this.doctor=doctor;
        this.surgeryNo=surgeryNo;
        this.time=time;
        this.operationTime=operationTime;
    }
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTreatMethod() {
		return treatMethod;
	}
	public void setTreatMethod(String treatMethod) {
		this.treatMethod = treatMethod;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSurgeryNo() {
		return surgeryNo;
	}
	public void setSurgeryNo(String surgeryNo) {
		this.surgeryNo = surgeryNo;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
    
}
