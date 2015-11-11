package com.zju.als.monitor.service;

import com.zju.als.monitor.guardian.DataCenter;
import com.zju.als.monitor.guardian.Monitor;
import com.zju.als.monitor.guardian.MonitorCenter;
import com.zju.als.monitor.guardian.datafile.DataFileCenter;
import com.zju.als.monitor.guardian.listener.DataEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/11/7.
 */
@RestController
public class AdminServer {
    @Autowired
    private MonitorCenter monitorCenter;
    @Autowired
    private DataFileCenter dataFileCenter;
    @Autowired
    private DataCenter dataCenter;

    @RequestMapping("/start")
    public ResponseEntity<String> start(@RequestParam("surgery_no")String surgery_no, @RequestParam("host")String host, @RequestParam("port")int port){
        try {
            monitorCenter.createMonitor(surgery_no, host, port);
            dataFileCenter.createEcgDataFile(surgery_no);
            dataCenter.createData(surgery_no);
            /*monitorCenter.createMonitor("123", "10.13.81.181", 60129);
            dataFileCenter.createEcgDataFile("123");
            dataCenter.createData("123");*/
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("start monitor failed!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("start monitor success!",HttpStatus.OK);
    }
    @RequestMapping("/stop")
    public ResponseEntity<String> stop(@RequestParam("surgery_no")String surgery_no, @RequestParam("host")String host, @RequestParam("port")int port){
        try {
            monitorCenter.stopMonitor(surgery_no);
            dataFileCenter.stopEcgDataFile(surgery_no);
            dataCenter.close(surgery_no);
            /*monitorCenter.stopMonitor("123");
            dataFileCenter.stopEcgDataFile("123");
            dataCenter.close("123");*/
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("stop monitor success!",HttpStatus.OK);
    }
}
