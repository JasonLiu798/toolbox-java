package com.atjl.retry.util;


import com.atjl.retry.api.AfterService;
import com.atjl.retry.api.CustomGetDatas;
import com.atjl.retry.api.CustomGetDatasUseIdPage;
import com.atjl.retry.api.exception.RetryInitException;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.util.reflect.ReflectClassUtil;

public class RetryCheckUtil {
    private RetryCheckUtil() {
        throw new UnsupportedOperationException();
    }

    public static void customAfter(InitOption opt, Object srv, RetryServiceItem retryServiceItem) {
        if (OptionUtil.isCustomAfter(opt)) {
            if (!ReflectClassUtil.chkAImplementB(srv, AfterService.class)) {
                throw new RetryInitException("自定义后置服务，但未实现 AfterService 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setAfterService((AfterService) srv);
        }
    }

    public static void customGetData(InitOption opt, Object srv, RetryServiceItem retryServiceItem) {
        if (opt.getGetDataType() == GetDataType.CUSTOM_USEID) {
            if (!ReflectClassUtil.chkAImplementB(srv, CustomGetDatas.class)) {
                throw new RetryInitException("自定义取数，但未实现 CustomGetDatas 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setRetryServiceCustomGetDatas((CustomGetDatas) srv);
        } else if (opt.getGetDataType() == GetDataType.CUSTOM_USEID) {
            if (!ReflectClassUtil.chkAImplementB(srv, CustomGetDatasUseIdPage.class)) {
                throw new RetryInitException("自定义取数，CustomGetDatasUseIdPage 接口，服务名" + opt.getServiceName());
            }
            retryServiceItem.setRetryServiceGetDatasUseIdPage((CustomGetDatasUseIdPage) srv);
        }
    }

}
