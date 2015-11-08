package com.zju.als.monitor.artificialliver.dao;

import com.zju.als.monitor.artificialliver.domain.PressureData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */

public interface PressureMapper {
    @Select("SELECT * FROM pressure_data where surgery_no=#{surgery_no} order by time_stamp")
    List<PressureData> getPressureDatas(@Param("surgery_no")String surgery_no);

    @Select("SELECT * FROM pressure_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
    List<PressureData> getAfterPressureDatas(@Param("surgery_no")String surgery_no, @Param("time_stamp")long time_stamp);

    @Insert("INSERT INTO pressure_data values(#{machine_no},#{surgery_no},#{time_stamp},#{in_blood_pressure},#{plasma_inlet_pressure},#{arterial_pressure},#{venous_pressure},#{plasma_pressure},#{transmembrane_pressure})")
    void insert(PressureData pressureData);
}
