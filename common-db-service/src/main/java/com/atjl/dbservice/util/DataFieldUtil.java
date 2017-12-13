package com.atjl.dbservice.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public static Map<String, String> raw2tgt(Map rawKV, Map<String, String> mapping) {
        Map<String, String> res = new HashMap<>();
        for (Map.Entry<String, String> pk : mapping.entrySet()) {
            String raw = pk.getKey();
            String tgt = pk.getValue();
            Object v = rawKV.get(raw);
            if (v == null) {
                res.put(tgt, "");
            } else {
                res.put(tgt, String.valueOf(v));
            }
        }
        return res;
    }


    private DataFieldUtil(){
        super();
    }
}
