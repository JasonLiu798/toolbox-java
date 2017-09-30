package com.atjl.retry.api;

import com.atjl.retry.api.option.InitOption;

public interface DataContext<T> {
    /**
     * ############## 重试前需要设值 #################
     */
    /**
     * 设置 重试条目主键
     * 必填项，不填则直接调用 通知管理员接口
     */
    void setId(String id);
    String getId();

    /**
     * 设置 已经重试的次数
     * 不设值，默认为 最后一次重试
     */
    void setRetriedCnt(Long retriedCnt);

    /**
     * 设置 上次重试时间
     * 不设值，默认为 当前时间立马执行
     */
    void setLastRetriedTs(Long lastRetriedTs);

    /**
     * 设置自定义数据，设置后，重试 execute操作内可用
     * 使用default，返回data类型为 Map<String, Object>
     */
    void setData(T data);

    /**
     * 获取自定义数据
     */
    T getData();


    /**
     * ############## 重试后 需要调用的 ##############
     */
    /**
     * 设置 重试结果失败
     */
    void setFail();

    /**
     * 设置 重试结果成功
     */
    void setSucc();

    /**
     * 设置 失败原因
     */
    void setFailReason(String reason);

    String getFailReason();

    /**
     * ############ 其他 ###################
     */
    /**
     * 获取初始化配置
     */
    InitOption getInitOption();


}
