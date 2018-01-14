package com.atjl.jetty.api;

import com.atjl.jetty.constant.EclipsePluginConstant;
import com.atjl.jetty.util.ClassPathUtil;
import com.atjl.jetty.util.EclipsePluginUtil;
import com.atjl.util.constant.SystemConstant;
import com.atjl.util.file.FileUtil;
import net.sourceforge.eclipsejetty.starter.jetty9.Jetty9LauncherMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 *
 */
public class JettyPluginUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathUtil.class);

    //sdjklfklsdklfkjlds
    private JettyPluginUtil() {
        super();
    }


    public static void startServerUsePluginWar(int port, String profile, String base, String webPrj) throws Exception {//}, String[] modules, String[] lib, boolean jndi, boolean useWar){
//, String webdftPath
        //ClassPathMavenUtil cpu = new ClassPathMavenUtil(base);
        EclipsePluginUtil pluginUtil = new EclipsePluginUtil();
        Map<String, String> configs = pluginUtil.getConfigMavenWar(port, profile, webPrj, base);//, webdftPath);

        String tmpXml = SystemConstant.TMP_DIR + "tmp.xml";
        logger.info("use tmp xml {}", tmpXml);
        File templateFile = FileUtil.getFileFromClasspath("templateOneLine.xml");
        String template = FileUtil.cat(templateFile);
        String templateFilePath = FileUtil.getFilePathFromClasspath("templateOneLine.xml");
        templateFilePath = templateFilePath.substring(1);
        logger.info("use template {}", templateFilePath);
        pluginUtil.generateXmlOneLine(template, tmpXml, configs);
//        String conf = tmpXml;
//        if () {
//            String jndiFile = configs.get(EclipsePluginConstant.JNDI);
//            conf = tmpXml + File.pathSeparator + jndiFile;
//        }
        System.setProperty("jetty.launcher.configuration", tmpXml);//conf);
        Jetty9LauncherMain.main(new String[]{});
    }

    /**
     * 通过 eclipse-jetty插件 启动服务
     *
     * @param port
     * @param base
     * @param lib
     * @throws Exception
     */
    public static void startServerUsePlugin(int port, String profile, String base, String webPrj, String[] modules, String[] lib, boolean jndi, boolean useWar) throws Exception {
        //, String webdftPath
        //ClassPathMavenUtil cpu = new ClassPathMavenUtil(base);
        EclipsePluginUtil pluginUtil = new EclipsePluginUtil();

        Map<String, String> configs = pluginUtil.getConfigMaven(port, profile, webPrj, base, useWar, lib, modules);//, webdftPath);

        String tmpXml = SystemConstant.TMP_DIR + "tmp.xml";
        logger.info("use tmp xml {}", tmpXml);
        File templateFile = FileUtil.getFileFromClasspath("templateOneLine.xml");
        String template = FileUtil.cat(templateFile);
        String templateFilePath = FileUtil.getFilePathFromClasspath("templateOneLine.xml");
        templateFilePath = templateFilePath.substring(1);
        logger.info("use template {}", templateFilePath);
        pluginUtil.generateXmlOneLine(template, tmpXml, configs);
        String conf = tmpXml;
        if (jndi) {
            String jndiFile = configs.get(EclipsePluginConstant.JNDI);
            conf = tmpXml + File.pathSeparator + jndiFile;
        }
        System.setProperty("jetty.launcher.configuration", conf);
        Jetty9LauncherMain.main(new String[]{});
    }

}
