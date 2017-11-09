package com.atjl.jetty.util;

import java.util.List;

public class JettyCommonUtil {
    private JettyCommonUtil(){
        super();
    }

    public static String format(List<String> cps) {
        StringBuilder sb = new StringBuilder();
        for (String cp : cps) {
            sb.append(cp).append(";");
        }
        return sb.toString();
    }
}
