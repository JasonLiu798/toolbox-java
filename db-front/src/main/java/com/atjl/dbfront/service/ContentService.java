package com.atjl.dbfront.service;

import com.atjl.common.api.exception.ParamFormatException;
import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.dbfront.domain.biz.ContentDomain;
import com.atjl.dbfront.domain.biz.UpdContentResp;
import com.atjl.dbfront.domain.gen.TsContent;
import com.atjl.dbfront.domain.gen.TsContentExample;
import com.atjl.dbfront.mapper.biz.ContentMapper;
import com.atjl.dbfront.mapper.gen.TsContentMapper;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.common.DateUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

@Service
public class ContentService {
    @Resource
    ContentMapper contentMapper;
    @Resource
    TsContentMapper tsContentMapper;

    public void printHtml(String name, String ver, HttpServletRequest request, HttpServletResponse resp) {
        ContentDomain cd = getContent(ContentConstant.TP_HTML, name, ver);
        String htmlContent = "";
        if (cd != null) {
            htmlContent = cd.getContent();
        }
        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html;charset=UTF-8");
            out.print(htmlContent);
        } catch (Exception e) {
            String path = request.getPathInfo();
            LogDbUtil.error(ContentConstant.MODULE_STATIC, "index get writer exception,path " + path, e);
        }
    }


    public void printJs(String name, String ver, HttpServletResponse resp) {
        ContentDomain cd = getContent(ContentConstant.TP_JS, name, ver);
        String jsContent = "";
        if (cd != null) {
            jsContent = cd.getContent();
        }
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
     * 发布 html
     *
     * @param content
     */
    public String pubHtml(String content, boolean first) {
        String backVer = DateUtil.format(new Date(), DateUtil.MMddHHmmss_EN);
        if (first) {
            return String.valueOf(addOrUpdateContent(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, ContentConstant.CUR_VER, content));
        } else {
            if (backupHtml(backVer)) {//backup content
                TsContent tc = new TsContent();
                tc.setContent(content);
                tc.setPreVersion(backVer);
                //更新当前index为新版
                updateContentObj(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, ContentConstant.CUR_VER, tc);
                return backVer;//返回备份版本号
            } else {
                return "backup fail";
            }
        }
    }


    /**
     * 是否存在
     *
     * @param type
     * @param name
     * @param ver
     * @return
     */
    private boolean exist(String type, String name, String ver) {
        ContentDomain cd = contentMapper.getContent(type, name, ver);
        if (cd == null) {
            return false;
        }
        return true;
    }

    /**
     * 更新内容
     *
     * @param type
     * @param name
     * @param ver
     * @param content
     */
    public int updateContent(String type, String name, String ver, String content) {
        TsContent rec = new TsContent();
        rec.setCtype(type);
        rec.setCversion(ver);
        rec.setCname(name);
        rec.setContent(content);
        //update
        TsContentExample param = new TsContentExample();
        param.createCriteria().andCtypeEqualTo(type).andCnameEqualTo(name).andCversionEqualTo(ver);
        return tsContentMapper.updateByExampleSelective(rec, param);
    }

    /**
     * 更新
     *
     * @param type
     * @param name
     * @param ver
     * @param tc
     */
    public void updateContentObj(String type, String name, String ver, TsContent tc) {
        //update
        TsContentExample param = new TsContentExample();
        param.createCriteria().andCtypeEqualTo(type).andCnameEqualTo(name).andCversionEqualTo(ver);
        tsContentMapper.updateByExampleSelective(tc, param);
    }

    /**
     * 添加
     *
     * @param type
     * @param name
     * @param ver
     * @param content
     * @return
     */
    public int addContent(String type, String name, String ver, String content) {
        TsContent rec = new TsContent();
        rec.setCtype(type);
        rec.setCversion(ver);
        rec.setCname(name);
        rec.setContent(content);
        return tsContentMapper.insertSelective(rec);
    }

    @Resource
    private ContentFileService contentFileService;

    /**
     * 新增或更新
     */
    public UpdContentResp addOrUpdateContent(String type, String name, String ver, String content) {
        if (StringCheckUtil.isExistEmpty(type, name, ver, content)) {
            throw new ParamFormatException("存在空值 type " + type + ",name " + name + ",ver " + ver + ",content " + content);
        }
        ContentConstant.chkTp(type);
        TsContent rec = new TsContent();
        rec.setCtype(type);
        rec.setCversion(ver);
        rec.setCname(name);
        rec.setContent(content);

        UpdContentResp resp = new UpdContentResp();
        try {
            contentFileService.addContent(type, name, ver, content);
            resp.setFileRes("file write succ");
        } catch (Exception e) {
            resp.setFileRes("file write fail" + e.getMessage());
        }

        if (exist(type, name, ver)) {
            //update
            int res = updateContent(type, name, ver, content);
            if (res > 0) {
                resp.setUpdRes("succ " + res);
            } else {
                resp.setUpdRes("fail" + res);
            }
        } else {
            //add
            int res = addContent(type, name, ver, content);
            if (res > 0) {
                resp.setAddRes("succ " + res);
            } else {
                resp.setAddRes("fail " + res);
            }
        }
        return resp;
    }


    /**
     * 回滚首页
     *
     * @param ver 回滚版本
     */
    public void rollBack(String ver) {
        ContentDomain cd = getContent(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, ver);
        if (cd == null) {
            throw new ParamFormatException("此版本的html找不到");
        }
        String rollBackContent = cd.getContent();
        TsContent tc = new TsContent();
        tc.setContent(rollBackContent);
        updateContentObj(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, ContentConstant.CUR_VER, tc);
    }

    /**
     * 备份首页
     *
     * @param bakVer 版本
     * @return 是否成功
     */
    private boolean backupHtml(String bakVer) {
        String curHtml = getCurIndexContent();
        int res = addContent(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, bakVer, curHtml);
        return res > 0;
    }

    /**
     * 获取当前首页
     */
    public String getCurIndexContent() {
        ContentDomain cd = getContent(ContentConstant.TP_HTML, ContentConstant.IDX_NAME, ContentConstant.CUR_VER);
        if (cd != null) {
            return cd.getContent();
        } else {
            return "";
        }
    }

    @Cacheable(value = ContentConstant.CONTENT_CACHE)
    public ContentDomain getContent(String type, String name, String ver) {
        return contentMapper.getContent(type, name, ver);
    }

}
