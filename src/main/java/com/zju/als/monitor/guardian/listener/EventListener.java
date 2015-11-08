package com.zju.als.monitor.guardian.listener;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface EventListener<T> {
    void onEvent(T event);
}
