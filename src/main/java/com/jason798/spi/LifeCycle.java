package com.jason798.spi;

/**
 * common dev interface,life cycle
 */
public interface LifeCycle {
    void init() throws Exception;
    void destroy();
}
