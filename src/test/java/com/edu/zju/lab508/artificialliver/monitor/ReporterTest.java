package com.edu.zju.lab508.artificialliver.monitor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.*;

/**
 * Created by Administrator on 2015/11/13.
 */
public class ReporterTest {
    @Test
    public void test(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8888/test");
        httpget.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        try(CloseableHttpResponse response = httpclient.execute(httpget)) {
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            if (statusCode == HttpStatus.SC_OK){
                try {
                    File file=new File("E://Study/report.pdf");
                    file.createNewFile();
                    OutputStream os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.close();
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("error");
            }
        } catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
