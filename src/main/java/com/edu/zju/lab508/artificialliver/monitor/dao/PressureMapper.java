package com.edu.zju.lab508.artificialliver.monitor.dao;

import com.edu.zju.lab508.artificialliver.monitor.domain.PressureData;
import com.edu.zju.lab508.artificialliver.monitor.sync.SyncObject;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface PressureMapper {
    @Select("SELECT time_stamp,in_blood_pressure,plasma_inlet_pressure,arterial_pressure,venous_pressure,plasma_pressure,transmembrane_pressure FROM pressure_data where surgery_no=#{surgery_no}# order by time_stamp")
    List<PressureData> getPressureDatas(String surgery_no);

    @Select("SELECT * FROM pressure_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
    List<PressureData> getAfterPressureDatas(SyncObject syncObject);
}
