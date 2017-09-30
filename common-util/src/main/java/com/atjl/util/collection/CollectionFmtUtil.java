package com.atjl.util.collection;


import java.util.List;

public class CollectionFmtUtil {
    private CollectionFmtUtil() {
        throw new UnsupportedOperationException();
    }


    /**
     * 自行保证 对象不为空，否则会出现null
     *
     * @param l
     * @param <T>
     * @return
     */
    public static <T> String list2str(List<T> l) {
        if (CollectionUtil.isEmpty(l)) {
            return "";
        }
        String res = "";
        int lastIdx = l.size() - 1;
        for (int i = 0; i < l.size(); i++) {
            if (i == lastIdx) {
                res = res + l.get(i);
            } else {
                res = res + l.get(i) + ",";
            }
        }
        return res;
    }


}
