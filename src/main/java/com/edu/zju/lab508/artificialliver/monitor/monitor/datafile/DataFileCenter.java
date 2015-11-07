package com.edu.zju.lab508.artificialliver.monitor.monitor.datafile;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2015/11/7.
 */
@Component
public class DataFileCenter {
    Map<String,EcgDataFile> ecgDataFileMap = new ConcurrentHashMap<>();
    public void createEcgDataFile(String surgery_no){
        EcgDataFile ecgDataFile = new EcgDataFile(surgery_no);
        ecgDataFileMap.put(surgery_no, ecgDataFile);
    }

    public void stopEcgDataFile(String surgery_no){
        ecgDataFileMap.remove(surgery_no);
    }
}
