package com.edu.zju.lab508.artificialliver.monitor.monitor;

import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.ECG;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class DataCenter {
    private Map<String, DataCache> dataCacheMap = new ConcurrentHashMap<>();

    public void createData(String surgery_no){
        DataCache dataCache = new DataCache();
        dataCacheMap.put(surgery_no, dataCache);
    }

    public void close(String surgery_no){
        dataCacheMap.remove(surgery_no);
    }

    public void addECG(String surgery_no,ECG ecg){
        if (dataCacheMap.containsKey(surgery_no)){
            dataCacheMap.get(surgery_no).addECG(ecg);
        }
    }

    public ECG getECG(String surgery_no){
        if (dataCacheMap.containsKey(surgery_no)){
            return dataCacheMap.get(surgery_no).getECG();
        }
        return null;
    }

}
