package com.zju.als.monitor.guardian.resolver;

import com.zju.als.monitor.guardian.model.data.BloodPressure;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/5.
 */
@Component
public class BloodPressureResolver implements DataResolver{
    @Override
    public Object resolve(byte[] data, int length) {
        BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.systolicpressure = (data[3]&0xff << 8) + (data[4]&0xff);
        bloodPressure.diastolicpressure = (data[5]&0xff << 8) + (data[6]&0xff);
        bloodPressure.meanpressure = (data[7]&0xff << 8) + (data[8]&0xff);
        bloodPressure.pulserate = (data[9]&0xff << 8) + (data[10]&0xff);
        return bloodPressure;
    }
}
