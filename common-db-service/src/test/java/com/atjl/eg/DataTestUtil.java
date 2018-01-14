package com.atjl.eg;


import com.atjl.dbservice.api.CoverteDataService;
import com.atjl.dbservice.api.RawDataDuplicateChecker;
import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.DataCpConfig;
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
        pc.setSrcCols(CollectionUtil.newList("ORG_CODE_RAW"));
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
        pc.setSrcCols(CollectionUtil.newList("ORG_TM_RAW"));
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


    public static DataCpConfig getConfig() {

        DataCpConfig config = new DataCpConfig();

        config.setRawDataDuplicateCheck(new RawDataDuplicateChecker() {
            @Override
            public boolean keepWhich(Map raw1, Map raw2) {
                String l1 = String.valueOf(raw1.get("load_tm"));
                String l2 = String.valueOf(raw2.get("load_tm"));
                return l1.compareTo(l2) > 0;
            }
        });
        config.setDefaultValues(CollectionUtil.newMap("ORG_REGION_RAW", "AA"));

//        config.setNoUpdateCheckMapping(CollectionUtil.newMap("load_tm", "LOAD_TM"));

        Map<String, String> pkmap = new HashMap<>();
        pkmap.put("month_code", "ORG_TM_RAW");
        pkmap.put("area_code", "ORG_CODE_RAW");
        config.setPkFieldMapping(pkmap);


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


    public static DataCpConfig getCustomSelectConfig() {

        DataCpConfig config = new DataCpConfig();
        config.setCustomSelect(true);
        config.setCustomSelectSqlPrefix("select area_code,month_code,load_tm,obj_type,sum(obj_point) obj_point from bie_fact_audit_area_point");
        config.setCustomSelectSqlSuffix("group by area_code,month_code,load_tm,obj_type ");

        config.setDefaultValues(CollectionUtil.newMap("ORG_REGION_RAW", "AA"));

//        config.setNoUpdateCheckMapping(CollectionUtil.newMap("load_tm", "LOAD_TM"));

        Map<String, String> pkmap = new HashMap<>();
        pkmap.put("month_code", "ORG_TM_RAW");
        pkmap.put("area_code", "ORG_CODE_RAW");
        config.setPkFieldMapping(pkmap);

        Map<String, String> fmap = new HashMap<>();
        fmap.put("obj_point", "OBJ_POINT");
        fmap.put("load_tm", "LOAD_TM");

        config.setTgtTablePk("AREA_MONITOR_ID");
        config.setFieldMapping(fmap);

        Map<String, String> jmap = new HashMap<>();
//        jmap.put("shou_mont", "shouMont");
//        config.setJsonFieldMapping(jmap);

        config.setTgtTable("tm_area_monitor");

        config.setRawTable("bie_fact_audit_area_point");
//        config.setOtherCond("order by area_code");
        config.setOrderClause(" area_code,month_code ");
        config.setJsonField("BASIC");

        config.setRawTableLoadTmColumnName("load_tm");
        return config;
    }
}
