package com.atjl.retry.domain;


import com.atjl.common.constant.CommonConstant;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryOption;

/**
 * 重试基础上下文，每条数据一个
 */
public class RetryDataContextImpl<T> implements DataContext<T> {
    /**
     * 重试前需要调用方设值数据
     */
    private T data;//用户自定义数据

    /**
     * 需要调用方 设值
     */
    private RetryData retryData;

    /**
     * 提供用户方使用，
     */
    private PageOption initOption;

    public RetryDataContextImpl(T data) {
        this.data = data;
        this.retryData = new RetryData();
    }

    /**
     * 增加，深拷贝 选项数据，防止被修改
     *
     * @return 拷贝的选项数据
     */
    public PageOption getInitOption() {
        return initOption;
//        if(initOption==null){
//            return null;
//        }
//        RetryOption opt = new RetryOption();
//        ReflectUtil.copyField(initOption, opt, ReflectUtil.GetClzOpt.ALL, true, new String[]{"retryTabMeta"}, null);
//        RetryTableMetaConf meta = new RetryTableMetaConf();
//        ReflectUtil.copyField(initOption.getRetryTabMeta(), meta, ReflectUtil.GetClzOpt.ALL, true, null, null);
//        opt.setRetryTabMeta(meta);
//        return opt;
    }

    public void setInitOption(RetryOption initOption) {
        this.initOption = initOption;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RetryData getRetryData() {
        return retryData;
    }

    public void setId(String id) {
        this.retryData.setId(id);
    }

    @Override
    public String getId() {
        return this.retryData.getId();
    }

    public void setLastRetriedTs(Long lastRetriedTs) {
        this.retryData.setLastRetriedTs(lastRetriedTs);
    }

    public void setRetriedCnt(Long retriedCnt) {
        this.retryData.setRetriedCnt(retriedCnt);
    }


    public void setFail() {
        this.retryData.setRes(CommonConstant.NO);//失败
    }

    public void setSucc() {
        this.retryData.setRes(CommonConstant.YES);//ok
    }

    @Override
    public void setFailReason(String reason) {
        this.retryData.setFailReason(reason);
    }

    @Override
    public String getFailReason() {
        return this.retryData.getFailReason();
    }

    public T getData() {
        return data;
    }
}
