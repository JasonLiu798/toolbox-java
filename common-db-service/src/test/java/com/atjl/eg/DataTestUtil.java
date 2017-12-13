package com.atjl.eg;


import com.atjl.dbservice.api.CoverteDataService;
import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.DbTableTransferConfig;
import com.atjl.dbservice.api.domain.PropertyCovertor;
import com.atjl.dbservice.api.exception.DataCovException;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.common.TimestampUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTestUtil {

    public static DataCoverteConfig getCovConfig() {

        DataCoverteConfig config = new DataCoverteConfig();
        config.setTgtTable("tm_area_monitor");
        config.setTgtTablePk("AREA_MONITOR_ID");

        config.setOrderClause(" AREA_MONITOR_ID ");
        config.setRawTableLoadTmColumnName("UPD_TM");

        List<PropertyCovertor> propertyCovertors = new ArrayList<>();
        PropertyCovertor pc = new PropertyCovertor();
        pc.setSrcCol("ORG_CODE_RAW");
        pc.setTgtCol("ORG_ID");
        pc.setCovertor(new CoverteDataService() {
            @Override
            public String coverte(Map src) {
                String res = "100";
//                if (StringCheckUtil.isEmpty(res)) {
//                    throw new DataCovException("值为空");
//                }
                return res;
            }
        });
        propertyCovertors.add(pc);


        pc = new PropertyCovertor();
        pc.setSrcCol("ORG_TM_RAW");
        pc.setTgtCol("ORG_TM");
        pc.setCovertor(new CoverteDataService() {
            @Override
            public String coverte(Map src) {
                String srcStr = StringUtil.getEmptyString(src.get("ORG_TM_RAW"));
                String res = String.valueOf(TimestampUtil.getMonth(DateUtil.str2date(srcStr, DateUtil.yyyyMM_EN)));
                if (StringCheckUtil.isEmpty(res)) {
                    throw new DataCovException("值为空");
                }
                return res;
            }
        });
        propertyCovertors.add(pc);

        config.setCovertors(propertyCovertors);
        return config;
    }


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
