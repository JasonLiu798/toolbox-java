package com.atjl.email.dto;

import com.atjl.email.api.MailException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.MultiPartEmail;
import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.net.URL;


/**
 * mail
 */
public class MailDto {//implements Serializable {

    private static final String charset = "UTF-8";

    private static final long serialVersionUID = 1L;

    public static final int SIMPLE_MAIL = 0;

    public static final int MUTIL_MAIL = 1;


    private int mailType = SIMPLE_MAIL;

    /**
     * 发件方式 - 普通发送
     */
    public static final int TO = 0;

    /**
     * 发件方式 - 抄送
     */
    public static final int CC = 1;
    /**
     * 发件方式 - 抄送人邮件地址
     */
    private String addChaoSong = null;

    /**
     * 发件方式 - 密件抄送
     */
    static final int BCC = 2;

    /**
     * 邮件内容
     */
    private String mailContent = null;

    /**
     * 邮件相关信息 - SMTP 服务器
     */
    private String mailSMTPHost = null;

    private String mailSMTPHostPort = "25";

    /**
     * 邮件相关信息 - 邮件用户名
     */
    private String mailUser = null;

    /**
     * 邮件相关信息 - 密码
     */
    private String mailPassword = null;

    /**
     * 邮件相关信息 - 发件人邮件地址
     */
    private String mailFromAddress = null;

    /**
     * 邮件相关信息 - 邮件主题
     */
    private String mailSubject = "";

    /**
     * 邮件相关信息 - 邮件发送地址
     */
    private String[] mailTOAddress = null;

    /**
     * 邮件相关信息 - 邮件抄送地址
     */
    private String[] mailCCAddress = null;

    /**
     * 邮件相关信息 - 邮件密件抄送地址
     */
    private String[] mailBCCAddress = null;

    /**
     * 邮件相关信息 - 邮件正文(复合结构)
     */
    private MimeMultipart mailBody = null;

    private MultiPartEmail mailclass = null;

    private boolean debug = false;

    private boolean result = false;

    /**
     * 邮件相关信息 - 邮件發送方顯示名
     */
    private String mailShowName = "";


    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public MailDto() {
        mailBody = new MimeMultipart();
    }

    /**
     * 设置 SMTP 服务器
     *
     * @param strSMTPHost 邮件服务器名称或 IP
     * @param strUser     邮件用户名
     * @param strPassword 密码
     */
    public void setSMTPHost(String strSMTPHost, String port, String strUser,
                            String strPassword) {
        this.mailSMTPHost = strSMTPHost;
        this.mailUser = strUser;
        this.mailPassword = strPassword;
        if (port != null && !"".equals(port)) {
            this.mailSMTPHostPort = port;
        }
    }

    /**
     * 设置邮件发送地址
     *
     * @param strFromAddress 邮件发送地址
     */
    public void setFromAddress(String strFromAddress) {
        this.mailFromAddress = strFromAddress;
    }

    /**
     * 设置邮件发送地址
     *
     * @param strFromAddress 邮件发送地址
     * @param mailShowName   邮件顯示名
     */
    public void setFromAddress(String strFromAddress, String mailShowName) {
        this.mailFromAddress = strFromAddress;
        this.mailShowName = mailShowName;
    }

    /**
     * 设置邮件目的地址
     *
     * @param strAddress   邮件目的地址列表, 不同的地址可用;号分隔
     * @param iAddressType 邮件发送方式 (TO 0, CC 1, BCC 2) 常量已在本类定义
     */
    public void setAddress(String strAddress, int iAddressType) {
        String[] mailAddr = strAddress.split(",");
        switch (iAddressType) {
            case MailDto.TO:
                mailTOAddress = mailAddr;
                break;
            case MailDto.CC:
                mailCCAddress = mailAddr;
                break;
            case MailDto.BCC:
                mailBCCAddress = mailAddr;
                break;
            default:
                mailTOAddress = mailAddr;
                break;
        }
    }

    /**
     * 设置邮件主题
     *
     * @param strSubject 邮件主题
     */
    public void setSubject(String strSubject) {
        this.mailSubject = strSubject;
    }

    /**
     * 设置邮件文本正文
     *
     * @param strTextBody 邮件文本正文
     * @throws MessagingException
     */
    public void setTextBody(String strTextBody) throws MessagingException {
        MimeBodyPart mimebodypart = new MimeBodyPart();
        mimebodypart.setText(strTextBody, charset);
        mailBody.addBodyPart(mimebodypart);
        mailContent = strTextBody;
    }

