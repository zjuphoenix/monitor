package com.zju.als.monitor.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;
import com.zju.als.monitor.reporter.Reporter;
import com.zju.als.monitor.reporter.Scheme;
import com.zju.als.monitor.reporter.Surgeryinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
@Scope("prototype")
@RestController
public class ReporterServer {
    private static Logger logger = LoggerFactory.getLogger(ReporterServer.class);
    @Resource
    private Reporter reporter;
    @RequestMapping(value = "/report")
    public InputStream getReportForm(@RequestParam("operationInfo") String operationInfo,@RequestParam("schemes") String schemestr){
        Gson gson = new Gson();
        Surgeryinfo operation = gson.fromJson(operationInfo,Surgeryinfo.class);
        List<Scheme> schemes = gson.fromJson(schemestr, new TypeToken<List<Scheme>>() {
        }.getType());
        try {
            return reporter.getReporterPdf(operation, schemes);
        } catch (DocumentException e) {
            logger.error("DocumentException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return null;
    }

    @RequestMapping("/test")
    public String test(){
        return reporter.toString();
    }
}
