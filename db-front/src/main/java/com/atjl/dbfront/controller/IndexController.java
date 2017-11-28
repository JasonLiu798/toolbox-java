package com.atjl.dbfront.controller;

import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "首页", description = "首页")
@Controller
public class IndexController {

    @Resource
    private ContentService contentService;

    @ApiOperation(value = "首页", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping(value = {"/module/index.html", "/"}, method = {RequestMethod.GET})
    public void index(HttpServletRequest request, HttpServletResponse resp) {
        contentService.printHtml(ContentConstant.IDX_NAME, ContentConstant.CUR_VER, request, resp);
    }

    @ApiOperation(value = "首页测试", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping(value = {"/getidx/{ver}"}, method = {RequestMethod.GET})
    public void indexWithVer(@PathVariable String ver, HttpServletRequest request, HttpServletResponse resp) {
        contentService.printHtml(ContentConstant.IDX_NAME, ver, request, resp);
    }

}
