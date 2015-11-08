package com.zju.als.monitor.guardian.datafile;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface DataFile {
    void save(byte[] data);
    byte[] getCurrentData();
    void close();
}
