
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task`;
CREATE TABLE `ts_task` (
  `TID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TKEY` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一编号',
  `NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT '任务名',
  `CONF_TYPE` char(8) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务类型，T-指定时间运行，CV-指定间隔，指定条件执行',
  `CONF_START_TM` bigint(20) DEFAULT NULL COMMENT '指定的 定时执行时间，单位：时间戳',
  `CONF_EXE_TIMES` bigint(20) DEFAULT '1' COMMENT '总执行次数，-1 一直执行',
  `CONF_INTERVAL_UNIT` varchar(32) COLLATE utf8mb4_bin DEFAULT 'S' COMMENT '间隔单位，秒，天，Y-年',
  `CONF_INTERVAL` bigint(20) DEFAULT NULL COMMENT '间隔时间，单位秒',
  `STATUS_PROCESSOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `STATUS_LAST_EXE_TM` bigint(20) DEFAULT '0' COMMENT '状态：上次运行时间',
  `STATUS_RUNED_TIME` bigint(20) DEFAULT '0' COMMENT '状态：已经运行次数',
  `PROCESSING` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '是否 manager 正在处理，处理完毕后需要重置，并发用',
  `EXECUTING` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '是否正在执行 Y，N，任务执行期间设值',
  `SERVICE_NAME` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务类',
  `VALID` char(1) COLLATE utf8mb4_bin DEFAULT 'Y' COMMENT '是否有效，Y，N',
  PRIMARY KEY (`TID`),
  UNIQUE KEY `NAME_UNI` (`NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task
-- ----------------------------
INSERT INTO `ts_task` VALUES ('1', 't1', 't1', 'T', null, '1', 'S', null, null, '0', '0', 'Y', 'N', null, 'Y');
