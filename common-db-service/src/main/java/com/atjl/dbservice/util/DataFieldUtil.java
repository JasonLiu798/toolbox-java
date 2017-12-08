package com.atjl.dbservice.util;


import java.util.List;

public class DataFieldUtil {

    public static String field2string(List<String> field) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int last = field.size() - 1;
        for (String f : field) {
            sb.append(f);
            if (i != last) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }


    private DataFieldUtil(){
        super();
    }
}
