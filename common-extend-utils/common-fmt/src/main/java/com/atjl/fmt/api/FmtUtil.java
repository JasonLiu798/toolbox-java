package com.atjl.fmt.api;


import com.atjl.fmt.context.FmtContext;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FmtUtil {
    private FmtUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(FmtUtil.class);
    private static final String DFT_TEMPLATE_NAME = "dftTemplate";

//    private static Configuration conf;
//    private static Map<String, String> templateMap;

//    public static void init(Map<String, String> templates) {
//        FmtContext.constructConfig();
//        templateMap = templates;
//    }

    /**
     * 渲染
     * @param templateContent
     * @param parameters
     * @return
     */
    public static String render(String templateContent, Map<String, String> parameters) {
        try {
            Configuration cfg = new Configuration();
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(DFT_TEMPLATE_NAME, templateContent);
            cfg.setTemplateLoader(stringLoader);
            Template template = cfg.getTemplate(DFT_TEMPLATE_NAME, "utf-8");
            StringWriter writer = new StringWriter();
            template.process(parameters, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            if (logger.isErrorEnabled()) {
                logger.error("render {}", e);
            }
        }
        return null;
    }


}
