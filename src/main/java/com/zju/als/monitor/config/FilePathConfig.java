package com.zju.als.monitor.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by MCH on 2015/11/28.
 */
@Component
public class FilePathConfig {
    private String ecgFilePath;

    public FilePathConfig(){}

    public void readXMLFile(){
        SAXReader reader = new SAXReader();
        File file = new File("/root/HealthMonitor/config/config.xml");
        System.out.println(file.getAbsolutePath());
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        ecgFilePath = childElements.get(0).attributeValue("path");
    }

    public String getEcgFilePath() {
        return ecgFilePath;
    }
}
