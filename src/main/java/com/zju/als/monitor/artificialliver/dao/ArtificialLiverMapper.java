package com.zju.als.monitor.artificialliver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.zju.als.monitor.artificialliver.domain.Cumulant;
import com.zju.als.monitor.artificialliver.domain.LiquidData;
import com.zju.als.monitor.artificialliver.domain.PressureData;
import com.zju.als.monitor.artificialliver.domain.PumpSpeedData;
public interface ArtificialLiverMapper {
	//pressure data
	 	@Select("SELECT * FROM pressure_data where surgery_no=#{surgery_no} order by time_stamp")
	    List<PressureData> getPressureDatas(@Param("surgery_no")String surgery_no);

	    @Select("SELECT * FROM pressure_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
	    List<PressureData> getAfterPressureDatas(@Param("surgery_no")String surgery_no, @Param("time_stamp")long time_stamp);

	    @Insert("INSERT INTO pressure_data values(#{machine_no},#{surgery_no},#{time_stamp},#{in_blood_pressure},#{plasma_inlet_pressure},#{arterial_pressure},#{venous_pressure},#{plasma_pressure},#{transmembrane_pressure})")
	    void insert(PressureData pressureData);
   //pump speed data
	    @Select("SELECT cumulative_time,blood_pump_t,separation_pump_t,dialysis_pump_t,tripe_pump_t,filtration_pump_t,circulating_pump_t,heparin_pump_t FROM pump_speed_data where surgery_no=#{surgery_no} order by time_stamp desc limit 1")
	    Cumulant getCumulant(@Param("surgery_no")String surgery_no);

	    @Select("SELECT time_stamp,blood_pump_t,tripe_pump_t,filtration_pump_t FROM pump_speed_data  where surgery_no=#{value} order by time_stamp")
	    List<LiquidData> getLiquidDatas(@Param("surgery_no")String surgery_no);

	    @Select("SELECT * FROM pump_speed_data where surgery_no=#{surgery_no} and time_stamp>#{time_stamp} order by time_stamp")
	    List<PumpSpeedData> getAfterPumpSpeedDatas(@Param("surgery_no")String surgery_no, @Param("time_stamp")long time_stamp);
}
