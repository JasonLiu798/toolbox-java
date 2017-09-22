package com.atjl.kafka.interceptor;

import com.atjl.kafka.api.hook.KafkaHook;
import org.springframework.stereotype.Component;

/**
 * 默认 注册状态改变拦截器，不做任何事，如不使用，误扫描此包，会导致命名冲突
 */
@Component("regStatusChangeInterceptor")
public class DefaultKafkaHook implements KafkaHook {
    @Override
    public boolean before(String type, String topic, String cid) {
        return true;
    }

    @Override
    public void after(String type, String topic, String cid) {
    }
}
