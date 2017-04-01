

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task_history`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_history`;
CREATE TABLE `ts_task_history` (
  `htid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tid` bigint(20) DEFAULT NULL COMMENT '任务id',
  `MANAGER` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的Manager',
  `THREAD` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的线程',
  `START_TM` bigint(20) DEFAULT NULL COMMENT '运行时间',
  `END_TM` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `EXE_STATUS` char(255) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '运行结果，N-正常，E-异常',
  `MEMO` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运行备注',
  PRIMARY KEY (`htid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_history
-- ----------------------------
