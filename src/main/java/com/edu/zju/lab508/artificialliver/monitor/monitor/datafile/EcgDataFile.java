package com.edu.zju.lab508.artificialliver.monitor.monitor.datafile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/5.
 */
public class EcgDataFile implements DataFile{
    private Logger logger = LoggerFactory.getLogger(EcgDataFile.class);
    private MappedByteBuffer out;
    private String surgery_no;

    public EcgDataFile(String surgery_no) {
        this.surgery_no = surgery_no;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        File file = new File(surgery_no+"_ecg_"+sdf.format(new Date()));
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            // 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
            FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
            //注意，文件通道的可读可写要建立在文件流本身可读写的基础之上
            out = fc.map(FileChannel.MapMode.READ_WRITE, 0, 4*1024*1024);
        } catch (IOException e) {
            logger.error("io exception", e);
        }
    }

    @Override
    public void save(byte[] data) {
        out.put(data);
    }

    @Override
    public byte[] getCurrentData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = surgery_no+"_ecg_"+sdf.format(new Date());
        try {
            RandomAccessFile raf = new RandomAccessFile(date, "rw");
            long pointer = raf.getFilePointer();
            int one = 3513+10;
            raf.seek(((pointer-one)/one)*one+10);
            byte[] data = new byte[3513];
            raf.read(data);
            return data;
        } catch (IOException e) {
            logger.error("read ecg file data occur io exception");
        }
        return null;
    }

    @Override
    public void close() {

    }
}
