package com.edu.zju.lab508.artificialliver.monitor.monitor.listener;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface EventListener<T> {
    void onEvent(T event);
}
