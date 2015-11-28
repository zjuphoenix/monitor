package com.zju.als.monitor.guardian.datafile;

import com.zju.als.monitor.config.FilePathConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by MCH on 2015/11/28.
 */
@Component
public class EcgDataFileFactory {
    @Resource
    private FilePathConfig filePathConfig;

    public EcgDataFile newInstance(String surgery_no){
        return new EcgDataFile(surgery_no, filePathConfig);
    }
}
