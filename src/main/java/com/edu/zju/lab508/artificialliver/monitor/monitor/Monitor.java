package com.edu.zju.lab508.artificialliver.monitor.monitor;

import com.edu.zju.lab508.artificialliver.monitor.monitor.listener.DataEventListener;
import com.edu.zju.lab508.artificialliver.monitor.monitor.listener.DataFrameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/11/4.
 */
//@Component
public class Monitor {
    @Autowired
    private Environment env;
    @Resource
    private DataEventListener dataEventListener;

    private Logger logger = LoggerFactory.getLogger(Monitor.class);
    private Socket socket;
    private BufferedOutputStream bos;
    private Executor executor;

    public Monitor() {
        try {
            socket = new Socket(env.getProperty("monitor.url"),Integer.parseInt(env.getProperty("monitor.port")));
            logger.info("连接设备成功");
            bos = new BufferedOutputStream(socket.getOutputStream());
            executor = Executors.newSingleThreadExecutor();
        } catch (UnknownHostException e) {
            logger.error("host unknown:",e);
        } catch (IOException e) {
            logger.error("io exception:", e);
        }
    }

    @PostConstruct
    public void init(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                BufferedInputStream bis = null;
                try {
                    bis = new BufferedInputStream(socket.getInputStream());
                } catch (IOException e) {
                    logger.error("io exception!",e);
                    return;
                }
                byte[] head = new byte[3];
                int type;
                int length;
                while (true) {
                    try {
                        if (bis.available()>3){
                            bis.read(head, 0, head.length);
                            type = head[0]&0xff;
                            length = ((head[2]&0xff) << 8) + (head[1]&0xff);
                            byte[] data = new byte[length];
                            while(bis.available()<length){
                                try {
                                    Thread.currentThread().sleep(10);
                                } catch (InterruptedException e) {
                                    logger.error("monitor thread is interrupted!", e);
                                }
                            }
                            bis.read(data, 0, length);
                            DataFrameEvent dataFrameEvent = new DataFrameEvent(type, length, data);
                            dataEventListener.onEvent(dataFrameEvent);
                        }
                        else{
                            continue;
                        }
                    } catch (IOException e) {
                        logger.error("io exception!", e);
                        return;
                    }
                }
            }
        });
    }
}
