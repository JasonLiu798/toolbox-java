package com.atjl.retry.api.option;

/**
 * 只使用分页功能 的 选项
 */
public class PageOption {
    public PageOption() {
    }

    /**
     * 服务名（必填）
     */
    private String serviceName;

    /**
     * 取数类型：
     * false 默认取数
     * true 自定义取数（需要实现RetryServiceCustomGetDatas接口，如果使用默认后置处理服务，结果对象需要设置 主键、上次执行时间、执行次数，后置服务会自动更新）
     */
    private GetDataType getDataType = GetDataType.DEFAULT;

    /**
     * 后置处理类型：
     * 只用默认、只用自定义、
     * 先默认 再自定义
     * 先自定义 再默认
     */
    private RetryAfterType afterType = RetryAfterType.DEFAULT;

    /**
     * 如果重试数据量超过分页大小，则分页查询
     */
    private int pageSize = 10;

    /**
     * 批量处理，需要实现 ExecuteBatchService
     */
    private boolean batchProcess = false;


    /**
     * ############## getter && setter ####################
     */
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public GetDataType getGetDataType() {
        return getDataType;
    }

    public void setGetDataType(GetDataType getDataType) {
        this.getDataType = getDataType;
    }

    public RetryAfterType getAfterType() {
        return afterType;
    }

    public void setAfterType(RetryAfterType afterType) {
        this.afterType = afterType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isBatchProcess() {
        return batchProcess;
    }

    public void setBatchProcess(boolean batchProcess) {
        this.batchProcess = batchProcess;
    }
}
