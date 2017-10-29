package com.atjl.dbfront.constant;

import java.util.HashMap;

public class ContentConstant {
    private ContentConstant() {
        throw new UnsupportedOperationException();
    }

    public static final String MODULE_STATIC = "static";
    /**
     * URLs
     */
//    public static final String INDEX = "module/index.html";
//    public static final String INDEX_ = "/";
    public static final String CONTENT = "content";
    public static final String CONTENT_MGR = "contentmgr";

    public static final String JS = "getscript";
    public static final String JS_PATH = "script/{name}/{ver}";

    public static final String CSS = "getstyles";

    public static final String HTML = "html";
    public static final String PUB_IDX = "pub";
    public static final String ROLLBACK_IDX = "/rollback";
    public static final String UPD_CONTENT = "upd";
    public static final String UPD_CONTENT_FILE = "updfile";

    public static final String IDX_NAME = "index";

    /**
     * static file types
     */
    //type html
    public static final String TP_HTML = "H";
    //type js
    public static final String TP_JS = "J";
    //type css
    public static final String TP_CSS = "C";

    /**
     * content type
     */
    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final String CONTENT_TYPE_JS = "application/javascript";
    public static final String CONTENT_TYPE_CSS = "text/css";

    //version cur
    public static final String CUR_VER = "CUR";

//    private static final Set<String> TP_SET = new HashSet<>();
    private static final HashMap<String, String> TP_TO_CONTENT_MAP = new HashMap<>();

    static {
//        TP_SET.add(TP_HTML);
//        TP_SET.add(TP_JS);
//        TP_SET.add(TP_CSS);
        TP_TO_CONTENT_MAP.put(TP_HTML, CONTENT_TYPE_HTML);
        TP_TO_CONTENT_MAP.put(TP_JS, CONTENT_TYPE_JS);
        TP_TO_CONTENT_MAP.put(TP_CSS, CONTENT_TYPE_CSS);
    }

    /**
     * 校验 类型
     * @param tp
     * @return
     */
    public static boolean chkTp(String tp) {
        return TP_TO_CONTENT_MAP.containsKey(tp);
    }
}
