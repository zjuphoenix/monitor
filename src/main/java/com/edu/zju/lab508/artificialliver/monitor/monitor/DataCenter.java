package com.edu.zju.lab508.artificialliver.monitor.monitor;

import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.BloodPressure;
import com.edu.zju.lab508.artificialliver.monitor.monitor.model.data.ECG;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class DataCenter {
    private LinkedBlockingQueue<ECG> ecgs;
    private LinkedBlockingQueue<BloodPressure> bloodPressures;

    public DataCenter() {
        this.ecgs = new LinkedBlockingQueue<>(1000);
        this.bloodPressures = new LinkedBlockingQueue<>(1000);
    }

    public ECG getECG(){
        return ecgs.poll();
    }

    public BloodPressure getBloodPressure(){
        return bloodPressures.poll();
    }

    public void addECG(ECG ecg){
        if (!ecgs.add(ecg)){
            try {
                ecgs.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ecgs.add(ecg);
        }

    }

    public void addBloodPressure(BloodPressure bloodPressure){
        bloodPressures.add(bloodPressure);
    }
}
