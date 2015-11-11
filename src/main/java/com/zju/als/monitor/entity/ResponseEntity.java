package com.zju.als.monitor.entity;

/**
 * Created by Administrator on 2015/11/11.
 */
public class ResponseEntity<T> {
    private int code;
    private T body;

    public ResponseEntity(T body, int code) {
        this.body = body;
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
