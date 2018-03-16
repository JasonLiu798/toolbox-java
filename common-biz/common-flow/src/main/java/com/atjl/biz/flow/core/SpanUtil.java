package com.atjl.biz.flow.core;


import com.atjl.biz.flow.api.Flow;

import java.util.List;

public class SpanUtil {
    public static String generateId(List<Flow> executeSeq){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int last = executeSeq.size() - 1;
        for (Flow snippet : executeSeq) {
            String sid = snippet.getId();
            sb.append(sid);
            if (i != last) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    public static String generateId(Flow[] snippets){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int last = snippets.length - 1;
        for (Flow snippet : snippets) {
            String sid = snippet.getId();
            sb.append(sid);
            if (i != last) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

}
