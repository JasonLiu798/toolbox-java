package com.atjl.dbservice.manager;


import com.atjl.dbservice.domain.DbTableTransferConfig;

import java.util.HashMap;
import java.util.Map;

public class DataTestUtil
{


    public static DbTableTransferConfig getConfig(){

        DbTableTransferConfig config = new DbTableTransferConfig();

        Map<String, String> pkmap = new HashMap<>();
        pkmap.put("month_code", "ORG_TM_RAW");
        pkmap.put("area_code", "ORG_CODE_RAW");
        config.setPkFieldMapping(pkmap);

        Map<String, String> fmap = new HashMap<>();
        fmap.put("area_name", "ORG_NAME_RAW");

        config.setFieldMapping(fmap);

        Map<String, String> jmap = new HashMap<>();
        jmap.put("shou_mont", "shouMont");
        config.setJsonFieldMapping(jmap);

        config.setTgtTable("tm_area_monitor");

        config.setRawTable("bie_fact_audit_idx_sum");
        return config;
    }
}
