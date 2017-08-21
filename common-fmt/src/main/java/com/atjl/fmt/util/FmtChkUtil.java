package com.atjl.fmt.util;


import com.atjl.util.collection.CollectionUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class FmtChkUtil {
    private FmtChkUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(FmtChkUtil.class);
    private static final String DFT_TEMPLATE_NAME = "valid";

    /**
     * 模板合法性校验
     *
     * @param template 要校验的模板
     * @return
     */
    public static boolean validTemplate(String template) {
        return validTemplate(CollectionUtil.newList(template));
    }

    /**
     * 模板合法性校验，批量
     *
     * @param templates 要校验的模板列表
     * @return
     */
    public static boolean validTemplate(List<String> templates) {
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        int i = 0;
        for (String t : templates) {
            stringLoader.putTemplate(DFT_TEMPLATE_NAME + i, t);
            i++;
        }
        cfg.setTemplateLoader(stringLoader);
        try {
            for (i = 0; i < templates.size(); i++) {
                cfg.getTemplate(DFT_TEMPLATE_NAME + i, "utf-8");
            }
            return true;
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("render {}", e);
            }
            return false;
        }
    }
}
