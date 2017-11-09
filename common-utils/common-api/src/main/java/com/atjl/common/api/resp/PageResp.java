package com.atjl.common.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页结果对象
 */
@ApiModel("分页结果对象")
public class PageResp<T> {

//    @ApiModelProperty(value = "是否发送email", dataType = ApiConstant.DATA_TP_CONST, allowableValues = ApiConstant.ALLOW_YN, example = "N", required = true)

    @ApiModelProperty(value = "总页数")
    private Long totalSize;
    @ApiModelProperty(value = "分页后的实体列表")
    private List<T> objects;

    public PageResp() {
    }

    public PageResp(Long totalSize, List<T> objects) {
        this.totalSize = totalSize;
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "totalSize=" + totalSize +
                ", objects=" + objects +
                '}';
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
