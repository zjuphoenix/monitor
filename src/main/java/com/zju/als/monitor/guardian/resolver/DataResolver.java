package com.zju.als.monitor.guardian.resolver;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface DataResolver {
    Object resolve(byte[] data, int length);
}
