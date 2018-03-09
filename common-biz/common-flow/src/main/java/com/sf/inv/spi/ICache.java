package com.sf.inv.spi;

/**
 * common dev interface,cache
 */
public interface ICache<T> extends LifeCycle {
    void put(String key,T data);
    T get(String key);
    boolean exist(String key);
    T remove(String key);
    String getStatus();
}
