package com.zju.als.monitor.guardian;

import com.zju.als.monitor.guardian.listener.DataEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2015/11/7.
 */
@Component
public class MonitorCenter {
    @Autowired
    private DataEventListener dataEventListener;
    private Map<String,Monitor> monitors = new ConcurrentHashMap<>();
    public synchronized void createMonitor(String surgery_no, String host, int port) throws Exception{
        Monitor monitor = new Monitor(surgery_no, host, port, dataEventListener);
        monitors.put(surgery_no,monitor);
        monitor.init();
    }

    public void stopMonitor(String surgery_no) throws Exception{
        if (monitors.containsKey(surgery_no)){
            monitors.get(surgery_no).stop();
            monitors.remove(surgery_no);
        }
    }
}
