package com.atjl.dbservice.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by async on 2017/12/3.
 */
public class TgtTableDataUpdatePkg {

    /**
     * key:propertyName
     * value:
     * List
     * key:pkValue
     * value:property's value
     */
    private Map<String, List<KeyValue>> items;

    private List<String> pkValues;

    private int failCount;

    public static TgtTableDataUpdatePkg buildFrom(TgtTableData data) {

        return null;
    }

    public Map<String, List<KeyValue>> getItems() {
        return items;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public void setItems(Map<String, List<KeyValue>> items) {
        this.items = items;
    }

    public List<String> getPkValues() {
        return pkValues;
    }

    public void setPkValues(List<String> pkValues) {
        this.pkValues = pkValues;
    }
}
