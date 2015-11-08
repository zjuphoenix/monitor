package com.zju.als.monitor.sync;

import com.google.gson.Gson;
import com.zju.als.monitor.artificialliver.dao.ArtificialLiverMapper;
import com.zju.als.monitor.artificialliver.domain.PressureData;
import com.zju.als.monitor.artificialliver.domain.PumpSpeedData;
import com.zju.als.monitor.guardian.dao.GuardianMapper;
import com.zju.als.monitor.guardian.domain.GuardianData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class SyncService {
    @Resource
    private ArtificialLiverMapper artificialLiverMapper;
    @Resource
    private GuardianMapper guardianMapper;

    public String getSyncData(String surgery_no, long time_stamp) {
        SyncResult syncResult = null;
        try {
            List<GuardianData> guardianDatas = guardianMapper.getAfterGuardianDatas(surgery_no, time_stamp);
            List<PressureData> pressureDatas = artificialLiverMapper.getAfterPressureDatas(surgery_no, time_stamp);
            List<PumpSpeedData> pumpSpeedDatas = artificialLiverMapper.getAfterPumpSpeedDatas(surgery_no, time_stamp);
            syncResult = new SyncResult(guardianDatas, pumpSpeedDatas, pressureDatas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = new Gson().toJson(syncResult);
        return str;
    }
}
