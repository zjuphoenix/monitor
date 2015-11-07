package com.edu.zju.lab508.artificialliver.monitor.reporter;

/**
 * Created by Administrator on 2015/11/4.
 */
public class OperationInfo {
    public String patientName;
    public String gender;
    public String age;
    public String treatMethod;
    public String doctor;
    public String time;
    public String operationNo;
    public String operationTime;
    public OperationInfo(String patientName,String gender,String age, String treatMethod,String doctor,String operationNo,String time,String operationTime) {
        this.patientName=patientName;
        this.gender=gender;
        this.age=age;
        this.treatMethod=treatMethod;
        this.doctor=doctor;
        this.operationNo=operationNo;
        this.time=time;
        this.operationTime=operationTime;
    }
}
