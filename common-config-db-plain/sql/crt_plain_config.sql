-- ----------------------------
-- Table structure for `tm_config`
-- ----------------------------
DROP TABLE IF EXISTS `tm_config`;
CREATE TABLE `tm_config` (
  `CFG_CODE` varchar(64) NOT NULL COMMENT '配置编码',
  `CFG_NAME` varchar(64) NOT NULL COMMENT '配置名称',
  `CFG_VALUE` varchar(128) NOT NULL COMMENT '配置值',
  `P_CFG_CODE` varchar(64) DEFAULT NULL COMMENT '父配置编码',
  `SEQ_NO` int(11) NOT NULL COMMENT '排序',
  `DESCRIPTION` varchar(128) DEFAULT NULL COMMENT '配置描述',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `EXT1` varchar(128) DEFAULT NULL,
  `EXT2` varchar(128) DEFAULT NULL,
  `EXT3` varchar(128) DEFAULT NULL,
  `EXT4` varchar(64) DEFAULT NULL,
  `EXT5` varchar(64) DEFAULT NULL,
  `R_CFG_CODE` varchar(64) DEFAULT NULL COMMENT '根配置编码',
  `CRT_USERID` varchar(40) DEFAULT NULL COMMENT '建立人ID',
  `CRT_USERNAME` varchar(32) DEFAULT NULL COMMENT '建立人姓名',
  `CRT_TIME` datetime DEFAULT NULL COMMENT '建立时间',
  `MD_USERID` varchar(40) DEFAULT NULL COMMENT '修改人ID',
  `MD_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `MD_USERNAME` varchar(32) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`CFG_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统选项配置 TM_CONFIG';


