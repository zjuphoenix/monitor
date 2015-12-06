package com.zju.als.monitor.guardian;

import com.zju.als.monitor.guardian.model.data.BloodPressure;
import com.zju.als.monitor.guardian.model.data.ECG;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/11/7.
 */
public class DataCache {
    private LinkedBlockingQueue<ECG> ecgs;
    private LinkedBlockingQueue<BloodPressure> bloodPressures;

    public DataCache() {
        ecgs = new LinkedBlockingQueue<>(100);
        bloodPressures = new LinkedBlockingQueue<>(100);
    }

    public ECG getECG(){
        return ecgs.poll();
    }

    public void addECG(ECG ecg){
        if (!ecgs.offer(ecg)){
            try {
                ecgs.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ecgs.add(ecg);
        }
    }

    public BloodPressure getBloodPressure(){
        return bloodPressures.poll();
    }

    public void addBloodPressure(BloodPressure bloodPressure){
        if (!bloodPressures.add(bloodPressure)){
            try {
                bloodPressures.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bloodPressures.add(bloodPressure);
        }
    }
}
