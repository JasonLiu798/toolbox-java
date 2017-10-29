package com.atjl.dbfront.controller;

import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页
 */
@Controller
public class IndexController {

    @Resource
    private ContentService contentService;

    @RequestMapping(value = {"/module/index.html", "/"}, method = {RequestMethod.GET})
    public void index(HttpServletRequest request, HttpServletResponse resp) {
        contentService.printHtml(ContentConstant.IDX_NAME, ContentConstant.CUR_VER, request, resp);
    }

    @RequestMapping(value = {"/getidx/{ver}"}, method = {RequestMethod.GET})
    public void indexWithVer(@PathVariable String ver, HttpServletRequest request, HttpServletResponse resp) {
        contentService.printHtml(ContentConstant.IDX_NAME, ver, request, resp);
    }

}
