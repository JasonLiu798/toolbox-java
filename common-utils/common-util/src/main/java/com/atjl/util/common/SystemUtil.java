package com.atjl.util.common;


import com.atjl.util.number.RandomUtil;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 系统相关类
 * <p>
 * sleep封装
 * 获取pid
 * 手动添加 classpath
 */
public class SystemUtil {
    private SystemUtil() {
        throw new UnsupportedOperationException();
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepRandom(int max, int dft) {
        int randSleepTm;
        try {
            randSleepTm = RandomUtil.generateInt(0, max);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            randSleepTm = dft;
        }
        sleep(randSleepTm);
    }

    public static void sleepForever() {
        sleep(Integer.MAX_VALUE);
    }

    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        return pid;
    }

    /**
     * add classpath
     *
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
