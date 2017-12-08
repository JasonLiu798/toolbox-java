package com.atjl.retry.manager;

import com.atjl.retry.api.exception.RetryRegisteException;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryInstanceOption;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.form.PageOptionForm;
import com.atjl.retry.form.RetryInstanceOptionForm;
import com.atjl.retry.form.RetryOptionForm;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.db.DbExecutor;
import com.atjl.util.db.DbExecutorSyntaxException;
import com.atjl.util.reflect.ReflectFieldUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class OptionManager {
    private static final Logger logger = LoggerFactory.getLogger(OptionManager.class);

    @Resource
    DataSource dataSource;


    public void checkPageOption(PageOption option) {
        ValidateForm form = ValidateFormFactory.build(PageOptionForm.class);
        //form.setValue(option);
        form.setValue(ReflectFieldUtil.getFieldValueString(option, ReflectUtil.GetClzOpt.ALL, true, null, null));
        //new String[]{"retryTabMeta"}, null));
        if (!form.validate()) {
            throw new RetryRegisteException("初始化基础配置校验失败，原因：" + form.getOneLineError());
        }
    }


    public void checkRetryInstanceOption(RetryInstanceOption option) {
        ValidateForm form = ValidateFormFactory.build(RetryInstanceOptionForm.class);
        form.setValue(ReflectFieldUtil.getFieldValueString(option, ReflectUtil.GetClzOpt.ALL, true, null, null));
//        form.setValue(option);
        //new String[]{"retryTabMeta"}, null));
        if (!form.validate()) {
            throw new RetryRegisteException("初始化立即重试相关配置校验失败，原因：" + form.getOneLineError());
        }


    }

    public void checkRetryOption(RetryOption option) {
        ValidateForm form = ValidateFormFactory.build(RetryOptionForm.class);

        form.setValue(option);
        if (!form.validate()) {
            throw new RetryRegisteException("初始化重试相关配置校验失败，原因：" + form.getOneLineError());
        }
        checkRetryTab(option.getRetryTabMeta());
    }


    /**
     * 校验 重试表 相关信息
     *
     * @param meta
     */
    public void checkRetryTab(RetryTableMetaConf meta) {
        if (meta == null) {
            throw new RetryRegisteException("重试表元信息校验失败，原因：数据为空");
        }
        ValidateForm form = ValidateFormFactory.build(RetryTableMetaConf.class);
        form.setValue(meta);
        if (!form.validate()) {
            throw new RetryRegisteException("重试表元信息校验失败，原因：" + form.getOneLineError());
        }

        String sqlraw = "select %s,%s,%s,%s from %s limit 1";
        String sql = String.format(sqlraw,
                meta.getIdCol(), meta.getResCol(),
                meta.getRetryCountCol(), meta.getFailReasonCol(),
                meta.getTab());
        try {
            logger.info("check sql {}", sql);
            DbExecutor.getTableData(dataSource, sql);
        } catch (DbExecutorSyntaxException e) {
            logger.info("checkRetryTab {}", e);
            throw new RetryRegisteException("重试表元信息校验失败，原因：" + e);
        }
    }

//    /**
//     * 校验 初始化 选项
//     *
//     * @param option
//     */
//    public void checkOption(RetryOption option) {
//        ValidateForm form;
//        if (option.isExceptionInstanceRetry()) {
//            form = ValidateFormFactory.build(InitOptionInstanceRetryForm.class);
//        } else {
//            form = ValidateFormFactory.build(RetryOptionForm.class);
//        }
//
//        /**
//         *     public static Map<String, Field> getFieldMap(Class obj, GetClzOpt parentOpt, String[] blackArr, String[] whiteArr) {
//         */
//        form.setValue(ReflectFieldUtil.getFieldValueString(option, ReflectUtil.GetClzOpt.ALL, true, null, null));
//        //new String[]{"retryTabMeta"}, null));
//        if (!form.validate()) {
//            throw new RetryExecuteException("初始化配置校验失败，原因：" + form.getOneLineError());
//        }
//        checkRetryTab(option.getRetryTabMeta());
//        logger.info("check retryservice {} pass,content {}", option.getServiceName(), JSONFastJsonUtil.objectToJson(option));
//    }


}
