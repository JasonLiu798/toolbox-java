package com.atjl.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by async on 2017/12/3.
 */
@ApiModel(value = "key-value")
public class KeyValue {

    @ApiModelProperty(value = "key")
    private String key;
    @ApiModelProperty(value = "value")
    private String value;

    public KeyValue(String key) {
        this.key = key;
    }

    public KeyValue() {
    }

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
