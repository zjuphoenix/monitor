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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import org.apache.commons.io.FileUtils;

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
    public ResponseEntity<byte[]> getReportForm(@RequestParam("operationInfo") String operationInfo,@RequestParam("schemes") String schemestr){
        Gson gson = new Gson();
        Surgeryinfo operation = gson.fromJson(operationInfo,Surgeryinfo.class);
        List<Scheme> schemes = gson.fromJson(schemestr, new TypeToken<List<Scheme>>() {
        }.getType());
        try {
            File file = reporter.getReporterPdf(operation, schemes);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
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
    public ResponseEntity<byte[]> test() throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File("E://repo/spring-boot-reference-guide-zh.pdf")),
                headers, HttpStatus.OK);
    }
}
