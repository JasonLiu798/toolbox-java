package com.atjl.mybatis.plugin.util;


import com.atjl.mybatis.plugin.domain.PrimaryKey;
import org.mybatis.generator.api.IntrospectedColumn;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKeyUtil {

    public static List<PrimaryKey> getPrimayKeys(List<IntrospectedColumn> pks) {
        if (pks == null || pks.isEmpty()) {
            return new ArrayList<PrimaryKey>();
        }
        List<PrimaryKey> res = new ArrayList<PrimaryKey>();
        for (IntrospectedColumn c : pks) {
            PrimaryKey k = new PrimaryKey();
            k.setColumnName(c.getActualColumnName());
            k.setFullyQualifiedJavaType(c.getFullyQualifiedJavaType());
            k.setPropName(c.getJavaProperty());
            res.add(k);
        }
        return res;
    }
}
