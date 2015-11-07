package com.edu.zju.lab508.artificialliver.monitor.sync;

import com.edu.zju.lab508.artificialliver.monitor.domain.GuardianData;
import com.edu.zju.lab508.artificialliver.monitor.domain.PressureData;
import com.edu.zju.lab508.artificialliver.monitor.domain.PumpSpeedData;

import java.util.List;

public class SyncResult {
	List<GuardianData> guardian_datas;
	List<PumpSpeedData> pump_speed_datas;
	List<PressureData> pressure_datas;

	public SyncResult(List<GuardianData> guardian_datas,
			List<PumpSpeedData> pump_speed_datas,
			List<PressureData> pressure_datas) {
		this.guardian_datas=guardian_datas;
		this.pump_speed_datas=pump_speed_datas;
		this.pressure_datas=pressure_datas;
	}
}
