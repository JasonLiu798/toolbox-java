package com.atjl.dbservice.api.domain;

import com.atjl.dbservice.api.CoverteDataService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("数据转换器")
public class PropertyCovertor {
    @ApiModelProperty(value = "需要查询的列名列表")
    private List<String> srcCols;
    @ApiModelProperty(value = "写入列名")
    private String tgtCol;
    @ApiModelProperty(value = "自定义转换器")
    private CoverteDataService covertor;
    @ApiModelProperty(value = "转换失败默认值")
    private String defaultValue;


    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public CoverteDataService getCovertor() {
        return covertor;
    }

    public void setCovertor(CoverteDataService covertor) {
        this.covertor = covertor;
    }

    public List<String> getSrcCols() {
        return srcCols;
    }

    public void setSrcCols(List<String> srcCols) {
        this.srcCols = srcCols;
    }

    public String getTgtCol() {
        return tgtCol;
    }

    public void setTgtCol(String tgtCol) {
        this.tgtCol = tgtCol;
    }

//    private Class<R> srcType;

//    private Class<T> tgtType;

//    private R srcVal;

//    private T tgtVal;
//    public R getSrcVal(Object src) {
//        if (src == null) {
//            return null;
//        }
//        return CovertUtil.covertObj(src, srcType);
//    }

//    public T getTgtVal(Object val) {
//        return CovertUtil.covertObj(val, tgtType);
//    }

//    public CoverteDataService<R, T> getCovertor() {
//        return covertor;
//    }

//    public void setCovertor(CoverteDataService<R, T> covertor) {
//        this.covertor = covertor;
//    }

//
//    public String getSrcCol() {
//        return srcCol;
//    }
//
//    public void setSrcCol(String srcCol) {
//        this.srcCol = srcCol;
//    }
//
//    public String getTgtCol() {
//        return tgtCol;
//    }
//
//    public void setTgtCol(String tgtCol) {
//        this.tgtCol = tgtCol;
//    }

}
