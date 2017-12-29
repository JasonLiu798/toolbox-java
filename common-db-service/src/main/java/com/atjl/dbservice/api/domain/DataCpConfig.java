package com.atjl.dbservice.api.domain;

import com.atjl.dbservice.api.validator.DftRawDataDuplicateChecker;
import com.atjl.dbservice.api.RawDataDuplicateChecker;
import com.atjl.dbservice.api.RawDataValidator;
import com.atjl.dbservice.api.TgtDataNeedUpdateChecker;
import com.atjl.dbservice.domain.KeyValue;
import com.atjl.dbservice.util.DataFieldUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionSortUtil;
import com.atjl.util.collection.CollectionUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiModel("数据表间拷贝配置")
public class DataCpConfig extends DataBaseConfig {

    @ApiModelProperty(value = "是否清空目标表")
    private boolean clearTgtTable = false;

    @ApiModelProperty(value = "原始数据 初步校验 校验器，不可有spring依赖，只校验 是否为空，格式，长度等简单数据")
    private RawDataValidator rawDataValidator;

    @ApiModelProperty(value = "原始数据+目标数据 校验，不满足的不更新,原表数据注入 所有字段，目标表 数据注入 主键+普通字段+指定的附加字段")
    private TgtDataNeedUpdateChecker tgtDataUpdateCheck;


    @ApiModelProperty(value = "一个分页内的原始数据重复，保留哪条校验器")
    private RawDataDuplicateChecker rawDataDuplicateCheck = new DftRawDataDuplicateChecker();

    @ApiModelProperty(value = "原表-目标表 主键字段映射关系")
    private Map<String, String> pkFieldMapping;
    @ApiModelProperty(value = "原表-目标表 基础字段映射关系")
    private Map<String, String> fieldMapping;
    @ApiModelProperty(value = "原表-目标表 json字段映射关系")
    private Map<String, String> jsonFieldMapping;

    @ApiModelProperty(value = "目标表 需要预置 默认值字段")
    private Map<String, String> defaultValues;

    @ApiModelProperty(value = "原始表名")
    private String rawTable;

    @ApiModelProperty(value = "是否自定义查询")
    private boolean customSelect = false;

    @ApiModelProperty(value = "自定义查询sql select 前缀")
    private String customSelectSqlPrefix;

    @ApiModelProperty(value = "自定义查询sql select 后缀")
    private String customSelectSqlSuffix;

    @ApiModelProperty(value = "自定义查询sql count前缀")
    private String customCountSqlPrefix;

    @ApiModelProperty(value = "自定义查询sql count后缀")
    private String customCountSqlSuffix;


    @ApiModelProperty(value = "目标表，需要取的附加字段")
    private List<String> tgtTableGetExtFields;

    @ApiModelProperty(value = "目标表json字段名")
    private String jsonField;


//    @ApiModelProperty(value = "需要转换的字段")
//    private Map<String, PropertyCovertor> covertors;

    public List<String> getRawPkFieldList() {
        return CollectionUtil.map2list(pkFieldMapping, true);
    }


    @ApiModelProperty(value = "主键域 字符串")
    public String getTgtPkFields() {
        String res;
        List<String> pkField = CollectionUtil.map2list(pkFieldMapping, false);
        res = DataFieldUtil.field2string(pkField);
        res = this.getTgtTablePk() + "," + res;
        return res;
    }

    @ApiModelProperty(value = "目标表查询的字段列表，主键域+需要校验 是否存在 的字符串")
    public String getTgtPksAndUpdCheckColsFields() {
        List<String> pkField = CollectionUtil.map2list(pkFieldMapping, false);

        List<String> normalField = CollectionUtil.map2list(fieldMapping, false);
//        List<String> noUpdChkFields = CollectionUtil.map2list(noUpdateCheckMapping, false);
        if (!CollectionUtil.isEmpty(normalField)) {
            pkField.addAll(normalField);
        }

        if (!CollectionUtil.isEmpty(tgtTableGetExtFields)) {
            pkField.addAll(tgtTableGetExtFields);
        }

        pkField.add(getTgtTablePk());
        return DataFieldUtil.field2string(pkField);
    }

    public Map.Entry<String, String> getPkFieldRandomOne() {
        for (Map.Entry<String, String> entry : pkFieldMapping.entrySet()) {
            return entry;
        }
        return null;
    }

    @ApiModelProperty(value = "目标表 所有字段,经过排序")
    public List<String> getAllTgtSortFields() {
        List<String> res = CollectionUtil.map2list(pkFieldMapping, false);
        if (!CollectionUtil.isEmpty(fieldMapping)) {
            List<String> field = CollectionUtil.map2list(fieldMapping, false);
            res.addAll(field);
        }

        if (!CollectionUtil.isEmpty(defaultValues)) {
            List<String> field = CollectionUtil.map2list(defaultValues, true);
            res.addAll(field);
        }

        if (!StringCheckUtil.isEmpty(jsonField)) {
            res.add(jsonField);
        }
        res = CollectionSortUtil.sort(res);
        return res;
    }

