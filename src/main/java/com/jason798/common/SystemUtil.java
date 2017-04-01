package com.jason798.common;


import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SystemUtil {

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getPid(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        return pid;
    }

    /**
     * add classpath
     * @param path absolute path
     * @throws NoSuchMethodException
     * @throws MalformedURLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void addClasspath(String path) throws Exception {
        File programRootDir = new File(path);
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        add.setAccessible(true);
        add.invoke(classLoader, programRootDir.toURI().toURL());
    }

}
