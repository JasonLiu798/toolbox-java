package com.jason798.common;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import java.util.LinkedList;
import java.util.List;

/**
 * path util
 * https://nodejs.org/docs/latest/api/path.html
 */
public class PathUtil {

    /**
     * dir separator ("/" on UNIX,"\" on Win)
     */
    public static final String DIR_SEP = System.getProperty("file.separator");
    public static final String DIR_SEP_POSIX = "/";
    public static final String DIR_SEP_WIN = "\\";
    /**
     * win disk separator
     * : in C:\\aa.txt
     */
    public static final String DISK_SEP_WIN = ":";
    /**
     * file extension separator
     */
    public static final String EXT_SEP = ".";
    /**
     * upper dir
     */
    public static final String UPPER_DIR = "..";
    /**
     * current dir
     */
    public static final String CURRENT_DIR = ".";

    /**
     * ######################### POSIX #########################
     * parseInner('/home/user/dir/file.txt')
     * returns
     * {
     * root : "/",
     * dir : "/home/user/dir",
     * base : "file.txt",
     * ext : ".txt",
     * name : "file"
     * }
     * <p>
     * ┌─────────────────────┬────────────┐
     * │          dir        │    base    │
     * ├──────┬              ├──────┬─────┤
     * │ root │              │ name │ ext │
     * "  /    home/user/dir / file  .txt "
     * └──────┴──────────────┴──────┴─────┘
     * (all spaces in the "" line should be ignored -- they are purely for formatting)
     * <p>
     * <p>
     * ######################### WIN #########################
     * parseInner('C:\\path\\dir\\file.txt')
     * returns
     * {
     * root : "C:\\",
     * dir : "C:\\path\\dir",
     * base : "file.txt",
     * ext : ".txt",
     * name : "file"
     * }
     * <p>
     * ┌─────────────────────┬────────────┐
     * │          dir        │    base    │
     * ├──────┬              ├──────┬─────┤
     * │ root │              │ name │ ext │
     * " C:\      path\dir   \ file  .txt "
     * └──────┴──────────────┴──────┴─────┘
     * (all spaces in the "" line should be ignored -- they are purely for formatting)
     * <p>
     * ######################### OTHER #########################
     * parseInner('||','a||path||dir||file.txt')
     * returns
     * {
     * root : "",
     * dir : "d||path||dir",
     * base : "file.txt",
     * ext : ".txt",
     * name : "file"
     * }
     *
     * @param pathStr
     * @return
     */
    public static Path parseInner(String sep, String pathStr) {
        if (StringCheckUtil.isExistEmpty(sep, pathStr)) {
            throw new IllegalArgumentException("sep or path can not be empty");
        }
        String[] str = pathStr.split(sep);
        if (CollectionUtil.isEmpty(str) || str.length == 0) {
            return null;
        }
        boolean haveBase = true;
        if (pathStr.endsWith(sep) || str.length <= 1) {
            haveBase = false;
        }
        Path resPath = new Path();
        switch (sep) {
            case DIR_SEP_POSIX:
                if(str.length==1){
                    resPath = Path.buildUnixRoot();
                }else{
                    String root = str[0];
                    resPath.setRoot(root);
                    if (haveBase) {
                        String base = str[str.length - 1];
                        Path ext = parseExt(base);
                        Path.mergeBasePart( resPath,ext);
                        String dir = pathStr.substring(0,root.length()+sep.length());
                        //TODO:
                    }else{
                        resPath.setDir(pathStr);
                    }

                }
                break;
            case DIR_SEP_WIN:
                break;
            default:

        }
        return null;
    }

    public static Path parseExt(String base) {
        String[] arr = base.split(EXT_SEP);
        return null;
    }

    public static String normalize(String path) {
        return "";
    }

    public static String relative(String from, String to) {
        return "";
    }


