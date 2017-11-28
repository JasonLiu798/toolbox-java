package com.atjl.dbfront.controller;

import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.domain.biz.ContentDomain;
import com.atjl.dbfront.service.ContentService;
import com.atjl.logdb.api.LogDbUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Api(value = "静态内容", description = "静态内容")
@Controller
@RequestMapping(ContentConstant.CONTENT)
public class ContentGetController {
    //    private static Logger logger = LoggerFactory.getLogger(ContentGetController.class);
    @Resource
    private ContentService contentService;

    /**
     * 获取js文件
     * content/getscript?name=nnn&ver=vvv
     * http://localhost:1080/content/getscript?name=js1&ver=CUR
     */
    @ApiOperation(value = "获取js文件", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping(ContentConstant.JS)
    public void js(String name, String ver, HttpServletResponse resp) {
        contentService.printJs(name, ver, resp);
    }

    /**
     * 同上
     * content/script/{name}/{ver}
     * content/script/main/1111
     */
    @ApiOperation(value = "获取js文件，通过pathvalue", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping(ContentConstant.JS_PATH)
    public void jsPath(@PathVariable String name, @PathVariable String ver, HttpServletResponse resp) {
        contentService.printJs(name, ver, resp);
    }

    /**
     * content/css?name=nnn&ver=vvv
     */
    @ApiOperation(value = "获取css文件", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
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
     * content/html?name=nnn&ver=vvv
     */
    @ApiOperation(value = "获取其他html内容", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
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
