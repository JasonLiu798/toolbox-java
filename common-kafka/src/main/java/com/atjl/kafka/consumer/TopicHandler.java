package com.atjl.kafka.consumer;

import com.atjl.kafka.api.event.Event;

import java.util.List;

/**
 * for kafka consumer process data
 * kafka自定义处理类 需要实现接口
 *
 * @since 1.0
 */
public interface TopicHandler {
    /**
     * process the data
     *
     * @param da
     */
    void execute(List<Event> da);
}
