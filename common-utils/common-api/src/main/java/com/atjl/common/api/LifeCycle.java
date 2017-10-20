package com.atjl.common.api;

/**
 * common dev interface,life cycle
 */
public interface LifeCycle {
    void init() throws Exception;
    void destroy();
}
