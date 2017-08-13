package com.atjl.email.api;


import java.util.Date;

/**
 * 邮件发送 内容dto
 */
public class SendMailDto {
    private String subject;//标题
    private String content;//业务模板对应渠道模板的参数，传Json格式的字符串->param
    private String userId;//收件人->userId
    private String ccId;//抄送人->ccId

    private Date expectedTime=null;//期待发送时间

    private String attachmentType = MailConstant.ATTACHMENT_TYPE_NO;
    private String attachmentPath;//附件地址，如不为空，则邮件服务自行读取文件内容
    private byte[] attachmentArray;//附件内容
    private String attachmentName;//附件名称


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserId() {
        return userId;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public byte[] getAttachmentArray() {
        return attachmentArray;
    }

    public void setAttachmentArray(byte[] attachmentArray) {
        this.attachmentArray = attachmentArray;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
