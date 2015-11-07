package com.edu.zju.lab508.artificialliver.monitor.reporter;

import org.jfree.data.time.Second;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/4.
 */
public class Scheme {
    public static final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public String solutionName;
    public Second startTime;

    public Scheme(String solutionName, String start) throws ParseException {
        Date date = dateFormat.parse(start);
        this.startTime = new Second(date);
        this.solutionName = solutionName;
    }
}
