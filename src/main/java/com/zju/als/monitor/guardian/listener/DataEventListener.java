package com.zju.als.monitor.guardian.listener;

import com.zju.als.monitor.guardian.datahandler.BloodOxygenDataHandler;
import com.zju.als.monitor.guardian.datahandler.BloodPressureDataHandler;
import com.zju.als.monitor.guardian.datahandler.EcgDataHandler;
import com.zju.als.monitor.guardian.model.command.CommandResult;
import com.zju.als.monitor.guardian.model.data.BloodKetone;
import com.zju.als.monitor.guardian.model.data.BloodSugar;
import com.zju.als.monitor.guardian.model.data.ProtocolDescription;
import com.zju.als.monitor.guardian.model.datastatus.*;
import com.zju.als.monitor.guardian.model.description.ModuleDescriptionInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/11/4.
 */
@Component
public class DataEventListener implements EventListener<DataFrameEvent>{

    private Logger logger = LoggerFactory.getLogger(DataEventListener.class);
    @Resource
    private EcgDataHandler ecgDataHandler;
    @Resource
    private BloodPressureDataHandler bloodPressureDataHandler;
    @Resource
    private BloodOxygenDataHandler bloodOxygenDataHandler;

    @Override
    public void onEvent(DataFrameEvent dataFrameEvent) {
        byte[] data;
        int modelType;
        int length = 0;
        switch (dataFrameEvent.getType()) {
            case 0:// 协议描述
                data = dataFrameEvent.getData();
                logger.info("协议描述         " + "长度:" + dataFrameEvent.getLength());
                ProtocolDescription protocolDescription = new ProtocolDescription();
                protocolDescription.result = data[0];// 结果
                protocolDescription.code = data[1];// 编码方式
                protocolDescription.version = data[2];// 协议版本
                protocolDescription.productID = data[3];// 产品号
                protocolDescription.deviceID = data[4];// 设备号
                protocolDescription.reservedbit = (char) ((data[5]&0xff << 8) + (data[6]&0xff));// 保留位

                if(dataFrameEvent.getLength()!=7){
                    logger.info("协议描述异常");
                }
                break;
            case 1:// 心电模块
                data = dataFrameEvent.getData();
                modelType = data[0]&0xff;
                length = (data[1]&0xff << 8) + (data[2]&0xff);
                if (modelType == 0) {// 模块描述
                    ModuleDescriptionInfo info = new ModuleDescriptionInfo();
                    info.protocolversion = data[3];// 协议版本
                    info.reservedbit = data[4];// 保留位
                    info.supportcommand = data[5];// 支持的命令
                    info.productID = data[6];// 模块产品号
                    info.reservedbit2 = (char) ((data[7]&0xff << 8) + (data[8]&0xff));// 保留位
                    logger.info("心电模块描述         " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 1) {// 模块数据
                    ecgDataHandler.handle(dataFrameEvent.getSurgery_no(), data, length);
                    logger.info("心电模块数据        " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 2) {// 模块状态
                    ECGState ecgState = new ECGState();
                    ecgState.wirestate = data[3];
                    ecgState.overloadinfo = data[4];
                    logger.info("心电模块状态          " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 3) {// 模块命令
                    CommandResult commandResult = new CommandResult();
                    commandResult.command = data[3];// 命令
                    commandResult.length = (char) ((data[4]&0xff << 8) + (data[5]&0xff));// 长度
                    commandResult.response = data[6];// 响应代码，返回非0为请求错误
                    commandResult.reservedbit = data[7];// 保留位
                    commandResult.data = Arrays.copyOfRange(data, 8,
                            8 + commandResult.length);
                    logger.info("心电模块命令    " + "长度:" + dataFrameEvent.getLength());
                }
                break;
            case 2:// 血糖模块
                data = dataFrameEvent.getData();
                modelType = data[0]&0xff;
                length = (data[1]&0xff << 8) + (data[2]&0xff);
                if (modelType == 0) {// 模块描述
                    ModuleDescriptionInfo info = new ModuleDescriptionInfo();
                    info.protocolversion = data[3];// 协议版本
                    info.reservedbit = data[4];// 保留位
                    info.supportcommand = data[5];// 支持的命令
                    info.productID = data[6];// 模块产品号
                    info.reservedbit2 = (char) ((data[7]&0xff << 8) + (data[8]&0xff));// 保留位
                    logger.info("血糖模块描述    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 1) {// 模块数据
                    BloodSugar bloodSugar = new BloodSugar();
                    bloodSugar.bloodsugar = (data[3]&0xff << 24) | (data[4]&0xff << 16)
                            | (data[5]&0xff << 8) | data[6]&0xff;
                    logger.info("血糖模块数据    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 2) {// 模块状态
                    BloodSugarState bloodSugarState = new BloodSugarState();
                    bloodSugarState.state = data[3];
                    logger.info("血糖模块状态    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 3) {// 模块命令
                    CommandResult commandResult = new CommandResult();
                    commandResult.command = data[3];// 命令
                    commandResult.length = (char) ((data[4]&0xff << 8) | (data[5]&0xff));// 长度
                    commandResult.response = data[6];// 响应代码，返回非0为请求错误
                    commandResult.reservedbit = data[7];// 保留位
                    commandResult.data = Arrays.copyOfRange(data, 8,
                            8 + commandResult.length);
                    logger.info("血糖模块命令     " + "长度:" + dataFrameEvent.getLength());
                }

                break;
            case 3:// 血压模块
                data = dataFrameEvent.getData();
                modelType = data[0]&0xff;
                length = (data[1]&0xff << 8) + (data[2]&0xff);
                if (modelType == 0) {// 模块描述
                    ModuleDescriptionInfo info = new ModuleDescriptionInfo();
                    info.protocolversion = data[3];// 协议版本
                    info.reservedbit = data[4];// 保留位
                    info.supportcommand = data[5];// 支持的命令
                    info.productID = data[6];// 模块产品号
                    info.reservedbit2 = (char) ((data[7]&0xff << 8) + (data[8]&0xff));// 保留位
                    logger.info("血压模块描述    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 1) {// 模块数据
                    bloodPressureDataHandler.handle(dataFrameEvent.getSurgery_no(), data, length);
                    logger.info("血压模块数据    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 2) {// 模块状态
                    BloodPressureState bloodPressureState = new BloodPressureState();
                    bloodPressureState.cuffpressure = (char) ((data[3]&0xff << 8) + (data[4]&0xff));
                    bloodPressureState.state = data[5];
                    bloodPressureState.type = data[6];
                    bloodPressureState.error = data[7];
                    logger.info("血压模块状态    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 3) {// 模块命令
                    CommandResult commandResult = new CommandResult();
                    commandResult.command = data[3];// 命令
                    commandResult.length = (char) ((data[4]&0xff << 8) + (data[5]&0xff));// 长度
                    commandResult.response = data[6];// 响应代码，返回非0为请求错误
                    commandResult.reservedbit = data[7];// 保留位
                    commandResult.data = Arrays.copyOfRange(data, 8,
                            8 + commandResult.length);
                    logger.info("血压模块命令    " + "长度:" + dataFrameEvent.getLength());
                }
                break;
            case 4:// 血氧模块
                data = dataFrameEvent.getData();
                modelType = data[0]&0xff;
                length = (data[1]&0xff << 8) + (data[2]&0xff);
                if (modelType == 0) {// 模块描述
                    ModuleDescriptionInfo info = new ModuleDescriptionInfo();
                    info.protocolversion = data[3];// 协议版本
                    info.reservedbit = data[4];// 保留位
                    info.supportcommand = data[5];// 支持的命令
                    info.productID = data[6];// 模块产品号
                    info.reservedbit2 = (char) ((data[7]&0xff << 8) + (data[8]&0xff));// 保留位
                    logger.info("血氧模块描述    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 1) {// 模块数据
                    bloodOxygenDataHandler.handle(dataFrameEvent.getSurgery_no(), data, length);
                    logger.info("血氧模块数据    "+"长度:"+dataFrameEvent.getLength());
                } else if (modelType == 2) {// 模块状态
                    BloodOxygenState bloodOxygenState = new BloodOxygenState();
                    bloodOxygenState.state = data[3];
                    logger.info("血氧模块状态    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 3) {// 模块命令
                    CommandResult commandResult = new CommandResult();
                    commandResult.command = data[3];// 命令
                    commandResult.length = (char) ((data[4]&0xff << 8) + (data[5]&0xff));// 长度
                    commandResult.response = data[6];// 响应代码，返回非0为请求错误
                    commandResult.reservedbit = data[7];// 保留位
                    commandResult.data = Arrays.copyOfRange(data, 8,
                            8 + commandResult.length);
                    logger.info("血氧模块命令    " + "长度:" + dataFrameEvent.getLength());
                }
                break;
            case 5:// 血酮模块
                data = dataFrameEvent.getData();
                modelType = data[0]&0xff;
                length = (data[1]&0xff << 8) | (data[2]&0xff);
                if (modelType == 0) {// 模块描述
                    ModuleDescriptionInfo info = new ModuleDescriptionInfo();
                    info.protocolversion = data[3];// 协议版本
                    info.reservedbit = data[4];// 保留位
                    info.supportcommand = data[5];// 支持的命令
                    info.productID = data[6];// 模块产品号
                    info.reservedbit2 = (char) ((data[7]&0xff << 8) | (data[8]&0xff));// 保留位
                    logger.info("血酮模块描述    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 1) {// 模块数据
                    BloodKetone bloodKetone = new BloodKetone();
                    bloodKetone.bloodketone = (data[3]&0xff << 24) | (data[4]&0xff << 16)
                            | (data[5]&0xff << 8) | (data[6]&0xff);
                    logger.info("血酮模块数据    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 2) {// 模块状态
                    BloodKetoneState bloodKetoneState = new BloodKetoneState();
                    bloodKetoneState.state = data[3];
                    logger.info("血酮模块状态    " + "长度:" + dataFrameEvent.getLength());
                } else if (modelType == 3) {// 模块命令
                    CommandResult commandResult = new CommandResult();
                    commandResult.command = data[3];// 命令
                    commandResult.length = (char) ((data[4]&0xff << 8) | (data[5]&0xff));// 长度
                    commandResult.response = data[6];// 响应代码，返回非0为请求错误
                    commandResult.reservedbit = data[7];// 保留位
                    commandResult.data = Arrays.copyOfRange(data, 8,
                            8 + commandResult.length);
                    logger.info("血酮模块命令     " + "长度:" + dataFrameEvent.getLength());
                }
                break;
            default:
                logger.info("数据帧类型异常");
                break;
        }
    }
}
