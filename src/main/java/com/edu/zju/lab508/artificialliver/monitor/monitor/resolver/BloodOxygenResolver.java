package com.edu.zju.lab508.artificialliver.monitor.monitor.resolver;

import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.BloodOxygen;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/11/5.
 */
@Component
public class BloodOxygenResolver implements DataResolver{
    @Override
    public Object resolve(byte[] data, int length) {
        BloodOxygen bloodOxygen = new BloodOxygen();
        bloodOxygen.pulserate = (data[3]&0xff << 8) + (data[4]&0xff);
        bloodOxygen.saturation = data[5];
        bloodOxygen.pulseintensity = data[6];
        System.arraycopy(data, 7, bloodOxygen.waveform, 0, 125);
        System.arraycopy(data, 132, bloodOxygen.oxygenmark, 0, 125);
        return bloodOxygen;
    }
}
