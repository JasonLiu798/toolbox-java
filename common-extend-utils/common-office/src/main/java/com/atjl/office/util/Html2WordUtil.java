package com.atjl.office.util;


import com.atjl.common.constant.CommonConstant;
import com.atjl.office.api.exception.WordException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class Html2WordUtil {
    private Html2WordUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 只支持 doc 格式 文档生成
     */
    public static void htmlToWord(String html, String wordPath) {
        try (InputStream is = new ByteArrayInputStream(html.getBytes(CommonConstant.UTF_8));
             OutputStream os = new FileOutputStream(wordPath)
        ) {
            POIFSFileSystem fs = new POIFSFileSystem();
            //对应于org.apache.poi.hdf.extractor.WordDocument
            fs.createDocument(is, "WordDocument");
            fs.writeFilesystem(os);
        } catch (Exception e) {
            throw new WordException(e);
        }
    }


}
