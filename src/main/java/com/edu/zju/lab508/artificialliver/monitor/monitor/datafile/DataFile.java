package com.edu.zju.lab508.artificialliver.monitor.monitor.datafile;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface DataFile {
    void init();
    void save(byte[] data);
    byte[] getCurrentData();
    void close();
}
