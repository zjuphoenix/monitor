package com.zju.als.monitor.guardian.datafile;

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

    public void writeEcg(String surgery_no, byte[] data){
        if (ecgDataFileMap.containsKey(surgery_no)) {
            ecgDataFileMap.get(surgery_no).save(data);
        }
    }
}
