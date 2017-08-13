package com.atjl.common.api;

/**
 * common dev interface,cache
 */
public interface ICache<T> extends LifeCycle {
    void put(String key, T data);
    T get(String key);
    boolean contain(String key);
    void remove(String key);
    String getStatus();
}
