package com.atjl.email.api;

/**
 * 邮件服务
 */
public interface MailService {

    /**
     * 使用默认邮件服务器
     *
     * @param toAddress    收件人，多个联系人用","分隔
     * @param title        标题
     * @param htmlContent  内容
     * @param filePath     附件位置
     * @param fileShowName 附加名
     * @return
     */
    void sendMail(String toAddress, String title, String htmlContent, String filePath, String fileShowName);
    void sendMail(String toAddress, String title, String htmlContent);
    void sendMail(SendMailDto dto);

    /**
     * 增加用户称呼
     * 增加系统链接
     * @param title
     * @param htmlContent
     * @return
     *
    boolean sendFmtMail(String empNum, String title, String htmlContent);
     */

    /**
     * 使用指定邮件服务器
     * @param toAddress 收件人，多个联系人 逗号分隔
     * @param title 标题
     * @param htmlContent 内容
     * @param filePath 附件本地地址
     * @param mailHost
     * @param mailAddress
     * @param mailUser
     * @param mailPassword
     * @return
     *
    boolean sendMail(String toAddress, String title,
    String htmlContent, String filePath,

    String mailHost, String mailAddress,
    String mailUser, String mailPassword);*/

}
