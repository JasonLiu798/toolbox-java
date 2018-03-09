package com.sf.inv.spi;

/**
 * common dev interface,life cycle
 */
public interface LifeCycle {
    void init() throws Exception;
    void destroy();
}
