package com.atjl.eg;


import com.atjl.dbservice.api.RawDataValidator;
import com.atjl.dbservice.api.TgtDataNeedUpdateChecker;
import com.atjl.dbservice.api.domain.DbTableTransferConfig;
import com.atjl.dbservice.service.DataTransferService;
import com.atjl.dbservice.util.DataFilterUtil;
import com.atjl.retry.api.GetOptionService;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(EgConstant.SERVICE)
public class TransAreaMonitorService extends DataTransferService implements GetOptionService {

    @Test
    public void testConfig() {
        TransAreaMonitorService s = new TransAreaMonitorService();
        DbTableTransferConfig c = s.getTransConfig();
        System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(c)));
    }

    @Override
    public DbTableTransferConfig getTransConfig() {
        DbTableTransferConfig config = new DbTableTransferConfig();

        //对比值
//        config.setNoUpdateCheckMapping(CollectionUtil.newMap("load_tm", "LOAD_TM"));

        config.setDefaultValues(CollectionUtil.newMap("ORG_REGION_RAW", "CXXX"));

        config.setTgtTableGetExtFields(CollectionUtil.newList("IS_MODIFY"));

        config.setTgtDataUpdateCheck(new TgtDataNeedUpdateChecker() {
            @Override
            public boolean needUpdate(Map raw, Map tgt) {
                if (!DataFilterUtil.loadTmDftChecker(raw, tgt)) {
                    return false;
                }
                if (!DataFilterUtil.isModifyChecker(tgt)) {
                    return false;
                }
                return true;
            }
        });

        config.setRawDataValidator(new RawDataValidator() {
            @Override
            public boolean valid(Map data) {
                if (CollectionUtil.isEmpty(data)) {
                    return false;
                }
                if (StringCheckUtil.isEmpty(data.get("month_code"))) {
                    return false;
                }
                if (!RegexUtil.isPositive(String.valueOf(data.get("month_code")))) {
                    return false;
                }
                if (StringCheckUtil.isEmpty(data.get("area_code"))) {
                    return false;
                }
                if (StringCheckUtil.isEmpty(data.get("load_tm"))) {
                    return false;
                }
                //if(DateUtil.str2date())
                return true;
            }
        });

        config.setRawTable("bie_fact_audit_idx_sum");

        config.setTgtTablePk("AREA_MONITOR_ID");
        config.setTgtTable("tm_area_monitor");
        config.setOrderClause(" area_code ");
        config.setJsonField("BASIC");
        config.setRawTableLoadTmColumnName("load_tm");


/**
 自动填值
 `AREA_MONITOR_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

 主键
 `ORG_ID` bigint(20) DEFAULT NULL COMMENT '审计地区ID(0：没有匹配的code)',
 `ORG_CODE_RAW` varchar(200) DEFAULT NULL COMMENT '审计地区code原始值-默认先用',
 `ORG_TM` bigint(20) DEFAULT NULL COMMENT '年月',
 `ORG_TM_RAW` varchar(200) DEFAULT NULL,

 json
 `BASIC` longtext,

 暂 不用
 `ORG_NAME_RAW` varchar(200) DEFAULT NULL COMMENT '审计地区名称原始值-结合大区名用',
 `ORG_REGION_RAW` varchar(200) DEFAULT NULL COMMENT '大区原始值',
 `ORG_LEVEL_RAW` varchar(200) DEFAULT NULL,


 `DELETED` char(4) DEFAULT NULL COMMENT '逻辑删除',
 `CRT_EMP` varchar(50) DEFAULT NULL,
 `UPD_EMP` varchar(50) DEFAULT NULL,
 `CRT_TM` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 `UPD_TM` timestamp NULL DEFAULT NULL,
 `LOAD_TM` varchar(200) DEFAULT NULL,
 `IS_MODIFY` char(4) DEFAULT 'N' COMMENT '是否已修改',
 */
        //主键字段
        Map<String, String> pkmap = new HashMap<>();
        pkmap.put("month_code", "ORG_TM_RAW");
        pkmap.put("area_code", "ORG_CODE_RAW");
        config.setPkFieldMapping(pkmap);

        //普通字段
        Map<String, String> fmap = new HashMap<>();
        fmap.put("area_name", "ORG_NAME_RAW");
        fmap.put("load_tm", "LOAD_TM");
        config.setFieldMapping(fmap);

        //json字段
        Map<String, String> jmap = new HashMap<>();
        //@ApiModelProperty(value ="收入累计值")
        //private String sumIncome;
        jmap.put("shou_add_value", "sumIncome");
//        @ApiModelProperty(value ="员满累计值")
//        private String  sumFullMan;
        jmap.put("yuan_add_value", "sumFullMan");
//        @ApiModelProperty(value ="客满累计值")
//        private String  sumFullCus;
        jmap.put("ke_add_value", "sumFullCus");
//        @ApiModelProperty(value ="成本累计")
//        private String  sumCost;
        jmap.put("chen_add_value", "sumCost");
//        @ApiModelProperty(value ="利润累计")
//        private String  sumProfit;
        jmap.put("li_add_value", "sumProfit");
//        @ApiModelProperty(value ="上年度绩效")
//        private String oneYearPre;
        jmap.put("one_year_per", "oneYearPre");
//        @ApiModelProperty(value ="上上年度绩效")
//        private String twoYearPre;
        jmap.put("two_year_per", "twoYearPre");
//        @ApiModelProperty(value ="网点数量")
//        private String deptCount;
        jmap.put("dept_count", "deptCount");
        //         @ApiModelProperty(value ="人员数量")
//        private String empCount;
        jmap.put("emp_count", "empCount");
//        @ApiModelProperty(value ="一线员工人数")
//        private String lv1Count;
        jmap.put("one_line_count", "lv1Count");
//        @ApiModelProperty(value ="二线员工人数")
//        private String lv2Count;
        jmap.put("two_lint_count", "lv2Count");
//        @ApiModelProperty(value ="三线员工人数")
        jmap.put("thr_line_count", "lv3Count");

        config.setJsonFieldMapping(jmap);

        return config;
    }

    @Override
    public PageOption getInitOption() {
        PageOption opt = new PageOption();
        opt.setServiceName(EgConstant.SERVICE);
        opt.setPageSize(10);
        opt.setBatchProcess(true);
        opt.setAfterType(RetryAfterType.NONE);
        opt.setGetDataType(GetDataType.CUSTOM_BATCH_USEPAGE);
        return opt;
    }
}
