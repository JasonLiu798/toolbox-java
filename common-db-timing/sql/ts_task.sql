/*
Navicat MySQL Data Transfer

Source Server Version : 50621
Source Host           : 10.202.125.245:3306
Source Database       : tk

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-10-13 18:56:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task_history`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_history`;
CREATE TABLE `ts_task_history` (
  `HTID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TID` bigint(20) DEFAULT NULL COMMENT '任务id',
  `PROCESSOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的进程',
  `THREAD` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的线程',
  `START_TM` bigint(20) DEFAULT NULL COMMENT '运行时间',
  `END_TM` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `EXE_STATUS` char(255) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '运行结果，N-正常，E-异常',
  `EXE_CNT` bigint(20) DEFAULT NULL COMMENT '运行次数',
  `MEMO` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运行备注',
  PRIMARY KEY (`HTID`)
) ENGINE=InnoDB AUTO_INCREMENT=279767 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_history
-- ----------------------------

-- ----------------------------
-- Table structure for `ts_task_manager`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_manager`;
CREATE TABLE `ts_task_manager` (
  `MID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理模块id',
  `NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `ALIVE_TM` bigint(20) DEFAULT NULL COMMENT '存活时间',
  `LAST_UPDATE_TM` bigint(20) DEFAULT NULL COMMENT '最近存活时间',
  PRIMARY KEY (`MID`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `ts_task_runned`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_runned`;
CREATE TABLE `ts_task_runned` (
  `TID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TKEY` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一编号',
  `TSERVICE` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务类',
  `PARAM` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONF_TYPE` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务类型',
  `CONF_CRON_EXPRESSION` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'cron表达式，类型为cron用',
  `CONF_DELAY_TM` bigint(20) DEFAULT NULL COMMENT '延迟执行时间，单位毫秒',
  `CONF_INTERVAL_TM` bigint(20) DEFAULT NULL COMMENT '间隔时间，单位毫秒',
  `CONF_EXE_TIMES` bigint(20) DEFAULT '1' COMMENT '最多执行次数，-1，永久执行（建议用cron）',
  `PROCESSOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `TMUTEX` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '锁',
  `MUTEX_TM` bigint(20) DEFAULT '0' COMMENT '加锁时间',
  `TSTATUS` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '状态，F-初始化，I-初始化，P-等待执行，E-执行中，N-结束',
  `ALIVE_TM` bigint(20) DEFAULT '0',
  `VALID` char(1) COLLATE utf8mb4_bin DEFAULT 'Y' COMMENT '是否有效，Y，N',
  `CRT_TM` bigint(20) DEFAULT '0' COMMENT '创建时间',
  `FIRST_START_TM` bigint(20) DEFAULT NULL,
  `FIRST_END_TM` bigint(20) DEFAULT NULL,
  `LAST_START_TM` bigint(20) DEFAULT NULL,
  `LAST_END_TM` bigint(20) DEFAULT NULL,
  `RUN_CNT` bigint(20) DEFAULT NULL COMMENT '运行次数',
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_runned
-- ----------------------------

-- ----------------------------
-- Table structure for `ts_task_v1`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_v1`;
CREATE TABLE `ts_task_v1` (
  `TID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TKEY` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一编号',
  `TASK_TYPE` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务类型',
  `DATAS` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置',
  `TMUTEX` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '锁,Y-有锁，N-没锁',
  `MUTEX_TM` bigint(20) DEFAULT '0' COMMENT '加锁时间',
  `LAST_EXECUTOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上次执行 进程',
  `LAST_EXECUTE_TM` bigint(20) DEFAULT '0' COMMENT '上次执行时间',
  `TSTATUS` char(1) COLLATE utf8mb4_bin DEFAULT 'F' COMMENT '状态，F-初始化，I-初始化，P-等待执行，E-执行中，D-结束',
  `VALID` char(1) COLLATE utf8mb4_bin DEFAULT 'Y' COMMENT '是否有效，Y，N',
  `CRT_TM` bigint(20) DEFAULT NULL COMMENT '创建时间，并发插入用',
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_v1
-- ----------------------------
