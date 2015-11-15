package com.zju.als.monitor.guardian.datafile;

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

    /**
     * 心电数据格式：
     * head 3字节：head[0]=1,head[1]=(byte)((3513&0xff00)>>8),head[2]=(byte)(3513&0xff);
     * datahead 3字节：datahead[0]=1,datahead[1]=(byte)(3510&0xff00)>>8,datahead[2]=(byte)(3510&0xff);
     * breathingrate 2字节：breathingrate[0]=(byte)((60&0xff00)>>8),breathingrate[1]=(byte)(60&0xff);
     * st 6字节： {0,0,0,0,0,0};
     * heartrate 2字节：heartrate[0]=(byte)((83&0xff00)>>8),heartrate[1]=(byte)(83&0xff);
     * ecg 3000字节：每个数据点2个字节，每个导联500数据点，一共3个导联;
     * flag 500字节;
     * data不包含head，从datahead开始，长度为3513字节
     * @param data
     */
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
