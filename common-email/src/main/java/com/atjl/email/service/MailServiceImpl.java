package com.atjl.email.service;

import com.atjl.config.api.ConfigConstant;
import com.atjl.config.api.ConfigService;
import com.atjl.email.api.MailConstant;
import com.atjl.email.api.MailException;
import com.atjl.email.api.MailService;
import com.atjl.email.api.SendMailDto;
import com.atjl.email.dto.MailConfigDto;
import com.atjl.email.dto.SendResultDto;
import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.file.FileUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.net.http.HttpClientUtil;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件服务
 */
@Component(MailConstant.MAIL_SERVICE_USE_GENERAL_PLAT)
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory
            .getLogger(MailServiceImpl.class);

    @Resource(name = ConfigConstant.CONF_SERVICE_USE_DB_TREE)
    private ConfigService configService;

    private MailConfigDto getConfig() {
        MailConfigDto configDto = new MailConfigDto();

        Map<String, String> map = configService.getBatch(MailConstant.CONFS);
        for (String confKey : MailConstant.CONFS) {
            if (StringCheckUtil.isEmpty(map.get(confKey))) {
                throw new MailException("get config null,keys:" + confKey);
            }
            switch (confKey) {
                case MailConstant.CONNECTION_TIMEOUT:
                    if (!RegexUtil.isDigit(map.get(confKey))) {
                        map.put(MailConstant.CONNECTION_TIMEOUT, MailConstant.DFT_CONNECTION_TIMEOUT_VAL);
                    }
                    break;
                case MailConstant.SOCKET_TIMEOUT:
                    if (!RegexUtil.isDigit(map.get(confKey))) {
                        map.put(MailConstant.SOCKET_TIMEOUT, MailConstant.DFT_SOCKET_TIMEOUT_VAL);
                    }
                    break;
            }
        }
        configDto.setSrvUrl(map.get(MailConstant.SRV_URL));
        configDto.setAccessId(map.get(MailConstant.ACCESS_ID));
        configDto.setAccessToken(map.get(MailConstant.ACCESS_TOKEN));
        configDto.setConnectionTimeOut(Integer.parseInt(map.get(MailConstant.CONNECTION_TIMEOUT)));
        configDto.setSocketTimeOut(Integer.parseInt(map.get(MailConstant.SOCKET_TIMEOUT)));
        configDto.setMsgType(map.get(MailConstant.MSG_TYPE));
        configDto.setTemplateCode(map.get(MailConstant.TEMPLATE_CODE));

        if (logger.isDebugEnabled()) {
            logger.debug("email send config: {}", JSONFastJsonUtil.objectToJson(map));
        }
        return configDto;
    }


    public void sendMail(String toAddress, String title, String htmlContent) {
        SendMailDto sendMailDto = new SendMailDto();
        sendMailDto.setSubject(title);
        sendMailDto.setUserId(toAddress);
        sendMailDto.setContent(htmlContent);
        this.sendMail(toAddress, title, htmlContent, null, null);
    }

    public void sendMail(String toAddress, String title, String htmlContent, String filePath, String fileShowName) {
        SendMailDto sendMailDto = new SendMailDto();
        sendMailDto.setSubject(title);
        sendMailDto.setUserId(toAddress);
        sendMailDto.setContent(htmlContent);
        if (filePath != null) {
            sendMailDto.setAttachmentType(MailConstant.ATTACHMENT_TYPE_FILE);
            sendMailDto.setAttachmentPath(filePath);
            sendMailDto.setAttachmentName(fileShowName);
        } else {
            sendMailDto.setAttachmentType(MailConstant.ATTACHMENT_TYPE_NO);
        }
        this.sendMail(sendMailDto);
    }


    @Override
    public void sendMail(SendMailDto contentDto) {
        //参数校验
        if (contentDto == null) {
            throw new MailException("邮件发送，发送参数为空");
        }
        if (!MailConstant.validAttachmentType(contentDto.getAttachmentType())) {
            throw new MailException("邮件发送，附件类型错误" + contentDto.getAttachmentType());
        }
        if (StringCheckUtil.isEmptyTrim(contentDto.getUserId())) {
            throw new MailException("邮件发送，接收人为空");
        }

        //读取附件
        if (MailConstant.ATTACHMENT_TYPE_FILE.equals(contentDto.getAttachmentType())) {
            byte[] byteArr = FileUtil.catRaw(contentDto.getAttachmentPath());
            contentDto.setAttachmentArray(byteArr);
        }

        MailConfigDto conf = getConfig();
        try {
            Map<String, Object> confMap = ReflectUtil.getFieldValue(conf, ReflectUtil.GetClzOpt.ALL, true, CollectionUtil.newArr("srvUrl", "connectionTimeOut", "socketTimeOut"), null);
            Map<String, Object> contentMap = ReflectUtil.getFieldValue(contentDto, ReflectUtil.GetClzOpt.ALL, true, CollectionUtil.newArr("attachmentType","attachmentPath" ,"content"), null);
//            Map<String, Object> contentMap = ReflectUtil.getFieldValueMapAll(contentDto, true);
            Map<String, Object> sendMap = new HashMap<>();
            sendMap.putAll(confMap);
            sendMap.putAll(contentMap);
            sendMap.put("templateParam", CollectionUtil.newMap("param", contentDto.getContent()));

            String contentJson = JSONFastJsonUtil.objectToJson(sendMap);
            if (logger.isDebugEnabled()) {
                logger.debug("send mail {}", contentJson);
            }
            String res = HttpClientUtil.sendPostJson(conf.getSrvUrl(), contentJson, conf.getConnectionTimeOut(), conf.getSocketTimeOut());
            if (StringCheckUtil.isEmpty(res)) {
                throw new MailException("发送失败，返回为空");
            }
            SendResultDto resDto = JSONFastJsonUtil.jsonToObject(res, SendResultDto.class);
            if (!resDto.isSuccess()) {
                throw new MailException("发送失败，错误码:" + resDto.getErrorCode() + ",错误信息：" + resDto.getErrorMessage());
            }
        } catch (Exception e) {
            throw new MailException(e);
        }
    }

    /**
     * 发送邮件
     *
     * @param toAddress   收件人，多个联系人用","分隔
     * @param title       标题
     * @param htmlContent 内容
     * @param filePath    附件
     * @return
     *
     @Override public boolean sendMail(String toAddress, String title, String htmlContent, String filePath, String fileShowName) {
     /*
     LogClient.writeInfo(LogConstant.MODULE_EMAIL, String.format("send email,address %s,title %s,content %s,filepath %s,fileShowName %s", toAddress, title, htmlContent, filePath, fileShowName));
     //        if (DevContext.isDev()) {
     //            toAddress = MailConstant.DEV_RECEIVER;
     //        }
     try {
     MailDto mail = getConfig();
     //mail.setMailTOAddress();
     mail.setAddress(toAddress.trim(), MailDto.TO);

     if (!StringCheckUtil.isEmpty(filePath)) {
     mail.setFileAttachment(MimeUtility.encodeText(fileShowName, "utf-8", "B"), filePath);
     } else {
     mail.setMailType(MailDto.SIMPLE_MAIL);
     }
     mail.setSubject(MimeUtility.encodeText(title, "utf-8", "B"));
     mail.setHtmlBody(htmlContent);
     if (logger.isDebugEnabled()) {
     logger.debug("send mail : {}", JSONFastJsonUtil.objectToJson(mail));
     }
     send(mail);
     return true;
     } catch (Exception e) {
     LogClient.writeError(MailService.class, "邮件发送异常！", e);
     return false;
     }*/
