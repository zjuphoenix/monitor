package com.edu.zju.lab508.artificialliver.monitor.sync;

import com.edu.zju.lab508.artificialliver.monitor.dao.GuardianMapper;
import com.edu.zju.lab508.artificialliver.monitor.dao.PressureMapper;
import com.edu.zju.lab508.artificialliver.monitor.dao.PumpSpeedMapper;
import com.edu.zju.lab508.artificialliver.monitor.domain.GuardianData;
import com.edu.zju.lab508.artificialliver.monitor.domain.PressureData;
import com.edu.zju.lab508.artificialliver.monitor.domain.PumpSpeedData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class SyncService {
    @Autowired
    private PumpSpeedMapper pumpSpeedMapper;
    @Autowired
    private PressureMapper pressureMapper;
    @Autowired
    private GuardianMapper guardianMapper;

    public String getSyncData(String surgery_no, long time_stamp) {
        SyncResult syncResult = null;
        try {
            List<GuardianData> guardianDatas = guardianMapper.getAfterGuardianDatas(surgery_no, time_stamp);
            List<PressureData> pressureDatas = pressureMapper.getAfterPressureDatas(surgery_no, time_stamp);
            List<PumpSpeedData> pumpSpeedDatas = pumpSpeedMapper.getAfterPumpSpeedDatas(surgery_no, time_stamp);
            syncResult = new SyncResult(guardianDatas, pumpSpeedDatas, pressureDatas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = new Gson().toJson(syncResult);
        return str;
    }
}
