package com.atjl.jetty.util;


import com.atjl.jetty.constant.JettyPathUtil;

import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.file.FileUtil;
import com.atjl.util.file.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassPathUtil {
    //    public static final String PATH_LIB =
    private static final Logger logger = LoggerFactory.getLogger(ClassPathUtil.class);

    private String base;
    private String project;

    public static final String BUILD_FILE = "build.gradle";
    public static final String BUILD_MAIN = PathUtil.join("build", "classes", "main");
    //src\main\resources
//    public static final String RESOURCE_MAIN = PathUtil.join("src", "main", "resources");
    //build/resources/main
    public static final String RESOURCE_MAIN = PathUtil.join("build", JettyPathUtil.RESOURCES, "main");

    public static final String RESOURCE_RAW_MAIN = PathUtil.join("src", "main", JettyPathUtil.RESOURCES);
    //D:\project\fork_eims_011746041\project\inv-fetcher-oms-rest-webapp\src\main\jetty-base\deploy\resources
    public static final String RESOURCE_DIR = PathUtil.join("src", "main", "jetty-base", "deploy", "resources");

//    public static final String RESOURCE_RAW_DIR = PathUtil.join("src", "main", "jetty-base", "deploy", "resources");

    public static final String PRJ_PT = "project('";
    public static final String PRJ_RPT = "')";
    public static final String PRJ_GRADLE_PREFIX = ":project:";

    public static final String RPATH_WEB_INF = "WEB-INF";
    private static final String RPATH_PRJ = "project";
    private static final String RPATH_JTY_BASE = PathUtil.join("src", "main", "jetty-base");

    public static final String CLASS_PATH_FILE = ".classpath";

    public ClassPathUtil(String base) {
        this.base = base;
        this.project = PathUtil.join(base, "project");
    }

    public String getCpStr(String module, String[] execludeModule) {
        List<String> cps = getCps(module, execludeModule);
        return format(cps);
    }

    public List<String> getTree(String module) throws IOException {
        List<String> modules = new LinkedList<>();
        modules.add(module);
        recuGetModule(module, modules, 3, 0);
        return modules;
    }


    public String getJndi(String module) {
        return PathUtil.join(base, "project", module, RESOURCE_DIR, "novatar-jndi-ds.xml");
    }

    public void checkCp(String module) {
        List<String> cps = getCps(module, null);
        for (String cp : cps) {
            if (FileUtil.dirExist(cp)) {
                List<String> l = FileUtil.ls(cp);
                if (l.isEmpty()) {
                    logger.info("###########WARN dir {} child null", cp);
                }
            }
        }
    }

    public String format(List<String> cps) {
        StringBuilder sb = new StringBuilder();
        for (String cp : cps) {
            sb.append(cp).append(";");
        }
        return sb.toString();
    }

    public String getWebInf(String module) {
        return PathUtil.join(this.getWebApp(module), "WEB-INF");
    }

    public String getJettyBase(String module) {
        return PathUtil.join(this.project, module, "src", "main", "jetty-base");
    }

    public String getWebApp(String module) {
        return PathUtil.join(this.project, module, "src", "main", "webapp");
    }

    /**
     * 获取 classpath
     *
     * @param module
     * @param execludeModule
     * @return
     * @throws IOException
     */
    public List<String> getCps(String module, String[] execludeModule) {
        List<String> res = new LinkedList<>();
        //jars classpath
        String[] execludes = {"dev-only", "jetty-ext"};
//        String[] execludes = {"dev-only"};//, "jetty-ext"};
        List<String> jars = this.getJars("lib", execludes);
        res.addAll(jars);

        //get modules
        List<String> modules = new LinkedList<>();
        modules.add(module);
        recuGetModule(module, modules, 0, 0);
        modules = ModulUtil.filterExecludeModule(modules, execludeModule);

        //module class classpath
        List<String> moduleClasspaths = this.getModuleClasspath(modules);
        res.addAll(moduleClasspaths);
        logger.debug("######modules:{}#########", moduleClasspaths);

        //module 涉及 gradle jar包
        List<String> gjars = this.getModuleGradleJars(modules, new String[]{"antlr"});
        res.addAll(gjars);
//        logger.debug("######gradle jars:{}#########",gjars);

        //获取各模块 resource路径
        List<String> resources = this.getModuleResource(modules);
        res.addAll(resources);
        logger.debug("######resources:{}##########", resources);
        return res;
    }


    public List<String> getModuleGradleJars(List<String> modules, String[] whiteJars) {
        List<String> cpFiles = new LinkedList<>();
        for (String module : modules) {
            String cpFile = PathUtil.join(this.project, module, CLASS_PATH_FILE);
            cpFiles.add(cpFile);
        }
        //System.out.println(cpFiles);
        List<String> jars = ClasspathEntryDao.readAllPath(cpFiles, whiteJars);
        return jars;
    }

    /*
    public List<String> getModuleGradleJars(String module){
        //get .classpath file path
        String classPathFile = PathUtil.join(this.project, module , CLASS_PATH_FILE);
        try {
            Set<String> ClasspathEntryDao.read(classPathFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
//        String classPathFile =
        //read xml
        //<classpathentry sourcepath="C:/Users/01174604/.gradle/caches/modules-2/files-2.1/commons-beanutils/commons-beanutils/1.9.2/53746de93a0b30f346c082074a40c01a6cebdfa7/commons-beanutils-1.9.2-sources.jar" kind="lib" path="C:/Users/01174604/.gradle/caches/modules-2/files-2.1/commons-beanutils/commons-beanutils/1.9.2/7a87d845ad3a155297e8f67d9008f4c1e5656b71/commons-beanutils-1.9.2.jar" exported="true"/>
        //kind=lib  sourcepath存在，则使用 path
        return null;
    }*/


    /**
     * 1.jars
     */
    public List<String> getJars(String innerPath, String[] execludes) {
        String libPath = this.base;
        if (StringCheckUtil.isNotEmpty(innerPath)) {
            libPath = PathUtil.join(this.base, innerPath);
        }
        List<String> files = FileUtil.tree(libPath);
        if (CollectionUtil.isNotEmpty(execludes)) {
            for (String e : execludes) {
                Iterator<String> i = files.iterator();
                while (i.hasNext()) {
                    String f = i.next();
                    if (f.indexOf(e) >= 0) {
                        i.remove();
                    }
                }
            }
        }
        return files;
    }


    /**
     * @return
     * @throws IOException
     */
    public List<String> getModuleClasspath(List<String> modules) {
        List<String> moduleClassPaths = new LinkedList<>();
        for (String m : modules) {
            List<String> moduleClassPath = getModuleBuildClassPath(m);
            moduleClassPaths.addAll(moduleClassPath);
        }
        return moduleClassPaths;
    }


    /**
     * 获取 各模块 resource 路径
     *
     * @param modules
     * @return
     */
    public List<String> getModuleResource(List<String> modules) {
        List<String> moduleResources = new LinkedList<>();
        for (String m : modules) {
            List<String> moduleClassPath = getModuleResourcePath(m);
            moduleResources.addAll(moduleClassPath);
        }
        return moduleResources;
    }


    /**
     * D:/project/fork_eims_011746041/project/inv-test/build/classes/main;
     *
     * @param module
     * @return
     */
    public List<String> getModuleBuildClassPath(String module) {
        List<String> list = new LinkedList<>();
        if (isWebPrj(module)) {
            //add resource dir
            String resourcePath = PathUtil.join(this.project, module, RESOURCE_DIR);
            list.add(resourcePath);
            return list;
        }
        String main = PathUtil.join(this.project, module, BUILD_MAIN);
        list.add(main);
        return list;
    }

    /**
     * 获取 resource文件
     * D:/project/fork_eims_011746041/project/inv-test/build/resources/main;
     *
     * @param module
     * @return
     * @throws IOException
     */
    public List<String> getModuleResourcePath(String module) {
        List<String> list = new LinkedList<>();
        if (isWebPrj(module)) {
            //add resource dir
//            String resourcePath = PathUtil.join(this.project, module, RESOURCE_DIR);
//            list.add(resourcePath);
            return list;
        }
//        String main = PathUtil.join(this.project, module, RESOURCE_MAIN);
        String main = PathUtil.join(this.project, module, RESOURCE_RAW_MAIN);
        list.add(main);
        return list;
    }


    /**
     * @param module
     * @param modules store modules
     * @param option  bit0,0= no duplicate items / 1 duplicate
     *                bit1,0= no space / 1= add level space
     *                0=normal
     *                3=tree
     *                2=tree no duplicate
     */
    public void recuGetModule(String module, List<String> modules, int option, int level) {
        List<String> gradleContents = getGradleBuildFile(module);
        String regex = "^" + RegexUtil.FRAG_SPACES_OR_NE + "project\\(" + RegexUtil.FRAG_NOT_SPACES + "\\),*$";
//        if (logger.isDebugEnabled()) {
//            logger.debug("other module pattern {}", regex);
//        }
        Pattern p = RegexUtil.getPattern(regex);

        //List<String> otherModules = new LinkedList<>();
        for (String gc : gradleContents) {
            Matcher m = p.matcher(gc);
            if (m.find()) {
                int start = gc.indexOf(PRJ_PT);
                if (start < 0) {
                    continue;
                }
                start += PRJ_PT.length() + PRJ_GRADLE_PREFIX.length();
                if (start >= gc.length()) {
                    continue;
                }
                int end = gc.indexOf(PRJ_RPT);
                if (end >= gc.length()) {
                    continue;
                }
                String moduleName = StringUtil.trim(gc.substring(start, end));
                boolean needDuplicate = (option & 0x1) == 1;
                List<String> trimModules = StringUtil.trimAll(modules);
                if (CollectionUtil.isIn(trimModules, moduleName) && !needDuplicate) {
                    continue;
                }
                boolean addLevelSpace = (option & 0x2) == 0x2;
                String moduleNameRaw = moduleName;
                if (addLevelSpace) {
                    moduleName = StringUtil.addSpaceFront(moduleName, level, 2);
                }
                modules.add(moduleName);
                recuGetModule(moduleNameRaw, modules, option, level + 1);
                //otherModules.add(moduleNameRaw);
            }
        }
//        if (otherModules.size() > 0) {
//            for (String otherModule : otherModules) {
//
//            }
//        }
    }


//    private List<String> recuGetAllModules(String module) throws IOException {
//        List<String> modules = new LinkedList<>();
//        modules.add(module);
//        recuGetModule(module, modules, 0, 0);
//        return modules;
//    }


    public boolean isWebPrj(String module) {
        List<String> gradleContents = getGradleBuildFile(module);
        for (String gradleC : gradleContents) {
            if (gradleC.indexOf("webappProject.gradle") > 0) {
                return true;
            }
        }
        return false;
    }

    public String getModulePath(String moudle) {
        return PathUtil.join(this.base, "project", moudle);
    }

    public List<String> getGradleBuildFile(String module) {
        String mp = getModulePath(module);
        String fp = PathUtil.join(mp, BUILD_FILE);
        return FileUtil.cat2list(fp);
    }

    public static String merge(String target, String merged) {
        String res = merged + ";" + target;
        return res;
    }
}
