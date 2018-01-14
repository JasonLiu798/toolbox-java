package com.atjl.fmt.context;


import com.atjl.fmt.util.FmtChkUtil;
import com.atjl.util.collection.CollectionUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 暂不用：性能优化用
 */
public class FmtContext {
    private FmtContext() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(FmtContext.class);

    private static Configuration conf;

    private static Map<String, String> templateMap;

    public static void initConf(Map<String, String> templateMap) {
        Configuration conf = constructConfig();

        addTemplateLoader(conf, templateMap);
        if (!FmtChkUtil.validTemplate(CollectionUtil.map2list(templateMap, false))) {

        }
    }

    /**
     * @return
     */
    public static Configuration constructConfig() {
        Configuration stringTemplateCfg = new Configuration();
        stringTemplateCfg.setObjectWrapper(new DefaultObjectWrapper());
        stringTemplateCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return stringTemplateCfg;
    }

    /**
     * 添加模板
     *
     * @param conf
     * @param templates
     */
    public static void addTemplateLoader(Configuration conf, Map<String, String> templates) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            stringLoader.putTemplate(entry.getKey(), entry.getValue());
        }
        conf.setTemplateLoader(stringLoader);
    }


    /**
     * @param templates key:模板名，value:模板内容
     * @param templates
     */
    public static void add(Map<String, String> templates) {
        //空校验
        if (CollectionUtil.isEmpty(templates)) {
            if (logger.isWarnEnabled()) {
                logger.warn("add template null");
            }
            return;
        }
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            //存在校验
            if (templateMap.containsKey(entry.getKey())) {
                logger.warn("add template exist,{}", entry.getKey());
                continue;
            }

//            templateMap.putAll(templates);
        }
    }
}
