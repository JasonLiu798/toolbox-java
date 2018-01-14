package com.atjl.jfeild.util.eg;


import com.atjl.jfeild.domain.JFieldMeta;
import com.atjl.jfeild.domain.JTabMeta;
import com.atjl.jfeild.util.eg.BizObj;
import com.atjl.jfeild.util.eg.BizObjI1;
import com.atjl.jfeild.util.eg.BizObjI2;

import java.util.ArrayList;
import java.util.List;

public class BizObjMetaUtil {

    public static JTabMeta getTabMeta() {
        JTabMeta o = new JTabMeta();
        o.setFieldList(getFieldMeta());
        JFieldMeta j = new JFieldMeta();
        j.setColumnName("basic");
        j.setFieldType(BizObj.class);
        o.setBasis(j);
        return o;
    }

    public static List<JFieldMeta> getFieldMeta() {
//        Map<String, Class> field = new HashMap<>();
//        field.put("prjCrew", ProjectEcpInfo.class);
//        field.put("", ProjectEcpInfo.class);

        List<JFieldMeta> fieldList = new ArrayList<>();
        JFieldMeta f = new JFieldMeta();
        f.setColumnName("oi1");
        f.setFieldType(BizObjI1.class);
        f.setFieldName("bizObjI1");
        fieldList.add(f);

        f = new JFieldMeta();
        f.setColumnName("oi2");
        f.setFieldType(BizObjI2.class);
        f.setFieldName("bizObjI2");
        fieldList.add(f);
        return fieldList;
    }
}
