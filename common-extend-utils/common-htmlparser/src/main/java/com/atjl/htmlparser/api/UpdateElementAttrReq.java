package com.atjl.htmlparser.api;


import com.atjl.common.domain.KeyValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "更新html对象属性请求")
public class UpdateElementAttrReq {

    @ApiModelProperty(value = "元素")
    private String tag;

    @ApiModelProperty(value = "要求改的属性列表(key-属性名，value-要修改的属性值)")
    private List<KeyValue> attrs;

    public void addAttr(String key, String value) {
        if (attrs == null) {
            attrs = new ArrayList<>();
        }
        attrs.add(new KeyValue(key, value));
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<KeyValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<KeyValue> attrs) {
        this.attrs = attrs;
    }
}
