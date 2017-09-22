package com.atjl.retry.domain;


import com.atjl.retry.api.domain.RetryData;

import java.util.Map;

public class RetryItem extends RetryData {

    /**
     * 其他需要查询的字段数据结果
     */
    private Map<String, String> datas;

    public Map<String, String> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, String> datas) {
        this.datas = datas;
    }
}
