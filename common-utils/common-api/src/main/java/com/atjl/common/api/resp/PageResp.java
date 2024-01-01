package com.atjl.common.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果对象
 */
@Data
@ApiModel("分页结果对象")
public class PageResp<T> implements Serializable {
    private static final long serialVersionUID = 7051946770615084523L;
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

}
