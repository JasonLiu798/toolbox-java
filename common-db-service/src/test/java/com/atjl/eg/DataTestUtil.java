package com.atjl.eg;


import com.atjl.dbservice.api.domain.DbTableTransferConfig;
import com.atjl.util.collection.CollectionUtil;

import java.util.HashMap;
import java.util.Map;

public class DataTestUtil {


    public static DbTableTransferConfig getConfig() {

        DbTableTransferConfig config = new DbTableTransferConfig();

        config.setDefaultValues(CollectionUtil.newMap("ORG_REGION_RAW", "AA"));

//        config.setNoUpdateCheckMapping(CollectionUtil.newMap("load_tm", "LOAD_TM"));

        Map<String, String> pkmap = new HashMap<>();
        pkmap.put("month_code", "ORG_TM_RAW");
        pkmap.put("area_code", "ORG_CODE_RAW");
        config.setPkFieldMapping(pkmap);

        pkmap.put("month_code", "ORG_TM_RAW");


        Map<String, String> fmap = new HashMap<>();
        fmap.put("area_name", "ORG_NAME_RAW");
        fmap.put("load_tm", "LOAD_TM");

        config.setTgtTablePk("AREA_MONITOR_ID");
        config.setFieldMapping(fmap);

        Map<String, String> jmap = new HashMap<>();
        jmap.put("shou_mont", "shouMont");
        config.setJsonFieldMapping(jmap);

        config.setTgtTable("tm_area_monitor");

        config.setRawTable("bie_fact_audit_idx_sum");
//        config.setOtherCond("order by area_code");
        config.setOrderClause(" area_code ");
        config.setJsonField("BASIC");

        config.setRawTableLoadTmColumnName("load_tm");
        return config;
    }
}
