package com.edu.zju.lab508.artificialliver.monitor.dao;

import com.edu.zju.lab508.artificialliver.monitor.domain.BloodPressureData;
import com.edu.zju.lab508.artificialliver.monitor.domain.GuardianData;
import com.edu.zju.lab508.artificialliver.monitor.domain.HeartRateData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface GuardianMapper {
    @Select("SELECT time_stamp,systolic_pressure,diastolic_pressure FROM guardian_data where surgery_no=#{surgery_no} order by time_stamp")
    List<BloodPressureData> getBloodPressureDatas(@Param("surgery_no")String surgery_no);

    @Select("SELECT time_stamp,heart_rate FROM guardian_data where surgery_no=#{surgery_no} order by time_stamp")
    List<HeartRateData> getHeartRateDatas(@Param("surgery_no")String surgery_no);

    @Select("SELECT dev_no,surgery_no,time_stamp,heart_rate,systolic_pressure,diastolic_pressure,blood_oxygen FROM guardian_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
    List<GuardianData> getAfterGuardianDatas(@Param("surgery_no")String surgery_no, @Param("time_stamp")long time_stamp);

    @Insert("INSERT INTO guardian_data values(#{dev_no},#{surgery_no},#{time_stamp},#{heart_rate},#{systolic_pressure},#{diastolic_pressure},#{blood_oxygen})")
    void insert(GuardianData guardianData);
}
