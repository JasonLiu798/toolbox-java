package com.atjl.office.util;


import com.atjl.common.constant.CommonConstant;
import com.atjl.office.api.exception.WordException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class Html2WordUtil {
    private Html2WordUtil() {
        throw new UnsupportedOperationException();
    }

    public static void htmlToWord(String html, String wordPath) {
        try (InputStream is = new ByteArrayInputStream(html.getBytes(CommonConstant.UTF_8));
             OutputStream os = new FileOutputStream(wordPath+".doc")
        ) {
            POIFSFileSystem fs = new POIFSFileSystem();
            //对应于org.apache.poi.hdf.extractor.WordDocument
            fs.createDocument(is, "WordDocument");
            fs.writeFilesystem(os);
        } catch (Exception e) {
            throw new WordException(e);
        }
    }


    /**
     * 把is写入到对应的word输出流os中
     * 不考虑异常的捕获，直接抛出
     *
     * @param is
     * @param os
     * @throws IOException
     */
    private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {

    }

    /**
     * 把输入流里面的内容以UTF-8编码当文本取出。
     * 不考虑异常，直接抛出
     *
     * @param ises
     * @return
     * @throws IOException
     */
    private static String getContent(InputStream... ises) throws IOException {
        if (ises != null) {
            StringBuilder result = new StringBuilder();
            BufferedReader br;
            String line;
            for (InputStream is : ises) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        }
        return null;
    }


}
