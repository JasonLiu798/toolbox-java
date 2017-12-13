package com.atjl.dbservice.api.domain;


import com.atjl.common.api.req.PageIntReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据相关公共配置")
public class DataBaseConfig extends PageIntReq {

    @ApiModelProperty(value = "目标表 主键")
    private String tgtTablePk;
    @ApiModelProperty(value = "目标表名")
    private String tgtTable;


    @ApiModelProperty(value = "取原表数据 附加条件")
    private String otherCond;

    @ApiModelProperty(value = "取原表数据 附加排序条件")
    private String orderClause;

    @ApiModelProperty(value = "原表loadTm/crtTm列名,查询数据时用")
    private String rawTableLoadTmColumnName;





    public String getRawTableLoadTmColumnName() {
        return rawTableLoadTmColumnName;
    }

    public void setRawTableLoadTmColumnName(String rawTableLoadTmColumnName) {
        this.rawTableLoadTmColumnName = rawTableLoadTmColumnName;
    }

    public String getTgtTable() {
        return tgtTable;
    }

    public void setTgtTable(String tgtTable) {
        this.tgtTable = tgtTable;
    }

    public String getOtherCond() {
        return otherCond;
    }

    public void setOtherCond(String otherCond) {
        this.otherCond = otherCond;
    }

    public String getOrderClause() {
        return orderClause;
    }

    public void setOrderClause(String orderClause) {
        this.orderClause = orderClause;
    }

    public String getTgtTablePk() {
        return tgtTablePk;
    }

    public void setTgtTablePk(String tgtTablePk) {
        this.tgtTablePk = tgtTablePk;
    }
}
