package com.atjl.util.table;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * DataStruct:
 *      c1      c2      c3
 * r1   1       0       1
 * r2   0       1       0
 *
 * HashMap key=[r1,r2...rn]
 *  value=set(c1,c2,....cn) which value =1
 *
 * Thread un-safe
 * Created by JasonLiu798 on 16/4/14.
 */
public class TwoDimensionBitTable {

    private String name;
    private Map<String,Set<String>> table = new ConcurrentHashMap<>();
    public static final String EL_TABLE="table";
    public static final String EL_ROW="row";
    public static final String EL_COL="col";
    public static final String ATTR_KEY="key";
    public static final String TAB_NAME_DFT ="default";


    public TwoDimensionBitTable(){
        init(TAB_NAME_DFT);
    }
    public TwoDimensionBitTable(String name) {
        init(name);
    }
    public void init(String name){
        this.name = name;
    }

    /**
     * 增加一列
     * @param rowName
     * @param columns
     */
    public void addRow(String rowName,Set<String> columns){
        table.put(rowName,columns);
    }
    public void addColumn(String rowName,String columnValue){
        Set<String> set = table.get(rowName);
        set.add(columnValue);
    }
    public Set<String> getRow(String rowName){
        return table.get(rowName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTable(HashMap<String, Set<String>> table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public Map<String, Set<String>> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "TwoDimensionBitTable{" +
                "name='" + name + '\'' +
                ", table=" + table +
                '}';
    }
}
