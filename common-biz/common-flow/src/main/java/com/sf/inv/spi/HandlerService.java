package com.sf.inv.spi;

import com.sf.inv.dto.common.ResponseInnerDto;

/**
 * kafka客户端处理接口
 */
public interface HandlerService {
    ResponseInnerDto execute(String json, int option);
}
