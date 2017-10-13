package com.atjl.dbtiming.api;

/**
 * @author JasonLiu
 */
public interface ITaskParam {

    /**
     * 执行线程在调用 execute方法前调用此方法，放入参数
     *
     * @param param 参数
     */
    void set(String param);

    /**
     * 获取参数，execute内可通过此方法获取 执行线程 放入的参数
     */
    String get();
}
