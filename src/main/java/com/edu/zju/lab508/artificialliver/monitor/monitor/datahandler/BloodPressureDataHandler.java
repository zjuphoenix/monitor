package com.edu.zju.lab508.artificialliver.monitor.monitor.datahandler;

import com.edu.zju.lab508.artificialliver.monitor.monitor.DataCenter;
import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.BloodPressure;
import com.edu.zju.lab508.artificialliver.monitor.monitor.resolver.BloodPressureResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class BloodPressureDataHandler implements DataHandler {
    @Resource
    private DataCenter dataCenter;
    @Resource
    private BloodPressureResolver bloodPressureResolver;
    @Override
    public void handle(byte[] data, int length) {
        BloodPressure bloodPressure = (BloodPressure)bloodPressureResolver.resolve(data, length);
        dataCenter.addBloodPressure(bloodPressure);
    }
}
