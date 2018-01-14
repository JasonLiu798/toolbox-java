package com.atjl.retry.util;


import com.atjl.jfeild.domain.JFieldMeta;
import com.atjl.jfeild.domain.JTabMeta;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.retry.domain.ProcessLogBiz;

public class ProcessLogMetaUtil {

    public static JTabMeta getBizTabMeta() {
        JTabMeta o = new JTabMeta();
        o.setFieldList(null);
        JFieldMeta j = new JFieldMeta();
        j.setColumnName("processDetail");
        //PROCESS_DETAIL
        j.setFieldType(ProcessLogBiz.class);
        o.setBasis(j);
        return o;
    }


    public static JTabMeta getDetailTabMeta() {
        JTabMeta o = new JTabMeta();
        o.setFieldList(null);
        JFieldMeta j = new JFieldMeta();
        j.setColumnName("basic");
        //PROCESS_DETAIL
        j.setFieldType(ExecuteStatusResp.class);
        o.setBasis(j);
        return o;
    }
}
