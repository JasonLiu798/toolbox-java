package com.atjl.retry.api;

import com.atjl.retry.api.option.PageOption;

import java.util.List;

/**
 * 获取配置
 */
public interface GetOptionService {

    /**
     * 获取重试配置，获取的为配置拷贝，注册后不可修改
     */
    PageOption getInitOption();

}
