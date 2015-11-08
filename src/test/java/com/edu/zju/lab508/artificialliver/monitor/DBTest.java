package com.edu.zju.lab508.artificialliver.monitor;

import com.zju.als.monitor.MonitorApplication;
import com.zju.als.monitor.artificialliver.dao.ArtificialLiverMapper;
import com.zju.als.monitor.artificialliver.domain.PressureData;
import com.zju.als.monitor.guardian.dao.GuardianMapper;

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
    ArtificialLiverMapper artificialLiverMapper;
    @Test
    public void test(){
        //guardianMapper.insert(new GuardianData("1","1",new Date().getTime(),"75","116","73","97"));
        //System.out.println(guardianMapper.getAfterGuardianDatas("1",(long)20));
        //System.out.println(guardianMapper.getHeartRateDatas("1"));
    	artificialLiverMapper.insert(new PressureData("1","1",new Date().getTime(),"123","123","123","123","123","123"));
    }
}
