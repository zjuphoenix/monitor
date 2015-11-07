USE artificial_liver;
DROP TABLE IF EXISTS 'pressure_data';
create table pressure_data (
    'machine_no' varchar(16) NOT NULL  COMMENT '人工肝机器编号',
    'surgery_no' varchar(16) NOT NULL  COMMENT '手术号',
    'time_stamp' LONG NOT NULL  COMMENT '时间戳',
    'in_blood_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '采血压',
    'plasma_inlet_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '血浆入口压力',
    'arterial_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '动脉压',
    'venous_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '静脉压',
    'plasma_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '血浆压',
    'transmembrane_pressure' varchar(32) DEFAULT '0' COMMENT '跨膜压',
    PRIMARY KEY (surgery_no,time_stamp)
)ENGINE=innodb DEFAULT CHARSET=utf8;
CREATE INDEX pressure_data_machine_no ON pressure_data (machine_no);

DROP TABLE IF EXISTS 'pump_speed_data';
create table pump_speed_data (
    'machine_no' varchar(16) NOT NULL  COMMENT '人工肝机器编号',
    'surgery_no' varchar(16) NOT NULL  COMMENT '手术号',
    'time_stamp' LONG NOT NULL  COMMENT '时间戳',
    'cumulative_time' varchar(32) NOT NULL DEFAULT '0' COMMENT '累计时间',
    'blood_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '血泵',
    'separation_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '分类泵',
    'dialysis_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '透析液泵',
    'tripe_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '补液泵',
    'filtration_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '滤过泵',
    'circulating_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '循环泵',
    'heparin_pump' varchar(32) NOT NULL DEFAULT '0' COMMENT '肝素泵',
    'warmer' varchar(32) NOT NULL DEFAULT '0' COMMENT '加温器',
    'blood_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '血液循环量(累计)',
    'separation_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '分离泵(累计)',
    'dialysis_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '透析液泵(累计)',
    'tripe_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '补液泵(累计)',
    'filtration_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '滤过泵(累计)',
    'circulating_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '循环泵(累计)',
    'heparin_pump_t' varchar(32) NOT NULL DEFAULT '0' COMMENT '肝素泵(累计)',
    PRIMARY KEY (surgery_no,time_stamp)
)ENGINE=innodb DEFAULT CHARSET=utf8;
CREATE INDEX pump_speed_data_machine_no ON pump_speed_data (machine_no);

DROP TABLE IF EXISTS 'guardian_data';
create table guardian_data(
    'dev_no' varchar(16) NOT NULL  COMMENT '心电设备编号',
    'surgery_no' varchar(16) NOT NULL  COMMENT '手术号',
    'time_stamp' LONG NOT NULL  COMMENT '时间戳',
    'heart_rate' varchar(32) NOT NULL DEFAULT '0' COMMENT '心率',
    'systolic_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '收缩压',
    'diastolic_pressure' varchar(32) NOT NULL DEFAULT '0' COMMENT '舒张压',
    'blood_oxygen' varchar(32) NOT NULL DEFAULT '0' COMMENT '血氧',
    PRIMARY KEY (surgery_no,time_stamp)
);