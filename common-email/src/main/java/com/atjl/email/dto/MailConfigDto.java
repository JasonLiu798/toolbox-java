package com.atjl.email.dto;

/**
 * 邮件发送配置
 */
public class MailConfigDto {

    private String srvUrl;//服务器地址

    /**
     * 模板编码，即管理平台上的业务模板编码。（必填）
     */
    private String templateCode;

    /**
     * 发送超时时间 ，默认 3000毫秒
     */
    private int connectionTimeOut;
    /**
     * socket连接超时 ，默认 3000毫秒
     */
    private int socketTimeOut;

    /**
     * 消息类型，目前仅支持文本，直接传值”txt”。（必填）
     */
    private String msgType;

    private String accessId;

    private String accessToken;

    public MailConfigDto() {
    }

    public String getSrvUrl() {
        return srvUrl;
    }

    public void setSrvUrl(String srvUrl) {
        this.srvUrl = srvUrl;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
