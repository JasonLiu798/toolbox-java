package com.atjl.dbtiming.init;

import com.atjl.dbtiming.api.TimingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * listener 初始化
 */
@Component
public class TimingInitListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(TimingInitListener.class);

    @Resource
    private TimingService timingManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /**
         * 初始化 定时任务
         */
        timingManager.init();
        if (logger.isDebugEnabled()) {
            logger.debug("initted timing manager");
        }
    }
}
