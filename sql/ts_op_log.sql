
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_op_log`
-- ----------------------------
DROP TABLE IF EXISTS `ts_op_log`;
CREATE TABLE `ts_op_log` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `OP_TM` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `LOG_LV` char(4) DEFAULT 'I' COMMENT 'debug-D,info-I,warn-W,error-E',
  `OP_MODULE` varchar(128) DEFAULT '' COMMENT '操作模块',
  `OP_CHILD_MODUEL` varchar(128) DEFAULT NULL,
  `EMP_NUM` varchar(11) DEFAULT NULL COMMENT '用户id',
  `OP_TYPE` char(8) DEFAULT 'R' COMMENT '操作类型，CRT创建，DEL删除，UPD修改，GET查看，LOGIN 登录，LOGOUT 登出，DATAIN 导入，DATAOUT 导出，OT其他',
  `OP_PARAM` varchar(2000) DEFAULT '' COMMENT '入参',
  `OP_RES` varchar(4000) DEFAULT NULL COMMENT '出参',
  `OP_REF` varchar(1024) DEFAULT NULL COMMENT '附加信息',
  `OP_CONTENT` longtext COMMENT '操作内容，变化量，异常堆栈',
  `COST` bigint(20) DEFAULT NULL,
  `SERVICE_IP` bigint(11) DEFAULT '0' COMMENT '提供服务，机器ip ,ip2long',
  `OP_IP` bigint(11) DEFAULT '0' COMMENT '用户ip ，ip2long',
  `USER_NAME` varchar(128) DEFAULT '' COMMENT '用户名',
  `SOURCE` char(4) DEFAULT 'W' COMMENT '操作来源，W-web，A-android，S-ios,X-weixin',
  `OP_HOST_NAME` varchar(128) DEFAULT NULL,
  `USER_EXT_INFO` varchar(128) DEFAULT NULL,
  `EXT1` varchar(128) DEFAULT '',
  `EXT2` varchar(128) DEFAULT '',
  `EXT3` varchar(128) DEFAULT '',
  PRIMARY KEY (`LOG_ID`),
  KEY `idx_tm_operator_type` (`OP_TM`,`USER_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32244 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ----------------------------
-- Records of ts_op_log
-- ----------------------------
