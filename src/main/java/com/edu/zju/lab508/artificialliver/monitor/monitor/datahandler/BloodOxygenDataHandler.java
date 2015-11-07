package com.edu.zju.lab508.artificialliver.monitor.monitor.datahandler;

import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.BloodOxygen;
import com.edu.zju.lab508.artificialliver.monitor.monitor.resolver.BloodOxygenResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class BloodOxygenDataHandler implements DataHandler{
    @Resource
    private BloodOxygenResolver bloodOxygenResolver;
    @Override
    public void handle(byte[] data, int length) {
        BloodOxygen bloodOxygen = (BloodOxygen)bloodOxygenResolver.resolve(data, length);
    }
}
