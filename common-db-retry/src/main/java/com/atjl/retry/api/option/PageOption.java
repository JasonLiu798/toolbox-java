package com.atjl.retry.api.option;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "只使用分页功能 的 选项")
public class PageOption {
    public PageOption() {
    }


    @ApiModelProperty(value = "服务名（必填），如果使用registeBean方式注册，服务名必须为简写且首字母小写的类名，即bean的默认id")
    private String serviceName;

    @ApiModelProperty(value = "取数方式：自定义取数（需要实现RetryServiceCustomGetDatas接口，如果使用默认后置处理服务，结果对象需要设置 主键、上次执行时间、执行次数，后置服务会自动更新）")
    private GetDataType getDataType = GetDataType.DEFAULT;


    @ApiModelProperty(value = "后置处理类型：只用默认、只用自定义；先默认 再自定义；先自定义 再默认；无")
    private RetryAfterType afterType = RetryAfterType.DEFAULT;


    @ApiModelProperty(value = "如果重试数据量超过分页大小，则分页查询，如果retryService的executeService条件参数 实现了PageIntReq，并且传递了值，则用条件参数的值")
    private int pageSize = 10;


    @ApiModelProperty(value = "是否批量处理，需要实现 ExecuteBatchService")
    private boolean batchProcess = false;

    @ApiModelProperty(value = "是否检查重复执行")
    private boolean checkRepeatExecute = true;

    @ApiModelProperty(value = "检查重复执行的间隔，单位秒，默认60秒内不能重复执行")
    private int checkRepeatExecuteInterval = 60;

    public boolean isCheckRepeatExecute() {
        return checkRepeatExecute;
    }

    public void setCheckRepeatExecute(boolean checkRepeatExecute) {
        this.checkRepeatExecute = checkRepeatExecute;
    }

    public int getCheckRepeatExecuteInterval() {
        return checkRepeatExecuteInterval;
    }

    public void setCheckRepeatExecuteInterval(int checkRepeatExecuteInterval) {
        this.checkRepeatExecuteInterval = checkRepeatExecuteInterval;
    }

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
