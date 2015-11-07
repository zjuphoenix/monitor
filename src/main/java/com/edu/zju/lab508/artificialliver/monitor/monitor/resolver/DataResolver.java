package com.edu.zju.lab508.artificialliver.monitor.monitor.resolver;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface DataResolver {
    Object resolve(byte[] data, int length);
}
