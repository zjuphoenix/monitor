package com.zju.als.monitor.artificialliver.dao;

import com.zju.als.monitor.artificialliver.domain.Cumulant;
import com.zju.als.monitor.artificialliver.domain.LiquidData;
import com.zju.als.monitor.artificialliver.domain.PumpSpeedData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */

public interface PumpSpeedMapper {
    @Select("SELECT cumulative_time,blood_pump_t,separation_pump_t,dialysis_pump_t,tripe_pump_t,filtration_pump_t,circulating_pump_t,heparin_pump_t FROM pump_speed_data where surgery_no=#{surgery_no} order by time_stamp desc limit 1")
    Cumulant getCumulant(@Param("surgery_no")String surgery_no);

    @Select("SELECT time_stamp,blood_pump_t,tripe_pump_t,filtration_pump_t FROM pump_speed_data  where surgery_no=#{value} order by time_stamp")
    List<LiquidData> getLiquidDatas(@Param("surgery_no")String surgery_no);

    @Select("SELECT * FROM pump_speed_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
    List<PumpSpeedData> getAfterPumpSpeedDatas(@Param("surgery_no")String surgery_no, @Param("time_stamp")long time_stamp);
}
