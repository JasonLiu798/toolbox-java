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
@ApiModel("分页结果对象，页码整型")
public class PageIntResp<T> implements Serializable {
    private static final long serialVersionUID = -7983899940314731101L;
    @ApiModelProperty(value = "总页数")
    private Integer totalSize;
    @ApiModelProperty(value = "分页后的实体列表")
    private List<T> objects;

}
