package com.atjl.common.api;

/**
 * common dev interface,life cycle
 */
public interface LifeCycle {
    /**
     * init
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * destroy
     */
    void destroy();
}
