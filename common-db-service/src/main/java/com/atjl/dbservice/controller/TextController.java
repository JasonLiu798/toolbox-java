package com.atjl.dbservice.controller;

import com.atjl.common.api.resp1.ResponseDataDtoV1;
import com.atjl.dbservice.api.domain.DbSearchReq;
import com.atjl.dbservice.service.TextService;
import com.atjl.util.character.StringCheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Api(value = "系统 状态 服务，一般不暴漏", description = "系统 状态 服务，一般不暴漏", hidden = true)
@Controller
public class TextController {
    @Resource
    private TextService textService;


    /**
     * 系统测试
     * pre/TEST
     *
     * @param text
     * @param req
     * @param response
     * @return
     @RequestMapping(AOSURL.TEST)
     @ResponseBody public ResponseDataDtoV1 TEST(String text, HttpServletRequest req, HttpServletResponse response) {
     Cookie cookie = new Cookie("ttt", "tttt");
     cookie.setMaxAge(60);
     cookie.setPath("/");
     response.addCookie(cookie);

     ResponseDataDtoV1 res = new ResponseDataDtoV1();
     res.setResult("hahah");
     //textService.process(text, type);
     return res;
     }*/

    /**
     * text/text
     */
    @ApiOperation(value = "db util", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping("text/text")
    @ResponseBody
    public ResponseDataDtoV1 text(String text, String type) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        if (StringCheckUtil.isEmpty(text)) {
            return resp;
        }
        text = StringEscapeUtils.escapeHtml4(text);
        type = StringEscapeUtils.escapeHtml4(type);
        return textService.process(text, type);
    }


    @ApiOperation(value = "db util", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    @RequestMapping("text/textp")
    @ResponseBody
    public ResponseDataDtoV1 textp(@RequestBody DbSearchReq req) {
        ResponseDataDtoV1 resp = new ResponseDataDtoV1();
        if (StringCheckUtil.isEmpty(req.getText())) {
            return resp;
        }
        String text = StringEscapeUtils.escapeHtml4(req.getText());
        String type = StringEscapeUtils.escapeHtml4(req.getType());
        return textService.process(text, type);
    }

}
