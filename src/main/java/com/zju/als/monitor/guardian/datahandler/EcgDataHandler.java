package com.zju.als.monitor.guardian.datahandler;

import com.zju.als.monitor.guardian.DataCenter;
import com.zju.als.monitor.guardian.datafile.DataFileCenter;
import com.zju.als.monitor.guardian.datafile.EcgDataFile;
import com.zju.als.monitor.guardian.model.data.ECG;
import com.zju.als.monitor.guardian.resolver.EcgResolver;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class EcgDataHandler implements DataHandler{

    @Resource
    private DataCenter dataCenter;
    @Resource
    private DataFileCenter dataFileCenter;
    @Resource
    private EcgResolver ecgResolver;
    @Override
    public void handle(String surgery_no, byte[] data, int length) {
        ECG ecg = (ECG)ecgResolver.resolve(data, length);
        System.out.println(ecg);
        dataCenter.addECG(surgery_no,ecg);
        dataFileCenter.writeEcg(surgery_no, data);
    }
}
