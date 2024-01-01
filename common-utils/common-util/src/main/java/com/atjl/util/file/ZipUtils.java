package com.atjl.util.file;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtilEx;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipUtils {


    public static boolean zipFiles(String base, List<String> srcList, String zipFile) throws IOException {
        if (CollectionUtilEx.isEmpty(srcList)) {
            return false;
        }
        if (StringCheckUtil.isExistEmpty(base, zipFile)) {
            return false;
        }
        List<String> srcAbsPathList = new ArrayList<>(srcList.size());
        for (String src : srcList) {
            if (StringCheckUtil.isEmpty(src)) {
                continue;
            }
            String tmpPath = PathUtil.join(base, src);
            if (!FileUtilEx.fileExist(tmpPath)) {
                continue;
            }
            srcAbsPathList.add(tmpPath);
        }
        if (srcAbsPathList.size() == 0) {
            return false;
        }
        String zipAbsFile = PathUtil.join(base, zipFile);
        return  zipFiles(srcAbsPathList, zipAbsFile);
    }

    public static boolean zipFiles(List<String> srcList, String zipFile) {
        // new a file input stream
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));

            for (String src : srcList) {
                try (FileInputStream fis = new FileInputStream(src);BufferedInputStream bis = new BufferedInputStream(fis)){
                    File f = new File(src);
                    // set the file name in the .zip file
                    zos.putNextEntry(new ZipEntry(f.getName()));
                    byte[] bytes = new byte[1024*5];
                    int read=0;
                    while ((read = bis.read(bytes)) != -1) {
                        zos.write(bytes, 0, read);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }



}
