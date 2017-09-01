package com.atjl.validate.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created by liujianlong on 16/3/31.
 */
public class Listener {//implements ApplicationListener<ContextRefreshedEvent> {
    /** 日志对象 **/
    private static Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    public void onApplicationEvent(){//ContextRefreshedEvent event) {
        // 初始化验证配置文件
        try {
//            LoadValidate.init();
            LOGGER.info("init Configuration succeed!");
        } catch (Exception e) {
            LOGGER.error("init Configuration error:", e);
        }
    }
}
