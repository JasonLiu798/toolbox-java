package com.atjl.retry.util;


import com.atjl.retry.api.*;
import com.atjl.retry.api.exception.RetryRegisteException;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.util.reflect.ReflectClassUtil;

public class RetryCheckUtil {
    private RetryCheckUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 自定义后置 校验
     */
    public static void customAfter(PageOption opt, Object srv, RetryServiceItem retryServiceItem) {
        if (OptionUtil.isCustomAfter(opt)) {
            if (!ReflectClassUtil.chkAImplementB(srv, AfterService.class)) {
                throw new RetryRegisteException("自定义后置服务，但未实现 " + AfterService.class.getName() + " 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setAfterService((AfterService) srv);
        }
    }

    /**
     * 自定义取数 校验
     */
    public static void customGetData(PageOption opt, Object srv, RetryServiceItem retryServiceItem) {
        boolean isCustom = false;
        if (opt.isBatchProcess()) {
            if (!ReflectClassUtil.chkAImplementB(srv, CustomGetSimpleDatas.class)) {
                throw new RetryRegisteException("自定义取数，但未实现 " + CustomGetSimpleDatas.class.getName() + " 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setCustomGetSimpleDatas((CustomGetSimpleDatas) srv);
            isCustom = true;
        } else {
            if (opt.getGetDataType() == GetDataType.CUSTOM_USEPAGE) {
                if (!ReflectClassUtil.chkAImplementB(srv, CustomGetDatas.class)) {
                    throw new RetryRegisteException("自定义取数，但未实现 " + CustomGetDatas.class.getName() + " 接口，服务名" + opt.getServiceName());
                }
                retryServiceItem.setCustomGetDatas((CustomGetDatas) srv);
                isCustom = true;
            } else if (opt.getGetDataType() == GetDataType.CUSTOM_USEID) {
                if (!ReflectClassUtil.chkAImplementB(srv, CustomGetDatasUseIdPage.class)) {
                    throw new RetryRegisteException("自定义取数，但未实现 " + CustomGetDatasUseIdPage.class.getName() + " 接口，服务名" + opt.getServiceName());
                }
                retryServiceItem.setCustomGetDatasUseIdPage((CustomGetDatasUseIdPage) srv);
                isCustom = true;
            }
        }
        if (isCustom) {
            if (!ReflectClassUtil.chkAImplementB(srv, CustomGetCount.class)) {
                throw new RetryRegisteException("自定义取数，但未实现 " + CustomGetCount.class.getName() + " 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setCustomGetCount((CustomGetCount) srv);
        }
    }

}
