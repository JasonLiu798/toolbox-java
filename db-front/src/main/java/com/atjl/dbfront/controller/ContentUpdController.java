package com.atjl.dbfront.controller;

import com.atjl.common.api.resp1.ResponseDataDtoV1;
import com.atjl.common.constant.CommonConstant;
import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.service.ContentService;
import com.atjl.util.character.StringCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * html js css 发布，回滚
 */
@Controller
@RequestMapping(ContentConstant.CONTENT_MGR)
public class ContentUpdController {

    @Resource
    private ContentService contentService;

    /**
     * 发布新首页
     * contentmgr/pub
     */
    @RequestMapping(value = ContentConstant.PUB_IDX, method = RequestMethod.POST)
    @ResponseBody
    public ResponseDataDtoV1 pub(String content, String isFirst) {
        String res;
        if (StringCheckUtil.isExistEmpty(content, isFirst)) {
            return ResponseDataDtoV1.buildFail("参数存在空值");
        }
        if (StringCheckUtil.equal(isFirst, CommonConstant.YES)) {
            res = contentService.pubHtml(content, true);
        } else {
            res = contentService.pubHtml(content, false);
        }
        return ResponseDataDtoV1.buildOk(res);
    }

    /**
     * 回滚首页
     * contentmgr/rollback
     *
     * @return
     */
    @RequestMapping(ContentConstant.ROLLBACK_IDX)
    @ResponseBody
    public ResponseDataDtoV1 rollback(String oldVer) {
        contentService.rollBack(oldVer);
        return ResponseDataDtoV1.buildOk(oldVer);
    }


    /**
     * 更新文件内容
     * /contentmgr/upd
     *
     * @param type
     * @param name
     * @param content
     * @return
     */
    @RequestMapping(ContentConstant.UPD_CONTENT)
    @ResponseBody
    public ResponseDataDtoV1 addOrUpdateContent(String type, String name, String version, String content) {
        contentService.addOrUpdateContent(type, name, version, content);
        return ResponseDataDtoV1.buildOk();
    }

    /**
     * 更新文件内容
     * /contentmgr/updfile
     *
     * @param fileInput
     * @param type
     * @param name
     * @param version
     * @return
     */
    @RequestMapping(ContentConstant.UPD_CONTENT_FILE)
    @ResponseBody
    public ResponseDataDtoV1 addOrUpdateContentFile(MultipartFile fileInput, String type, String name, String version) throws IOException {
        if (fileInput == null || StringCheckUtil.isExistEmpty(type, name, version)) {
            return ResponseDataDtoV1.buildFail("参数存在空值");
        }
        byte[] contentBytes = fileInput.getBytes();
        String content = new String(contentBytes, Charset.forName("utf-8"));
        contentService.addOrUpdateContent(type, name, version, content);
        return ResponseDataDtoV1.buildOk();
    }

}
