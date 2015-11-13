package com.zju.als.monitor.service;

import com.zju.als.monitor.entity.ResponseEntity;
import com.zju.als.monitor.guardian.DataCenter;
import com.zju.als.monitor.guardian.model.data.ECG;
import com.zju.als.monitor.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/3.
 */
@RestController
public class RestfulServer {
    private static Logger logger = LoggerFactory.getLogger(RestfulServer.class);
    @Resource
    private SyncService syncService;
    @Resource
    private DataCenter dataCenter;


    @RequestMapping("/reporter")
    public String reporter(){
        return "reporter";
    }

    @RequestMapping(value = "/sync")
    public String getSyncData(@RequestParam("surgery_no") String surgery_no, @RequestParam("time_stamp") long time_stamp){
        return syncService.getSyncData(surgery_no, time_stamp);
    }

    @RequestMapping(value = "/ecg")
    public ResponseEntity<ECG> ecg(@RequestParam("surgery_no") String surgery_no){
        ECG ecg = dataCenter.getECG(surgery_no);
        if (ecg == null){
            return new ResponseEntity<ECG>(ecg, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        else{
            return new ResponseEntity<ECG>(ecg, HttpStatus.OK.value());
        }
    }
}
