package com.atjl.email.api;


public class MailException extends RuntimeException {
    public MailException() {
        super();
    }

    public MailException(Throwable e) {
        super(e);
    }

    public MailException(String msg,Throwable e) {
        super(msg,e);
    }

    public MailException(String msg) {
        super("邮件异常，" + msg);
    }
}
