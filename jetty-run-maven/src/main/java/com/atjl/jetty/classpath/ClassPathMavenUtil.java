package com.atjl.jetty.classpath;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.file.FileUtil;
import com.atjl.util.file.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClassPathMavenUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathMavenUtil.class);

    private String root;//根目录
    private String[] modules;//模块
    private String webprj;//web目录
    private String profile;//

    private static final String TARGET = PathUtil.join("target", "classes");

    public ClassPathMavenUtil(String profile, String base, String webprj, String[] modules) {
        this.profile = profile;
        this.root = base;
        this.modules = modules;
        this.webprj = webprj;
    }


    public String getJndiPath() {
        return PathUtil.join(this.root, this.webprj, "src", "main", "config", this.profile, "novatar-jndi-ds.xml");
    }


    /**
     * 获取 classpath
     */
    public List<String> getCps(String[] libPath) {
        List<String> res = new LinkedList<>();
        //jar包
        List<String> jars = this.getJars(libPath, null);
        res.addAll(jars);
//        logger.info("######jars:{}#########", jars);

        //module
        List<String> moduleClasspaths = this.getModuleClasspath();
        //assemble 插件配置
        moduleClasspaths.add(this.getAssembleConfig());

        //web
        moduleClasspaths.addAll(this.getWebPath());
        res.addAll(moduleClasspaths);
        logger.info("modules " + moduleClasspaths);
        return res;
    }


    private List<String> getWebPath() {
        List<String> res = new ArrayList<>();
        if (!StringCheckUtil.isEmpty(this.webprj)) {
            String conf = PathUtil.join(this.root, this.webprj, "src", "main", "config", this.profile);
            res.add(conf);
            String resource = PathUtil.join(this.root, this.webprj, "src", "main", "resources");
            res.add(resource);
            return res;
        }
        return res;
    }

    private String getAssembleConfig() {
        return PathUtil.join(this.root, this.webprj, "src", "main", "assembly", this.profile);
    }


    /**
     * 获取module path
     */
    public List<String> getModulePath() {
        List<String> moduleClasspaths = new ArrayList<>();
        if (!CollectionUtil.isEmpty(this.modules)) {
            for (String m : this.modules) {
                String modulePath = this.root + "\\" + m;
                moduleClasspaths.add(modulePath);
                //List<String> modules = FileUtil.lsPrefix(this.root,"aos",new String[]{".iml","web"});
            }
        }
//        logger.info("modules " + moduleClasspaths);
        return moduleClasspaths;
    }

    /**
     * 通过指定jar包 目录，获取jar包
     */
    public List<String> getJars(String[] libpath, String[] execludes) {
        List<String> res = new ArrayList<>();
        if (CollectionUtil.isEmpty(libpath)) {
            return res;
        }
        for (String s : libpath) {
            List<String> tmp = getOneDirJars(s, execludes);
            if (!CollectionUtil.isEmpty(tmp)) {
                res.addAll(tmp);
            }
        }
        return res;
    }

    public List<String> getOneDirJars(String libpath, String[] execludes) {
        List<String> files = FileUtil.tree(libpath);
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
     * 获取所有 maven module所需的classpath
     * 主要包括：target路径
     */
    public List<String> getModuleClasspath() {
        List<String> moduleClassPaths = new LinkedList<>();
        for (String m : this.getModulePath()) {
            List<String> moduleClassPath = getModuleTarget(m);
            moduleClassPaths.addAll(moduleClassPath);
        }
        return moduleClassPaths;
    }


    /**
     * 获取target路径
     */
    public List<String> getModuleTarget(String module) {
        List<String> list = new LinkedList<>();
        String main = PathUtil.join(module, TARGET);
        list.add(main);
        return list;
    }

}
