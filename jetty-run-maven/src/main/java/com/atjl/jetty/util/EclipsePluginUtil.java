package com.atjl.jetty.util;

import com.atjl.jetty.classpath.ClassPathMavenUtil;
import com.atjl.jetty.constant.EclipsePluginConstant;
import com.atjl.util.file.FileUtil;
import com.atjl.util.file.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EclipsePluginUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathUtil.class);

    public EclipsePluginUtil() {
        super();
    }

    private ClassPathUtil cpu;


    public EclipsePluginUtil(ClassPathUtil cpu) {
        this.cpu = cpu;
    }

    public Map<String, String> getConfig(int port, String module, String[] execludeModule) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put(EclipsePluginConstant.PORT, Integer.toString(port));
        logger.info("PORT: {}", port);
        String webappPath = cpu.getWebApp(module);
        map.put(EclipsePluginConstant.WEB_PATH, webappPath);
        logger.info("WEB_PATH: {}", webappPath);
        map.put(EclipsePluginConstant.WEB_CONTEXT, "/");
        logger.info("WEB_CONTEXT: {}", "/");
        String dftDesc = PathUtil.join(cpu.getJettyBase(module), "etc", "webdefault.xml");
        map.put(EclipsePluginConstant.DFT_DESC, dftDesc);
        logger.info("DFT_DESC: {}", dftDesc);
        String webinfo = cpu.getWebInf(module);
        logger.info("WEB-INFO: {}", webinfo);

        //生成 classpath
        String classPathStr = cpu.getCpStr(module, execludeModule);
//        logger.info("classpath modules: {}",classPathStr);


        classPathStr = ClassPathUtil.merge(classPathStr, webinfo);
        map.put(EclipsePluginConstant.EXTRA_CLASSPATH, classPathStr);
//        logger.info("classpath with extra: {}",classPathStr);
        return map;
    }


    public Map<String, String> getConfigMavenWar(int port, String profile, String webprjName, String base) throws IOException {
        return getConfigMaven(port, profile, webprjName, base, true, null, null);
    }

    public Map<String, String> getConfigMaven(int port, String profile, String webprjName, String base, boolean useWar, String[] lib, String[] modules) throws IOException {
//        String dftWebXml = "";
//        if (!StringCheckUtil.isEmpty(webdft)) {
//            dftWebXml = webdft;
//        } else {
//        }

//        dftWebXml = FileUtil.getFilePathFromClasspath("webdefault.xml");

        Map<String, String> map = new HashMap<>();
        map.put(EclipsePluginConstant.PORT, String.valueOf(port));//port
        String webpath = PathUtil.join(base, webprjName, "src", "main", "webapp");
        if (useWar) {
            webpath = PathUtil.join(base, webprjName);
        }
//        String webpath = PathUtil.join(base, webprjName, "src", "main", "webapp");
        map.put(EclipsePluginConstant.WEB_PATH, webpath);//web
        map.put(EclipsePluginConstant.WEB_CONTEXT, "/");
//        map.put(EclipsePluginConstant.DFT_DESC, dftWebXml);

        ClassPathMavenUtil c = new ClassPathMavenUtil(profile, base, webprjName, modules);

        List<String> cpList = c.getCps(lib);
        String cps = JettyCommonUtil.format(cpList);

        map.put(EclipsePluginConstant.EXTRA_CLASSPATH, cps);
        map.put("jndi", c.getJndiPath());
        return map;
    }


    public String generateXmlOneLine(String template, String destPath, Map<String, String> config) {

        String c = template;//FileUtil.cat(template);
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String tagRaw = entry.getKey();
            String content = entry.getValue();
            int tagRawStartIndex = c.indexOf(tagRaw);
            if (tagRawStartIndex >= 0) {
                logger.debug("config:{},content:{}", tagRaw, content);
                if (content == null) {//allow ""
                    continue;
                }
                c = c.replace(EclipsePluginConstant.TAG_START + tagRaw + EclipsePluginConstant.TAG_END, content);
            }
        }
        FileUtil.writeForce(destPath, c);
        return destPath;
    }

    /**
     * 生成 xml
     *
     * @param srcPath
     * @param destPath
     * @param config
     * @return
     */
    public String generateXml(String srcPath, String destPath, Map<String, String> config) {
        List<String> contents = FileUtil.cat2list(srcPath);
        int idx = 0;
        for (String c : contents) {
            int tagStartIndex = c.indexOf(EclipsePluginConstant.TAG_START);
            int tagEndIndex = c.indexOf(EclipsePluginConstant.TAG_END);
            if (tagStartIndex >= 0 && tagEndIndex >= 0 && tagStartIndex < tagEndIndex) {
                String tag = c.substring(tagStartIndex + EclipsePluginConstant.TAG_START.length(), tagEndIndex);
                String configContent = config.get(tag);
                logger.debug("config:{},content:{}", tag, configContent);
                if (configContent == null) {//allow ""
                    continue;
                }
                c = c.replace(EclipsePluginConstant.TAG_START + tag + EclipsePluginConstant.TAG_END, configContent);
                contents.set(idx, c);
            }
            idx++;
        }
        FileUtil.writeForce(destPath, contents);
        //System.out.println(contents);
        return destPath;
    }
}
