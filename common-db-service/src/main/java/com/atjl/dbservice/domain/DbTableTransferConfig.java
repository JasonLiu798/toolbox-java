package com.atjl.dbservice.domain;

import com.atjl.common.api.req.PageIntReq;
import com.atjl.dbservice.api.RawDataValidator;
import com.atjl.util.collection.CollectionUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel("数据清洗配置")
public class DbTableTransferConfig extends PageIntReq {

    @ApiModelProperty(value = "原始数据 初步校验 校验器，不可有spring依赖，只校验 是否为空，格式，长度等简单数据")
    private RawDataValidator rawDataValidator;

    @ApiModelProperty(value = "原表-目标表 主键字段映射关系")
    private Map<String, String> pkFieldMapping;
    @ApiModelProperty(value = "原表-目标表 基础字段映射关系")
    private Map<String, String> fieldMapping;
    @ApiModelProperty(value = "原表-目标表 json字段映射关系")
    private Map<String, String> jsonFieldMapping;

    @ApiModelProperty(value = "目标表 主键")
    private String tgtTablePk;

    @ApiModelProperty(value = "取原表数据 附加条件")
    private String otherCond;

    @ApiModelProperty(value = "原始表名")
    private String rawTable;

    @ApiModelProperty(value = "目标表名")
    private String tgtTable;

    @ApiModelProperty(value = "主键域 字符串")
    public String getTgtPkFields() {
        String res;
        List<String> pkField = CollectionUtil.map2list(pkFieldMapping, false);
        res = field2string(pkField);
        res = tgtTablePk + "," + res;
        return res;
    }

    public Map.Entry<String, String> getPkFieldRandomOne() {
        for (Map.Entry<String, String> entry : pkFieldMapping.entrySet()) {
            return entry;
        }
        return null;
    }

    @ApiModelProperty(value = "原始数据 fields")
    public String getAllFields() {
        String res = "";
        List<String> pkField = CollectionUtil.map2list(pkFieldMapping, true);
        res = field2string(pkField);

        if (!CollectionUtil.isEmpty(fieldMapping)) {
            List<String> field = CollectionUtil.map2list(fieldMapping, true);

            res = res + "," + field2string(field);
        }
        if (!CollectionUtil.isEmpty(jsonFieldMapping)) {
            List<String> jsonFields = CollectionUtil.map2list(jsonFieldMapping, true);
            String jsonFeidlsStr = field2string(jsonFields);
            res += "," + jsonFeidlsStr;
        }
        return res;
    }


    public String field2string(List<String> field) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int last = field.size() - 1;
        for (String f : field) {
            sb.append(f);
            if (i != last) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }


    public String getTgtTablePk() {
        return tgtTablePk;
    }

    public void setTgtTablePk(String tgtTablePk) {
        this.tgtTablePk = tgtTablePk;
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

    public String getOtherCond() {
        return otherCond;
    }

    public void setOtherCond(String otherCond) {
        this.otherCond = otherCond;
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

    public String getTgtTable() {
        return tgtTable;
    }

    public void setTgtTable(String tgtTable) {
        this.tgtTable = tgtTable;
    }
}
