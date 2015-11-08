package com.zju.als.monitor.guardian.datahandler;

import com.zju.als.monitor.guardian.model.data.BloodOxygen;
import com.zju.als.monitor.guardian.resolver.BloodOxygenResolver;

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
    public void handle(String surgery_no, byte[] data, int length) {
        BloodOxygen bloodOxygen = (BloodOxygen)bloodOxygenResolver.resolve(data, length);
    }
}
