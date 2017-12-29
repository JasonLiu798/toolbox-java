package com.atjl.dbfront.service;

import com.atjl.config.api.ConfigConstant;
import com.atjl.config.api.ConfigUtil;
import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.exception.ContentException;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.file.FileUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Service
public class ContentFileService {

    public void printJsFromFile(String name, String ver, HttpServletResponse resp) {
        String jsContent = getContent(ContentConstant.TP_JS, name, ver);
        resp.setContentType("application/javascript");//content type
        resp.setHeader("Accept-Ranges", "bytes");
        resp.setHeader("Cache-Control", "max-age=43200");
        try {
            PrintWriter out = resp.getWriter();
            out.print(jsContent);
        } catch (Exception e) {
            LogDbUtil.error(ContentConstant.MODULE_STATIC, "js get writer exception", e);
        }
        /*
         //缓存1小时
         Date d = DateUtil.getDateH(1);
         String expTmStr = DateUtil.fmtExpireTm(d);
         resp.setHeader("Expires", expTmStr);
         resp.setHeader("Connection", "Keep-alive");
         */
    }

    /**
     * @param type
     * @param name
     * @param ver
     * @param content
     * @return
     */
    public int addContent(String type, String name, String ver, String content) {
        String fileName = String.format(ContentConstant.FILE_NAME_FMT, name, ver, type);
        String dir = ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_TREE, "BaseSetting.fileSetting.contentPath", "/nfsc/ESG_AOS_CORE/content/");

        if (!FileUtil.dirExist(dir)) {
            if (!FileUtil.mkDir(dir)) {
                throw new ContentException("mkdir fail");
            }
        }

        if (FileUtil.fileExist(dir + fileName)) {
            throw new ContentException("file exist");
        }

        FileUtil.write(dir + fileName, content, false);

        return 0;
    }


    public String getContent(String type, String name, String ver) {
        String fileName = String.format(ContentConstant.FILE_NAME_FMT, name, ver, type);
        String dir = ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_TREE, "BaseSetting.fileSetting.contentPath", "/nfsc/ESG_AOS_CORE/content/");

        if (!FileUtil.fileExist(dir + fileName)) {
            throw new ContentException("file not exist");
        }
        return FileUtil.cat(dir + fileName);
    }

}