    /**
     * 设置邮件超文本正文
     *
     * @param strHtmlBody 邮件超文本正文
     * @throws MessagingException
     */
    public void setHtmlBody(String strHtmlBody) throws MessagingException {
        MimeBodyPart mimebodypart = new MimeBodyPart();
        mimebodypart.setDataHandler(new DataHandler(strHtmlBody,
                "text/html;charset=UTF-8"));
        mailBody.addBodyPart(mimebodypart);
        mailContent = strHtmlBody;
    }

    /**
     * 设置邮件正文外部链接 URL, 信体中将包含链接所指向的内容
     *
     * @param strURLAttachment 邮件正文外部链接 URL
     */
    public void setURLAttachment(String strURLAttachment) {
        MimeBodyPart mimebodypart = new MimeBodyPart();
        try {
            mimebodypart.setDataHandler(new DataHandler(new URL(strURLAttachment)));
            mailBody.addBodyPart(mimebodypart);
        } catch (Exception e) {
            throw new MailException(e);
        }
        this.mailType = MUTIL_MAIL;
    }

    /**
     * 设置邮件附件
     *
     * @param strFileAttachment 文件的全路径
     *
    public void setFileAttachment(String showName, String strFileAttachment) {
        File path = new File(strFileAttachment);
        if (!path.exists() || path.isDirectory()) {
            throw new FileException("文件不存在！");
        }
        try {
            MimeBodyPart mimebodypart = new MimeBodyPart();
            mimebodypart.setDataHandler(new DataHandler(new FileDataSource(
                    strFileAttachment)));
            mimebodypart.setFileName(MimeUtility.encodeText(showName, charset, charset));
            mailBody.addBodyPart(mimebodypart);
        } catch (Exception e) {
            throw new MailException(e);
        }
        this.mailType = MUTIL_MAIL;
    }*/


    /**
     * 设置邮件图片附件
     *
     * @param strFileAttachment 文件的全路径
     *
    public void setImgFileAttachment(String strFileAttachment, String fileNum) {
        File path = new File(strFileAttachment);
        if (!path.exists() || path.isDirectory()) {
            throw new FileException("文件不存在！");
        }
        try {
            String strFileName = path.getName();
            MimeBodyPart mimebodypart = new MimeBodyPart();
            mimebodypart.setDataHandler(new DataHandler(new FileDataSource(
                    strFileAttachment)));
            mimebodypart.setFileName(MimeUtility.encodeText(strFileName));
            mailBody.setSubType("related");
            mimebodypart.setHeader("Content-ID", "IMG" + fileNum);
            mailBody.addBodyPart(mimebodypart);
        } catch (Exception e) {
            throw new MailException(e);
        }
        this.mailType = MUTIL_MAIL;
    }*/

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Email getMailclass() {
        return mailclass;
    }

    public void setMailclass(MultiPartEmail mailclass) {
        this.mailclass = mailclass;
    }

    public int getMailType() {
        return mailType;
    }

    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    public String getMailShowName() {
        return mailShowName;
    }

    public void setMailShowName(String mailShowName) {
        this.mailShowName = mailShowName;
    }

    public String getAddChaoSong() {
        return addChaoSong;
    }

    public void setAddChaoSong(String addChaoSong) {
        this.addChaoSong = addChaoSong;
    }

    public String getMailSMTPHost() {
        return mailSMTPHost;
    }

    public void setMailSMTPHost(String mailSMTPHost) {
        this.mailSMTPHost = mailSMTPHost;
    }

    public String getMailSMTPHostPort() {
        return mailSMTPHostPort;
    }

    public void setMailSMTPHostPort(String mailSMTPHostPort) {
        this.mailSMTPHostPort = mailSMTPHostPort;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailFromAddress() {
        return mailFromAddress;
    }

    public void setMailFromAddress(String mailFromAddress) {
        this.mailFromAddress = mailFromAddress;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String[] getMailTOAddress() {
        return mailTOAddress;
    }

    public void setMailTOAddress(String[] mailTOAddress) {
        this.mailTOAddress = mailTOAddress;
    }

    public String[] getMailCCAddress() {
        return mailCCAddress;
    }

    public void setMailCCAddress(String[] mailCCAddress) {
        this.mailCCAddress = mailCCAddress;
    }

    public String[] getMailBCCAddress() {
        return mailBCCAddress;
    }

    public void setMailBCCAddress(String[] mailBCCAddress) {
        this.mailBCCAddress = mailBCCAddress;
    }

    public MimeMultipart getMailBody() {
        return mailBody;
    }

    public void setMailBody(MimeMultipart mailBody) {
        this.mailBody = mailBody;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static int getSimpleMail() {
        return SIMPLE_MAIL;
    }

    public static int getMutilMail() {
        return MUTIL_MAIL;
    }

    public static int getTO() {
        return TO;
    }

    public static int getCC() {
        return CC;
    }

    public static int getBCC() {
        return BCC;
    }

}
