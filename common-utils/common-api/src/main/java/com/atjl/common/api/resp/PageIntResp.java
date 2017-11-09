package com.atjl.common.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页结果对象
 */
@ApiModel("分页结果对象，页码整型")
public class PageIntResp<T> {

    @ApiModelProperty(value = "总页数")
    private Integer totalSize;
    @ApiModelProperty(value = "分页后的实体列表")
    private List<T> objects;

    public PageIntResp() {
        this.totalSize = 0;
    }

    public PageIntResp(Integer totalSize, List<T> objects) {
        this.totalSize = totalSize;
        this.objects = objects;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
