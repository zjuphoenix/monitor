package com.edu.zju.lab508.artificialliver.monitor.monitor.resolver;

import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.ECG;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/11/5.
 */
@Component
public class EcgResolver implements DataResolver{
    @Override
    public Object resolve(byte[] data, int length) {
        ECG ecg = new ECG();
        ecg.breathingrate = (data[3]&0xff << 8) + (data[4]&0xff);
        ecg.ST1 = (data[5]&0xff << 8) + (data[6]&0xff);
        ecg.ST2 = (data[7]&0xff << 8) + (data[8]&0xff);
        ecg.ST3 = (data[9]&0xff << 8) + (data[10]&0xff);
        ecg.heartrate = (data[11]&0xff << 8) + (data[12]&0xff);
        int k = 0;
        int i = 13;
        while (k < 500) {
            ecg.ecg[0][k++] = (short) ((data[i++]&0xff << 8) + (data[i++]&0xff));
        }
        k = 0;
        while (k < 500) {
            ecg.ecg[1][k++] = (short) ((data[i++]&0xff << 8) + (data[i++]&0xff));
        }
        k = 0;
        while (k < 500) {
            ecg.ecg[2][k++] = (short) ((data[i++]&0xff << 8) + (data[i++]&0xff));
        }
        k = 0;
        while (k<500) {
            ecg.flag[k++] = data[i++];
        }
        return ecg;
    }
}
