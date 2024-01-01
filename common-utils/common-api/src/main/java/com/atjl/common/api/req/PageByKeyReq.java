package com.atjl.common.api.req;

import com.atjl.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 按指定key的值分页，key需加索引
 *
 * @author atjl
 */
@Data
@ApiModel(value = "按指定key分页，瀑布流")
public class PageByKeyReq extends PageBaseReq implements Serializable {

    private static final long serialVersionUID = -1812478650105802153L;
    @ApiModelProperty(value = "起始对象主键", required = true, example = "查询需分页时传递")
    private String startItemId;
    @ApiModelProperty(value = "页大小", example = "查询需分页时传递，默认10")
    private Long pageSize = CommonConstant.DFT_PAGE_SIZE;
    public PageByKeyReq() {
        super();
    }
    public PageByKeyReq(Long pageSize) {
        this.startItemId = null;
        this.pageSize = pageSize;
    }
    public PageByKeyReq(String startItemId, Long pageSize) {
        this.startItemId = startItemId;
        this.pageSize = pageSize;
    }
    public Long getPageSize() {
        return pageSize == null || pageSize <= 0 ? CommonConstant.DFT_PAGE_SIZE : pageSize;
    }

}
