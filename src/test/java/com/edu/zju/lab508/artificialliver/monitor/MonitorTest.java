package com.edu.zju.lab508.artificialliver.monitor;

import com.zju.als.monitor.MonitorApplication;
import com.zju.als.monitor.guardian.Monitor;
import com.zju.als.monitor.guardian.listener.DataEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Administrator on 2015/11/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
public class MonitorTest {
    @Autowired
    private DataEventListener dataEventListener;
    @Test
    public void test(){
        try {
            Monitor monitor = new Monitor("123","127.0.0.1",60129,dataEventListener);
            monitor.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
