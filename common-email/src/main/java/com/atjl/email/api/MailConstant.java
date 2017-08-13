package com.atjl.email.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 邮件 相关常量
 */
public class MailConstant {
    private MailConstant() {
        throw new UnsupportedOperationException();
    }

    public static final String MAIL_SERVICE_USE_GENERAL_PLAT = "mailServiceUseGeneralPlatImpl";

    public static final String ATTACHMENT_TYPE_NO = "no";
    public static final String ATTACHMENT_TYPE_BYTE = "byte";
    public static final String ATTACHMENT_TYPE_FILE = "file";

    // 邮件相关 pathKey
    public static final String DICT_PATH_MAIL = "BisArguSetting.email";

    // 邮件
//    public static final String DICT_MAIL_HOST_KEY = DICT_PATH_MAIL + "host";
    // 发送地址
//    public static final String DICT_MAIL_SEND_ADDRESS_KEY = DICT_PATH_MAIL + "sendaddress";
    // 用户
//    public static final String DICT_MAIL_USER_KEY = DICT_PATH_MAIL + "user";
    // 密码
//    public static final String DICT_MAIL_PASS_KEY = DICT_PATH_MAIL + "mailpass";
    //public static final String DEV_RECEIVER = "sfuat888@sfuat.com";


    public static final String SRV_URL = DICT_PATH_MAIL + ".srvUrl";
    public static final String ACCESS_ID = DICT_PATH_MAIL + ".accessId";
    public static final String ACCESS_TOKEN = DICT_PATH_MAIL + ".accessToken";
    public static final String MSG_TYPE = DICT_PATH_MAIL + ".msgType";
    public static final String EXPECTED_TIME = DICT_PATH_MAIL + ".expectedTime";
    public static final String CONNECTION_TIMEOUT = DICT_PATH_MAIL + ".connectionTimeOut";
    public static final String DFT_CONNECTION_TIMEOUT_VAL = "3000";
    public static final String SOCKET_TIMEOUT = DICT_PATH_MAIL + ".socketTimeOut";
    public static final String DFT_SOCKET_TIMEOUT_VAL = "3000";
    public static final String TEMPLATE_CODE = DICT_PATH_MAIL + ".templateCode";
    public static final String HELLO = "BaseSetting.email.hello";

    //    public static Map<String, String> DFT_CONF = new HashMap<>();
    public static final List<String> CONFS = new ArrayList<>();
    public static final Set<String> ATTACHMENT_TYPE = new HashSet<>();

    public static final String PROP_CONTENT = "content";
    /**
     * 附加是否有效
     * @param type
     * @return
     */
    public static boolean validAttachmentType(String type){
        return ATTACHMENT_TYPE.contains(type);
    }

    /*
     // 邮件称呼
    public static final String DICT_MAIL_HELLO_PK = DICT_PATH_MAIL + ".hello";
        accessId;
    accessToken;
    msgType

    expectedTime
    connectionTimeOut;
    socketTimeOut;
    */

    static {

        CONFS.add(SRV_URL);
        CONFS.add(ACCESS_ID);
        CONFS.add(ACCESS_TOKEN);
        CONFS.add(MSG_TYPE);
//        CONFS.add(EXPECTED_TIME);
        CONFS.add(CONNECTION_TIMEOUT);
        CONFS.add(SOCKET_TIMEOUT);
        CONFS.add(HELLO);
        CONFS.add(TEMPLATE_CODE);

        ATTACHMENT_TYPE.add(ATTACHMENT_TYPE_NO);
        ATTACHMENT_TYPE.add(ATTACHMENT_TYPE_BYTE);
        ATTACHMENT_TYPE.add(ATTACHMENT_TYPE_FILE);
    }
}
