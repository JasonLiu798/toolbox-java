package com.jason798.file;

import com.jason798.character.StringCheckUtil;
import com.jason798.character.StringUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.common.DateUtil;
import com.jason798.common.PathUtil;
import com.jason798.constant.SystemConstant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * API style unix like
 */
public final class FileUtil {

    private static Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    public static final String DFT_FILE = "/opt/logs/test.dat";
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
                if (option!=null && option.indexOf("-l")>=0) {
                    list.add(formatFile(f));
                } else {
                    list.add(f.getName());
                }
            }
        } else {
            if (option!=null && option.indexOf("-l")>=0) {
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

    public static List<String> lsPrefix(String dirPath,String prefix,String[] black){
        List<String> res = new LinkedList<>();
        File parent = new File(dirPath);
        File[] childs = parent.listFiles();
        if (CollectionUtil.isEmpty(childs)) {
            return res;
        }
        for (File c : childs) {
            if(c.getName().contains(prefix) ) {
                boolean inBlack = false;
                if(!CollectionUtil.isEmpty(black)){
                    for(String b:black){
                        if(c.getName().contains(b)){
                            inBlack = true;
                        }
                    }
                }
                if(!inBlack) {
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
        String[] arr = className.split(StringUtil.DOT_SEP_NO_REX);
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
        BufferedReader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
//				String codeced = new String(tempString.getBytes("utf8"));
                res.append(tempString).append(SystemConstant.LINE_SEP);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res.toString();
    }

    /**
     * read file content to List<String>
     *
     * @param path
     * @return
     */
    public static List<String> cat2List(String path) {
        List<String> res = new ArrayList<>();
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(path));
            line = in.readLine();
            while (line != null) {
                if (line.indexOf("--") != 0) {
                    res.add(line);
                }
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] catRaw(String filePath) {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = null;
        byte[] buffer = null;
        try {
            fi = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
        LOG.debug("file size {}", size);
        byte[] res = new byte[size];
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)));
//            FileInputStream in = new FileInputStream(filepath);
            for (int i = 0; i < size; i++) {
                res[i] = in.readByte();
            }
//            while ((c=in.read())!=-1) {
//                byte tmpByte = in.readByte();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * ***********************  write file apis ************************
     */
    public static void writeBytes2File(String filepath, byte[] content) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filepath));
            for (int i = 0; i < content.length; i++) {
                out.writeByte(content[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.debug("write to file {} finish,bin mode", filepath);
        return;
    }


    /**
     * if exit ,not write,
     * @param filepath
     * @param content
     */
    public static void write(String filepath, String content,boolean force) {
        List<String> list = new LinkedList<>();
        list.add(content);
        write(filepath,list,force);
    }

    public static void write(String filepath, List<String> content,boolean force) {
        if(force && fileExist(filepath)){
            rm(filepath);
        }
        append(filepath,content);
    }
    public static void writeForce(String filepath, String content) {
        write(filepath,content,true);
    }
    public static void write(String filepath, List<String> content) {
        write(filepath,content,false);
    }
    public static void writeForce(String filepath, List<String> content) {
        write(filepath,content,true);
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
            if (LOG.isDebugEnabled()) {
                LOG.debug("write to file,file {},content {}", filepath, contents);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
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


