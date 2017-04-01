
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task_manager`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_manager`;
CREATE TABLE `ts_task_manager` (
  `MID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理模块id',
  `NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `LAST_UPDATE_TM` bigint(20) DEFAULT NULL COMMENT '最近存活时间',
  PRIMARY KEY (`MID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_manager
-- ----------------------------
