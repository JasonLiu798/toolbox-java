package com.atjl.retry.domain;


import com.atjl.retry.api.*;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryInstanceOption;
import com.atjl.retry.api.option.RetryOption;

public class RetryServiceItem {
    /**
     * 主服务
     */
    private ExecuteService retryService;
    /**
     * 批量处理主服务
     */
    private ExecuteBatchService executeBatchService;

    private GeneralPreService generalPreService;


    /**
     * 后置服务
     */
    private AfterService afterService;

    //取总数
    private CustomGetCount customGetCount;

    //分页取数
    private CustomGetDatas customGetDatas;
    //取数，不包含上下文
    private CustomGetSimpleDatas customGetSimpleDatas;
    //分页取数，按id
    private CustomGetDatasUseIdPage customGetDatasUseIdPage;

    //初级 选项
    private PageOption pageOption;
    //需要立即重试 选项
    private RetryInstanceOption retryInstanceOption;
    //需要 db 重试 选项
    private RetryOption retryOption;

    public AfterService getAfterService() {
        return afterService;
    }

    public CustomGetDatasUseIdPage getCustomGetDatasUseIdPage() {
        return customGetDatasUseIdPage;
    }


    public void setCustomGetDatasUseIdPage(CustomGetDatasUseIdPage customGetDatasUseIdPage) {
        this.customGetDatasUseIdPage = customGetDatasUseIdPage;
    }

    public void setAfterService(AfterService afterService) {
        this.afterService = afterService;
    }

    public CustomGetDatas getCustomGetDatas() {
        return customGetDatas;
    }

    public void setCustomGetDatas(CustomGetDatas customGetDatas) {
        this.customGetDatas = customGetDatas;
    }

    public ExecuteService getRetryService() {
        return retryService;
    }

    public void setRetryService(ExecuteService retryService) {
        this.retryService = retryService;
    }

    public PageOption getPageOption() {
        return pageOption;
    }

    public void setPageOption(PageOption pageOption) {
        this.pageOption = pageOption;
    }

    public RetryInstanceOption getRetryInstanceOption() {
        return retryInstanceOption;
    }

    public CustomGetSimpleDatas getCustomGetSimpleDatas() {
        return customGetSimpleDatas;
    }

    public void setCustomGetSimpleDatas(CustomGetSimpleDatas customGetSimpleDatas) {
        this.customGetSimpleDatas = customGetSimpleDatas;
    }

    public void setRetryInstanceOption(RetryInstanceOption retryInstanceOption) {
        this.retryInstanceOption = retryInstanceOption;
    }

    public CustomGetCount getCustomGetCount() {
        return customGetCount;
    }

    public void setCustomGetCount(CustomGetCount customGetCount) {
        this.customGetCount = customGetCount;
    }

    public ExecuteBatchService getExecuteBatchService() {
        return executeBatchService;
    }

    public void setExecuteBatchService(ExecuteBatchService executeBatchService) {
        this.executeBatchService = executeBatchService;
    }

    public RetryOption getRetryOption() {
        return retryOption;
    }

    public void setRetryOption(RetryOption retryOption) {
        this.retryOption = retryOption;
    }

    public GeneralPreService getGeneralPreService() {
        return generalPreService;
    }

    public void setGeneralPreService(GeneralPreService generalPreService) {
        this.generalPreService = generalPreService;
    }
}
