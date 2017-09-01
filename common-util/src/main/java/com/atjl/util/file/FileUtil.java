package com.atjl.util.file;

import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.config.ParameterConfigParser;
import com.atjl.util.constant.SystemConstant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * API style unix like
 */
public final class FileUtil {
    private FileUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * permission size lastmodified name
     */
    private static final String FORMAT_LS = "%s %s %s %s";

    /**
     * ############################# check #############################
     */
    /**
     * file exist
     *
     * @param filepath
     * @return
     */
    public static boolean fileExist(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    /**
     * dir exist
     *
     * @param filepath
     * @return
     */
    public static boolean dirExist(String filepath) {
        File file = new File(filepath);
        return file.exists() && file.isDirectory();
    }

    public static boolean checkFree(String uploadpath) {
        File f = new File(uploadpath);
        if (!(f.exists())) {
            return false;
        }
        return (f.getFreeSpace() != 0L);
    }

    /**
     * ############################# ls #############################
     */
    /**
     * @param dirPath path
     * @return
     * @if file
     * return self's absolute path
     * @if dir
     * return list dir's child file
     */
    public static List<String> ls(String dirPath) {
        return lsInner(dirPath, null);
    }

    /**
     * @param dirPath
     * @return file
     */
    public static List<String> ll(String dirPath) {
        return lsInner(dirPath, "-l");
    }


    /**
     * 获取 classpath 下文件全路径
     *
     * @param fileName
     * @return
     */
    public static String getFileFromClasspath(String fileName) {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        return url.getFile();
        /**
         * url.getFile() 得到这个文件的绝对路径
         *
         System.out.println(url.getFile());
         File file = new File(url.getFile());
         System.out.println(file.exists());
         */
    }

    public static List<String> lsInner(String dirPath, String option) {
        File file = new File(dirPath);
        List<String> list = new LinkedList<>();
        if (!file.exists()) {
            return list;
        }
        if (file.isDirectory()) {
            File[] childs = file.listFiles();
            if (CollectionUtil.isEmpty(childs)) {
                return list;
            }
            for (File f : childs) {
                if (option != null && option.indexOf("-l") >= 0) {
                    list.add(formatFile(f));
                } else {
                    list.add(f.getName());
                }
            }
        } else {
            if (option != null && option.indexOf("-l") >= 0) {
                list.add(formatFile(file));
            } else {
                list.add(file.getAbsolutePath());
            }
            return list;
        }
        return list;
    }

    private static String formatFile(File f) {
        String permission = formatPermit(f);
        String size = String.valueOf(f.length());
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

    /**
     * @param dirPath
     * @return all reletive file path
     */
    public static List<String> tree(String dirPath) {
        if (!dirExist(dirPath)) {
            return null;
        }
        List<String> res = new LinkedList<>();
        traverseDir(dirPath, res);
        return res;
    }

    /**
     * traverse dir
     *
     * @param dirpath
     * @param res
     */
    private static void traverseDir(String dirpath, List<String> res) {
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


    public static List<String> lsPrefix(String dirPath, String prefix, String[] black) {
        List<String> res = new LinkedList<>();
        File parent = new File(dirPath);
        File[] childs = parent.listFiles();
        if (CollectionUtil.isEmpty(childs)) {
            return res;
        }
        for (File c : childs) {
            if (c.getName().contains(prefix)) {
                boolean inBlack = false;
                if (!CollectionUtil.isEmpty(black)) {
                    for (String b : black) {
                        if (c.getName().contains(b)) {

                            inBlack = true;
                        }
                    }
                }
                if (!inBlack) {
                    res.add(PathUtil.join(dirPath, c.getName()));
                }
            }
        }
        return res;
    }

    /**
     * ############################# new #############################
     */
    /**
     * new file
     *
     * @param filepath
     * @throws IOException
     */
    public static void touch(String filepath) throws IOException {
        File file = new File(filepath);
        if (file.exists()) {
            return;
        }
        file.createNewFile();
    }


    /**
     * ############################# covertor #############################
     */
    /**
     * class name format to Path
     * example:
     * com.jason798.util.A
     * in linux :
     * -> com/jason798/util/A
     *
     * @param className
     * @return
     */
    public static String className2FilePath(String className) {
        if (StringCheckUtil.isEmpty(className)) {
            return "";
        }
        // className.replaceAll(StringUtil.DOT_SEP_NO_REX,SystemConstant.DIR_SEP);
        String[] arr = className.split(RegexUtil.DOT_SEP_NO_REX);
        //System.out.println("arr size "+arr.length+","+className);
        if (arr.length == 1) {
            return className;
        }
        int lenMinus1 = arr.length - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != lenMinus1) {
                sb.append(PathUtil.DIR_SEP);
            }
        }
        return sb.toString();
    }

    /**
     * file size
     *
     * @param filepath file name
     * @return file size
     */
    public static long getFileSize(String filepath) {
        long res = -1;
        FileChannel fc = null;
        File f = new File(filepath);
        FileInputStream fis = null;
        if (f.exists() && f.isFile()) {
            try {
                fis = new FileInputStream(f);
                fc = fis.getChannel();
                res = fc.size();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != fc) {
                    try {

                        fc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return res;
    }

    /**
     * 文件拷贝
     * commons.io包 封装
     *
     * @param srcFilePath  {@link File} 待拷贝的文件
     * @param destFilePath {@link String} 目的地
     * @throws Exception IOException
     */
    public static void cp(String srcFilePath, String destFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        File destFile = new File(destFilePath);
        FileUtils.copyFile(srcFile, destFile);
    }


    /**
     * *********************** read  file apis ************************
     */
    public static String cat(String filepath) {
        File file = new File(filepath);
        FileReader fileReader = null;
        BufferedReader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String tempString;
            while ((tempString = reader.readLine()) != null) {
//				String codeced = new String(tempString.getBytes("utf8"));
                res.append(tempString).append(SystemConstant.LINE_SEP);
            }
            reader.close();
        } catch (IOException e) {
            logger.error("{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        return res.toString();
    }

    /**
     * not skip space line
     *
     * @param path
     * @return
     */
    public static List<String> cat2List(String path) {
        return cat2List(path, 0);
    }

    /**
     * read file content to List<String>
     * 1=skip space line
     *
     * @param path
     * @return
     */
    public static List<String> cat2List(String path, int option) {
        List<String> res = new ArrayList<>();
        String line;
        BufferedReader in = null;
        List<Boolean> options = ParameterConfigParser.int2bits(option, 1);
        boolean filterSpace = options.get(0);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            in = new BufferedReader(fileReader);
            line = in.readLine();
            while (line != null) {
                boolean add = true;
                if (filterSpace && StringCheckUtil.isEmpty(line.trim())) {
                    add = false;
                }
                if (add) {
                    res.add(line);
                }
                line = in.readLine();
            }
        } catch (Exception e) {
            logger.error("{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        return res;
    }


    /**
     * read bytes
     *
     * @param filePath file path
     * @return byte[]
     */
    public static byte[] catRaw(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            logger.info("file too big...");
            return new byte[0];
        }
        FileInputStream fi = null;
        byte[] buffer = null;
        try {
            fi = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
        } catch (IOException e) {
            logger.error("{}", e);
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        return buffer;
    }

    /**
     * only read int.max_value byte
     *
     * @param filepath
     * @return
     */
    public static byte[] catRaw2(String filepath) {
        int size = (int) getFileSize(filepath);
        logger.debug("file size {}", size);
        byte[] res = new byte[size];
        FileInputStream fileInputStream = null;
        DataInputStream in = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            in = new DataInputStream(new BufferedInputStream(fileInputStream));
            for (int i = 0; i < size; i++) {
                res[i] = in.readByte();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        return res;
    }


    /**
     * ***********************  write file apis ************************
     */
    public static void writeBytes2File(String filepath, byte[] content) {
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filepath);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            for (int i = 0; i < content.length; i++) {
                dataOutputStream.writeByte(content[i]);
            }
        } catch (Exception e) {
            logger.error("{}", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.error("writeBytes2File close fileOutputStream {}", e);
                }
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    logger.error("writeBytes2File close dataOutputStream {}", e);
                }
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("write to file {} finish,bin mode", filepath);
        }
        return;
    }


    /**
     * if exit ,not write,
     *
     * @param filepath
     * @param content
     */
    public static void write(String filepath, String content, boolean force) {
        List<String> list = new LinkedList<>();
        list.add(content);
        write(filepath, list, force);
    }

    public static void write(String filepath, List<String> content, boolean force) {
        if (force && fileExist(filepath)) {
            rm(filepath);
        }
        append(filepath, content);
    }

    public static void writeForce(String filepath, String content) {
        write(filepath, content, true);
    }

    public static void write(String filepath, List<String> content) {
        write(filepath, content, false);
    }

    public static void writeForce(String filepath, List<String> content) {
        write(filepath, content, true);
    }

    /**
     * write content to file,auto add "\n"
     *
     * @param filepath
     * @param content
     */
    public static void append(String filepath, String content) {
        List<String> list = new LinkedList<>();
        list.add(content);
        append(filepath, list);
    }


    /**
     * write contents to file
     *
     * @param filepath file path
     * @param contents content
     */
    public static void append(String filepath, List<String> contents) {
        FileWriter writer = null;
        try {
            if (fileExist(filepath)) {
                writer = new FileWriter(filepath, true);//exist,append
            } else {
                //not exist,create
                writer = new FileWriter(filepath, false);//if file exist ,it will rm the file then create
            }
            StringBuilder sb = new StringBuilder();
            for (String str : contents) {
                sb.append(str).append("\n");
            }
            writer.write(sb.toString());
            if (logger.isDebugEnabled()) {
                logger.debug("write to file,file {},content {}", filepath, contents);
            }
        } catch (IOException e) {
            logger.error("{}", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        return;
    }

    /**
     * ######################### dir operations ##################################
     */
    /**
     * mkdir
     *
     * @param dirPath dirpath
     * @return success or not
     */
    public static boolean mkDir(String dirPath) {
        if (dirPath == null || dirPath.isEmpty()) {
            return false;
        }
        File folder = new File(dirPath);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }


    /**
     * @param sPath
     * @return
     */
    public static boolean rm(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            if (file.isFile()) {
                return rmFile(sPath);
            } else {
                return rmDir(sPath);
            }
        }
    }

    /**
     * rm file
     *
     * @param sPath
     * @return
     */
    public static boolean rmFile(String sPath) {
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * rm dir none recu
     *
     * @param sPath dir path
     * @return success or not
     */
    public static boolean rmDir(String sPath) {
        if (!sPath.endsWith(File.separator)) {//add sep
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {//check exist,type
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        if (CollectionUtil.isEmpty(files)) {
            return flag;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = rmFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {//recursive rm
                flag = rmDir(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) {
            return false;
        }
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    public static String getMd5(String filepath) {
        FileInputStream fis = null;
        try {
            File file = new File(filepath);
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            String md5raw = new BigInteger(1, md.digest()).toString(16);
            if (32 - md5raw.length() > 0) {
                return StringUtil.addCharacterFront(md5raw, "0", 32 - md5raw.length(), 1);
            } else {
                return md5raw;
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            logger.error("{}", e);
            return "";
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("getMd5 close stream fail {}", e.getMessage());
                }
            }
        }
    }

}


/**
 * read file use PathMatchingResourcePatternResolver
 *
 * @return
 * @throws IOException
 * <p>
 * public static List<String> getFilesStr(String filepath) throws IOException {
 * PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
 * Resource[] resources = patternResolver.getResources(filepath);
 * List<String> res = new ArrayList<>();
 * if (resources == null) {
 * log.error("load file {} fail,file null or not exist", filepath);
 * return res;
 * }
 * for (int i = 0; i < resources.length; i++) {
 * log.debug("get resource: {}", resources[i].getFilename());
 * InputStreamReader inputStreamReader = new InputStreamReader(resources[i].getInputStream());
 * BufferedReader breader = new BufferedReader(inputStreamReader);
 * String temp = breader.readLine();
 * String xmlStr = "";
 * while (temp != null) {
 * xmlStr += temp + "\n";
 * temp = breader.readLine();
 * }
 * res.add(xmlStr);
 * }
 * return res;
 * }
 */
    /*
    public static String getFileStr(String filepath) throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = patternResolver.getResource(filepath);
        String res = "";
        if (resource == null) {
            log.error("load file {} fail,file null or not exist", filepath);
            return res;
        }
        log.debug("get resource: {}", resource.getFilename());
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
        BufferedReader breader = new BufferedReader(inputStreamReader);
        String temp = breader.readLine();
        String xmlStr = "";
        while (temp != null) {
            xmlStr += temp + "\n";
            temp = breader.readLine();
        }
        res = xmlStr;
        return res;
    }*/