//        return false;
//    }*/


    /*
    @Override
    public boolean sendMail(String toAddress, String title, String htmlContent, String filePath, String mailHost, String mailAddress, String mailUser, String mailPassword) {
        try {
            MailDto mail = new MailDto();
            mail.setAddress(toAddress.trim(), MailDto.TO);
            mail.setFromAddress(mailAddress);
            mail.setSMTPHost(mailHost, null, mailUser, mailPassword);
            if (!StringCheckUtil.isEmpty(filePath)) {
                mail.setFileAttachment(filePath);
            } else {
                mail.setMailType(1);
            }
            mail.setSubject(new String(title.getBytes("UTF-8"), "UTF-8"));
            mail.setHtmlBody(htmlContent);
            send(mail);
            return true;
        } catch (Exception e) {
            logger.error("邮件发送异常！" + e.getMessage());
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            return false;
        }
    }*/


    /**
     * @param mail
     * @return
     * @throws EmailException
     *
    public boolean send(MailDto mail) throws MailException {
    MultiPartEmail mailclass = new MultiPartEmail();
    mailclass.setDebug(mail.isDebug());
    if (mail.getMailTOAddress() != null) {
    for (int i = 0; i < mail.getMailTOAddress().length; i++) {
    mailclass.addTo(mail.getMailTOAddress()[i]);
    }
    }

    if (mail.getAddChaoSong() != null) {
    String[] mailAddr = mail.getAddChaoSong().split(",");
    for (int i = 0; i < mailAddr.length; i++) {
    mailclass.addCc(mailAddr[i]);
    }
    }

    mailclass.setHostName(mail.getMailSMTPHost());
    mailclass.setSmtpPort(Integer.parseInt(mail.getMailSMTPHostPort()));
    // 設置郵箱显示名
    if (mail.getMailShowName() != null) {
    mailclass.setFrom(mail.getMailFromAddress(), mail.getMailShowName());
    } else {
    mailclass.setFrom(mail.getMailFromAddress(), mail.getMailUser());
    }

    // mailclass.setAuthentication(this.mailUser, this.mailPassword);
    mailclass.setSubject(mail.getMailSubject());
    mailclass.setCharset("UTF-8");
    mailclass.setContent(mail.getMailBody());

    //        if (MailDto.SIMPLE_MAIL == mail.getMailType()) {
    //            mailclass.setMsg(mail.getMailContent());
    //email.setContent(mailMessage, "text/plain;charset=GBK");
    //        } else {
    //        }
    // 不需要回执的标记
    mailclass.send();
    return true;
    }
     */

}
