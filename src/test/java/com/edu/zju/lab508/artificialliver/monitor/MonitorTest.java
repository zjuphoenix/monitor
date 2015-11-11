package com.edu.zju.lab508.artificialliver.monitor;

import com.zju.als.monitor.MonitorApplication;
import com.zju.als.monitor.guardian.Monitor;
import com.zju.als.monitor.guardian.listener.DataEventListener;
import com.zju.als.monitor.guardian.listener.DataFrameEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Administrator on 2015/11/8.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MonitorApplication.class)
public class MonitorTest {
    /*@Autowired
    private DataEventListener dataEventListener;*/
    @Test
    public void test(){
        try {
            Socket socket = new Socket("10.13.81.181",60129);
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(socket.getInputStream());
            } catch (IOException e) {
                return;
            }
            byte[] head = new byte[3];
            int type;
            int length;
            int count = 0;
            while (count<10) {
                try {
                    if (bis.available() > 3) {
                        bis.read(head, 0, head.length);
                        type = head[0] & 0xff;
                        length = ((head[1] & 0xff) << 8) + (head[2] & 0xff);
                        System.out.println("data length:"+length);
                        byte[] data = new byte[length];
                        while (bis.available() < length) {
                            try {
                                Thread.currentThread().sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        bis.read(data, 0, length);
                        for (int i = 0; i < length; i++) {
                            System.out.print(data[i]+",");
                        }
                        System.out.println();
                        count++;
                    } else {
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            Monitor monitor = new Monitor("123","127.0.0.1",60129,dataEventListener);
            monitor.init();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
