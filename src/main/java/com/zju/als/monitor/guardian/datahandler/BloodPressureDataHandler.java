package com.zju.als.monitor.guardian.datahandler;

import com.zju.als.monitor.guardian.model.data.BloodPressure;
import com.zju.als.monitor.guardian.resolver.BloodPressureResolver;

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