    public List<KeyValue> getAllTgtSortKV() {
        List<String> keys = getAllTgtSortFields();
        List<KeyValue> res = new ArrayList<>();
        for (String k : keys) {
            res.add(new KeyValue(k));
        }
        return res;
    }

    @ApiModelProperty(value = "原始数据 fields")
    public String getAllRawFieldsStr() {
        String res = "";
        List<String> pkField = CollectionUtil.map2list(pkFieldMapping, true);
        res = DataFieldUtil.field2string(pkField);

        if (!CollectionUtil.isEmpty(fieldMapping)) {
            List<String> field = CollectionUtil.map2list(fieldMapping, true);
            res = res + "," + DataFieldUtil.field2string(field);
        }

        if (!CollectionUtil.isEmpty(jsonFieldMapping)) {
            List<String> jsonFields = CollectionUtil.map2list(jsonFieldMapping, true);
            res += "," + DataFieldUtil.field2string(jsonFields);
        }

//        if (!CollectionUtil.isEmpty(noUpdateCheckMapping)) {
//            List<String> updFields = CollectionUtil.map2list(noUpdateCheckMapping, true);
//            res += "," + DataFieldUtil.field2string(updFields);
//        }

        return res;
    }


    public Map<String, String> getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(Map<String, String> defaultValues) {
        this.defaultValues = defaultValues;
    }

//    public Map<String, String> getNoUpdateCheckMapping() {
//        return noUpdateCheckMapping;
//    }

//    public void setNoUpdateCheckMapping(Map<String, String> noUpdateCheckMapping) {
//        this.noUpdateCheckMapping = noUpdateCheckMapping;
//    }


    public boolean isCustomSelect() {
        return customSelect;
    }

    public void setCustomSelect(boolean customSelect) {
        this.customSelect = customSelect;
    }

    public String getCustomSelectSqlPrefix() {
        return customSelectSqlPrefix;
    }

    public void setCustomSelectSqlPrefix(String customSelectSqlPrefix) {
        this.customSelectSqlPrefix = customSelectSqlPrefix;
    }

    public String getCustomSelectSqlSuffix() {
        return customSelectSqlSuffix;
    }

    public void setCustomSelectSqlSuffix(String customSelectSqlSuffix) {
        this.customSelectSqlSuffix = customSelectSqlSuffix;
    }

    public Map<String, String> getJsonFieldMapping() {
        return jsonFieldMapping;
    }

    public void setJsonFieldMapping(Map<String, String> jsonFieldMapping) {
        this.jsonFieldMapping = jsonFieldMapping;
    }

    public RawDataValidator getRawDataValidator() {
        return rawDataValidator;
    }

    public void setRawDataValidator(RawDataValidator rawDataValidator) {
        this.rawDataValidator = rawDataValidator;
    }


    public Map<String, String> getFieldMapping() {
        return fieldMapping;
    }

    public void setFieldMapping(Map<String, String> fieldMapping) {
        this.fieldMapping = fieldMapping;
    }

    public Map<String, String> getPkFieldMapping() {
        return pkFieldMapping;
    }

    public void setPkFieldMapping(Map<String, String> pkFieldMapping) {
        this.pkFieldMapping = pkFieldMapping;
    }

    public String getRawTable() {
        return rawTable;
    }

    public void setRawTable(String rawTable) {
        this.rawTable = rawTable;
    }

    public String getJsonField() {
        return jsonField;
    }

    public void setJsonField(String jsonField) {
        this.jsonField = jsonField;
    }

    public TgtDataNeedUpdateChecker getTgtDataUpdateCheck() {
        return tgtDataUpdateCheck;
    }

    public void setTgtDataUpdateCheck(TgtDataNeedUpdateChecker tgtDataUpdateCheck) {
        this.tgtDataUpdateCheck = tgtDataUpdateCheck;
    }

    public List<String> getTgtTableGetExtFields() {
        return tgtTableGetExtFields;
    }

    public void setTgtTableGetExtFields(List<String> tgtTableGetExtFields) {
        this.tgtTableGetExtFields = tgtTableGetExtFields;
    }

    public boolean isClearTgtTable() {
        return clearTgtTable;
    }

    public RawDataDuplicateChecker getRawDataDuplicateCheck() {
        return rawDataDuplicateCheck;
    }

    public String getCustomCountSqlPrefix() {
        return customCountSqlPrefix;
    }

    public void setCustomCountSqlPrefix(String customCountSqlPrefix) {
        this.customCountSqlPrefix = customCountSqlPrefix;
    }

    public String getCustomCountSqlSuffix() {
        return customCountSqlSuffix;
    }

    public void setCustomCountSqlSuffix(String customCountSqlSuffix) {
        this.customCountSqlSuffix = customCountSqlSuffix;
    }

    public void setRawDataDuplicateCheck(RawDataDuplicateChecker rawDataDuplicateCheck) {
        this.rawDataDuplicateCheck = rawDataDuplicateCheck;
    }

    public void setClearTgtTable(boolean clearTgtTable) {
        this.clearTgtTable = clearTgtTable;
    }
}
