package com.jason798.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * simple package of fixed thread pool
 */
public final class SimpleExecutorHelper {

    private ExecutorService executor;

    private static final int DFT_POOL_SIZE = 10;

    private static class HandlerExecutorsHolder {
        private static SimpleExecutorHelper handlerExecutor = new SimpleExecutorHelper();
    }

    /**
     * 构造函数
     */
    private SimpleExecutorHelper() {
        executor = Executors.newFixedThreadPool(DFT_POOL_SIZE);
    }

    /**
     * 单例
     *
     * @return 获取单例
     */
    public static SimpleExecutorHelper getInstance() {
        return HandlerExecutorsHolder.handlerExecutor;
    }

    /**
     * 执行线程
     *
     * @param command 命令
     */

    public void execute(Runnable command) {
        executor.execute(command);
    }
}
