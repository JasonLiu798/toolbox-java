package com.atjl.util.file;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CmdOptionUtil;
import com.atjl.util.common.DateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileBrowserUtil {

    /**
     * permission size lastmodified name
     */
    private static final String FORMAT_LS = "%s %s %s %s";


    /**
     * traverse dir
     *
     * @param dirpath
     * @param res
     */
    public static void traverseDir(String dirpath, List<String> res) {
        File parent = new File(dirpath);
        File[] childs = parent.listFiles();
        if (CollectionUtil.isEmpty(childs)) {
            return;
        }
        for (File c : childs) {
            if (c.isDirectory()) {
                traverseDir(PathUtil.join(dirpath, c.getName()), res);
            } else {
                res.add(PathUtil.join(dirpath, c.getName()));
            }
        }
    }

    /**
     * @param dirPath
     * @param option
     * @param list
     */
    public static void lsInner(String dirPath, String option, List<String> list) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] childs = file.listFiles();

            //添加自身
            if (CmdOptionUtil.hasOption(option, "-R")) {
                list.add(file.getAbsolutePath() + ":");
            }
            if (CollectionUtil.isEmpty(childs)) {
                return;
            }
            //分离 目录、普通文件
            List<File> childFiles = new ArrayList<>();
            List<File> childDirs = new ArrayList<>();
            for (File c : childs) {
                if (c.isDirectory()) {
                    childDirs.add(c);
                } else {
                    childFiles.add(c);
                }
            }
            //添加 普通文件
            if (!CollectionUtil.isEmpty(childFiles)) {
                for (File f : childFiles) {
                    addAndFormatFile(list, f, option, file.isDirectory());
                }
            }
            if (CmdOptionUtil.hasOption(option, "-R")) {
                list.add("");
            }

            //添加 目录及子节点
            if (!CollectionUtil.isEmpty(childDirs)) {
                for (File f : childDirs) {
                    lsInner(f.getAbsolutePath(), option, list);
                }
            }
        } else {
            addAndFormatFile(list, file, option, file.isDirectory());
        }
    }

    private static void addAndFormatFile(List<String> list, File f, String option, boolean isDir) {
        //加入自身 和 子文件
        if (CmdOptionUtil.hasOption(option, "-l")) {
            if (CmdOptionUtil.hasOption(option, "-h")) {
                list.add(formatFile(f, "-h"));
            } else {
                list.add(formatFile(f));
            }
        } else {
//            if (isDir) {
//                if (CmdOptionUtil.hasOption(option, "-R")) {
//                    list.add(f.getAbsolutePath() + ":");
//                } else {
//                    list.add(f.getName());
//                }
//            } else {
            list.add(f.getAbsolutePath());
//            }
        }
    }


    private static String formatFile(File f) {
        return formatFile(f, null);
    }

    /**
     * 格式化 文件
     *
     * @param f
     * @param opt
     * @return
     */
    private static String formatFile(File f, String opt) {
        String permission = formatPermit(f);
        String size = String.valueOf(f.length());
        if (StringCheckUtil.equal(opt, "-h")) {
            if (f.length() > 1024 && f.length() < (2L << 20)) {
                size = (f.length() >> 10) + "K";
            } else if (f.length() > (2L << 20) && f.length() < (2L << 30)) {
                size = (f.length() >> 20) + "M";
            } else if (f.length() > 2L << 30) {
                size = (f.length() >> 30) + "G";
            }
        }
        String lastModified = DateUtil.formatDefault(DateUtil.tsms2Date(f.lastModified()));
        String name = f.getName();
        return String.format(FORMAT_LS, permission, size, lastModified, name);
    }

    private static String formatPermit(File f) {
        String res = "";
        if (!f.exists()) {
            return res;
        }
        if (f.isDirectory()) {
            res += "d";
        }
        if (f.canRead()) {
            res += "r";
        } else {
            res += "-";
        }
        if (f.canWrite()) {
            res += "w";
        } else {
            res += "-";
        }
        if (f.canExecute()) {
            res += "x";
        } else {
            res += "-";
        }
        return res;
    }


}
