package com.atjl.mybatis.plugin.domain;


import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class PrimaryKey {

    public PrimaryKey() {
    }

    private String columnName;
    private String propName;
    private FullyQualifiedJavaType fullyQualifiedJavaType;
    private String value;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public PrimaryKey(String columnName) {
        this.columnName = columnName;
    }

    public FullyQualifiedJavaType getFullyQualifiedJavaType() {
        return fullyQualifiedJavaType;
    }

    public void setFullyQualifiedJavaType(FullyQualifiedJavaType fullyQualifiedJavaType) {
        this.fullyQualifiedJavaType = fullyQualifiedJavaType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
