package com.edu.zju.lab508.artificialliver.monitor.monitor.datahandler;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface DataHandler {
    void handle(byte[] data, int length);
}
