package com.edu.zju.lab508.artificialliver.monitor.monitor.datahandler;

import com.edu.zju.lab508.artificialliver.monitor.monitor.DataCenter;
import com.edu.zju.lab508.artificialliver.monitor.monitor.datafile.EcgDataFile;
import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.ECG;
import com.edu.zju.lab508.artificialliver.monitor.monitor.resolver.EcgResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class EcgDataHandler implements DataHandler{

    @Resource
    private DataCenter dataCenter;
    @Resource
    private EcgResolver ecgResolver;
    @Override
    public void handle(byte[] data, int length) {
        ECG ecg = (ECG)ecgResolver.resolve(data, length);
        dataCenter.addECG(ecg);
    }
}
