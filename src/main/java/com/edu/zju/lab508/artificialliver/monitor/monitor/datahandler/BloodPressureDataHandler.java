package com.edu.zju.lab508.artificialliver.monitor.monitor.datahandler;

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
    private BloodPressureResolver bloodPressureResolver;
    @Override
    public void handle(String surgery_no, byte[] data, int length) {
        BloodPressure bloodPressure = (BloodPressure)bloodPressureResolver.resolve(data, length);

    }
}
