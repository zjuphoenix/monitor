package com.edu.zju.lab508.artificialliver.monitor.dao;

import com.edu.zju.lab508.artificialliver.monitor.domain.BloodPressureData;
import com.edu.zju.lab508.artificialliver.monitor.domain.GuardianData;
import com.edu.zju.lab508.artificialliver.monitor.domain.HeartRateData;
import com.edu.zju.lab508.artificialliver.monitor.sync.SyncObject;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface GuardianMapper {
    @Select("SELECT time_stamp,systolic_pressure,diastolic_pressure FROM guardian_data where surgery_no=#{surgery_no}# order by time_stamp")
    List<BloodPressureData> getBloodPressureDatas(String surgery_no);

    @Select("SELECT  time_stamp,heart_rate FROM guardian_data where surgery_no=#{surgery_no}# order by time_stamp")
    List<HeartRateData> getHeartRateDatas(String surgery_no);

    @Select("SELECT  *  FROM guardian_data where surgery_no=#{surgery_no}# and time_stamp>#{time_stamp}# order by time_stamp")
    List<GuardianData> getAfterGuardianDatas(SyncObject syncObject);
}
