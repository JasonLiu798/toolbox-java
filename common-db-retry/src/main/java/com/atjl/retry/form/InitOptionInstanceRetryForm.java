package com.atjl.retry.form;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.param.NumRange;
import com.atjl.validate.validator.noparam.Require;

public class InitOptionInstanceRetryForm extends RetryOptionForm {
    /**
     * 重试调度默认 x分钟被调用一次
     * 如果需要重试时间大于x分钟，需要设定此函数返回y，
     * 最终重试间隔=x*y
     * 默认 5min*6=30 分钟，重试一次
     *
     * @return 重试间隔倍数
    private int multiple = 6;

     * 默认使用 固定间隔
     * 新版实现可变间隔
    private boolean fixRate = true;

    private Long retryMaxCount;//重试最大次数
     * 执行 重试操作时，出现异常是否立即重试
    private boolean exceptionInstanceRetry = false;

     * 立即重试次数
    private int exceptionInstanceRetryCount = 1;
    exceptionInstanceRetryWaitMs
     */

    /**
     *
     */
    StringField exceptionInstanceRetryCount = new StringField("立即重试次数", new Require(), new NumRange(1L, 5L));

    /**
     * 不超过 10秒
     */
    StringField exceptionInstanceRetryWaitMs = new StringField("立即重试等待时间", new Require(), new NumRange(0L, 10 * 1000L));
}
