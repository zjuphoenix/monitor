package com.edu.zju.lab508.artificialliver.monitor.monitor.model.command;

/**
 * Created by wuhaitao on 2015/5/16.
 */
public class Command {

    public static final byte[] STARTECG = new byte[]{1,0,8,3,0,0,2,0,0,0,0};

    public static final byte[] STOPECG = new byte[]{1,0,8,3,0,0,3,0,0,0,0};

    public static final byte[] STARTBloodPressure = new byte[]{3,0,8,3,0,0,2,0,0,0,0};

    public static final byte[] STOPBloodPressure = new byte[]{3,0,8,3,0,0,3,0,0,0,0};

    public static final byte[] STARTBloodOxygen = new byte[]{2,0,8,3,0,0,2,0,0,0,0};

    public static final byte[] STOPBloodOxygen = new byte[]{2,0,8,3,0,0,3,0,0,0,0};

    public static final byte[] PROTOCOLDESCRIPTION = new byte[]{0,0,3,0,0,0};
}
