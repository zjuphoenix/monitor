package com.edu.zju.lab508.artificialliver.monitor;

import com.edu.zju.lab508.artificialliver.monitor.dao.GuardianMapper;
import com.edu.zju.lab508.artificialliver.monitor.dao.PressureMapper;
import com.edu.zju.lab508.artificialliver.monitor.dao.PumpSpeedMapper;
import com.edu.zju.lab508.artificialliver.monitor.domain.PressureData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
public class DBTest {
    @Autowired
    private GuardianMapper guardianMapper;
    @Autowired
    private PressureMapper pressureMapper;
    @Autowired
    private PumpSpeedMapper pumpSpeedMapper;
    @Test
    public void test(){
        //guardianMapper.insert(new GuardianData("1","1",new Date().getTime(),"75","116","73","97"));
        //System.out.println(guardianMapper.getAfterGuardianDatas("1",(long)20));
        //System.out.println(guardianMapper.getHeartRateDatas("1"));
        pressureMapper.insert(new PressureData("1","1",new Date().getTime(),"123","123","123","123","123","123"));
    }
}