    /**
     * join path
     *
     * @param path
     * @return path string
     */
    public static String join(String... path) {
        if(DIR_SEP.equals(DIR_SEP_WIN)){
            return joinInnerRelative(DIR_SEP, path);
        }else{
            return joinInnerAbs(DIR_SEP, path);
        }
    }
//    public static String joinRelaive(String ... path){
//        return joinInnerRelative(DIR_SEP,path);
//    }
    public static String joinInnerRelative(String sep, String... paths) {
        return joinInner(false,sep,paths);
    }

    public static String joinInnerAbs(String sep, String... paths) {
        return joinInner(true,sep,paths);
    }

    /**
     * join with specified sep
     *
     * @param sep
     * @param paths
     * @return path string
     */
    public static String joinInner(boolean isAbsolute,String sep, String... paths) {
        if (CollectionUtil.isEmpty(paths)) {
            return "";
        }
        if (StringCheckUtil.isEmpty(sep)) {
            throw new IllegalArgumentException("sep can not be empty");
        }
        List<String> l = filterPath(sep, paths);
        StringBuilder sb = new StringBuilder();
        if (l.size() == 1) {
            return l.get(0);
        }
        for (int i = 0; i < l.size(); i++) {
            String path = l.get(i);
            sb.append(path);
        }
        if (sb.toString().startsWith(sep + sep)) {
            sb.deleteCharAt(0);
        }
        if( sb.toString().startsWith(sep) && !isAbsolute){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static List<String> filterPath(String sep, String[] paths) {
        List<String> l = new LinkedList<>();
        int i = 0;
        //boolean firstIsSep = l.get(0).equals(ZkConstant.PATH_SEPERATOR);
        for (String path : paths) {
            String af = filterPath(sep, path);
            if (StringCheckUtil.isEmpty(af)) {
                throw new NullPointerException();
            }
            if (i == 0) {
                l.add(af);
            } else {
                if (!StringCheckUtil.equal(af, sep)) {
                    l.add(af);
                }
            }
            i++;
        }
        return l;
    }

    public static String filterPath(String sep, String s) {
        if (StringCheckUtil.isEmpty(s)) {
            return sep;
        }
        if (!s.startsWith(sep)) {
            return sep + s;
        }
        return s;
    }


    /**
     * path model
     * example:
     * POSIX /home/jason/a.out
     * WIN C:\\User\\jason\\a.out
     */
    public static class Path {
        private PathType type;
        /**
         * POSIX  /
         * WIN  C:\\
         */
        private String root = "";
        /**
         * POSIX /home/jason
         * WIN  C:\\User\\jason
         */
        private String dir = "";
        /**
         * POSIX/WIN  a.out
         */
        private String base = "";
        /**
         * POSIX/WIN  out
         */
        private String ext = "";
        /**
         * POSIX/WIN a
         */
        private String name = "";

        public Path() {
        }

        public static void mergeBasePart(Path target, Path extPath) {
            if(extPath==null){
                return;
            }
            target.setBase(extPath.getBase());
            target.setName(extPath.getName());
            target.setExt(extPath.getExt());
        }

        public static Path buildUnixRoot() {
            Path p = new Path();
            p.setRoot(DIR_SEP_POSIX);
            p.setDir(DIR_SEP_POSIX);
            p.setType(PathType.POSIX);
            return p;
        }

        public static Path buildWinRoot(String disk) {
            Path p = new Path();
            if (!disk.endsWith(DISK_SEP_WIN)) {
                disk = disk + DISK_SEP_WIN;
            }
            String root = disk + DIR_SEP_WIN;
            p.setRoot(root);
            p.setDir(root);
            p.setType(PathType.WIN);
            return p;
        }

        public static Path buildUnix(String path) {
            Path p = new Path();

            return p;
        }

        public PathType getType() {
            return type;
        }

        public void setType(PathType type) {
            this.type = type;
        }

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static enum PathType {
        POSIX, WIN, OTHER
    }
}
