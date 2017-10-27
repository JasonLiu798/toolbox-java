package com.atjl.dbfront.controller;

import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.domain.biz.ContentDomain;
import com.atjl.dbfront.service.ContentService;
import com.atjl.logdb.api.LogDbUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取静态文件内容
 */
@Controller
@RequestMapping(ContentConstant.CONTENT)
public class ContentGetController {
    //    private static Logger logger = LoggerFactory.getLogger(ContentGetController.class);
    @Resource
    private ContentService contentService;

    /**
     * 获取js文件
     * content/getscript?name=nnn&ver=vvv
     * @return
     */
    @RequestMapping(ContentConstant.JS)
    public void js(String name, String ver, HttpServletResponse resp) {
        contentService.printJs(name, ver, resp);
    }

    /**
     * 同上
     * content/script/{name}/{ver}
     * content/script/main/1111
     * @param name
     * @param ver
     * @param resp
     */
    @RequestMapping(ContentConstant.JS_PATH)
    public void jsPath(@PathVariable String name, @PathVariable String ver, HttpServletResponse resp) {
        contentService.printJs(name, ver, resp);
    }

    /**
     * 获取css
     * content/css?name=nnn&ver=vvv
     *
     * @return
     */
    @RequestMapping(ContentConstant.CSS)
    public void css(String name, String ver, HttpServletResponse resp) {
        ContentDomain cd = contentService.getContent(ContentConstant.TP_CSS, name, ver);
        String jsContent = "";
        if (cd != null) {
            jsContent = cd.getContent();
        }
        resp.setContentType("text/css");//content type
        resp.setHeader("Accept-Ranges", "bytes");
        resp.setHeader("Cache-Control", "max-age=43200");
        try {
            PrintWriter out = resp.getWriter();
            out.println(jsContent);
        } catch (Exception e) {
            LogDbUtil.error(ContentConstant.MODULE_STATIC, "css get writer exception", e);
        }
        //缓存1小时
//        Date d = DateUtil.getDateH(1);
//        String expTmStr = DateUtil.fmtExpireTm(d);
//        resp.setHeader("Expires", expTmStr);

//        resp.setHeader("Connection", "Keep-alive");
    }

    /**
     * 获取其他html内容
     * content/html?name=nnn&ver=vvv
     *
     * @param resp
     * @throws IOException
     */
    @RequestMapping(ContentConstant.HTML)
    public void html(String name, String ver, HttpServletResponse resp) {
        ContentDomain cd = contentService.getContent(ContentConstant.TP_HTML, name, ver);
        String htmlContent = "";
        if (cd != null) {
            htmlContent = cd.getContent();
        }
        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            out.print(htmlContent);
        } catch (Exception e) {
            LogDbUtil.error(ContentConstant.MODULE_STATIC, "html get writer exception", e);
            return;
        }
    }


}
