package com.zju.als.monitor.guardian.datahandler;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface DataHandler {
    void handle(String surgery_no, byte[] data, int length);
}
