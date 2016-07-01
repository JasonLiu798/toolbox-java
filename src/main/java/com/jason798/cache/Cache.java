package com.jason798.cache;

public interface Cache {
    public Object get(String key);
    public void set(String key,Object data);
    public Object delete(String key);
}
