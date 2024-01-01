package com.atjl.util.common;


import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.common.domain.BlackWhiteResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 黑白名单处理辅助类
 */
public class BlackWhiteUtil {
    private BlackWhiteUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 默认
     *
     * @param white
     * @param black
     * @param <T>
     * @return
     */
    public static <T> BlackWhiteResp<T> processBlackWhite(T[] white, T[] black) {
        return processBlackWhite(CollectionUtilEx.array2List(white), CollectionUtilEx.array2List(black), false);
    }

    /**
     * 默认白名单优先
     *
     * @param white
     * @param black
     * @return
     */
    public static <T> BlackWhiteResp<T> processBlackWhite(List<T> white, List<T> black) {
        return processBlackWhite(white, black, false);
    }

    /**
     * 处理白名单和黑名单
     *
     * @param white white list
     * @param balck black list
     * @return
     */
    public static <T> BlackWhiteResp<T> processBlackWhite(List<T> white, List<T> balck, boolean blackFirst) {
        boolean filterWhite = false;
        List<T> whiteList;
        if (!CollectionUtilEx.isEmpty(white)) {
            whiteList = white;
            filterWhite = true;
        } else {
            whiteList = new ArrayList<>();
        }

        //init black list
        boolean filterBlack = false;
        List<T> blackList = null;
        if (!CollectionUtilEx.isEmpty(balck)) {
            filterBlack = true;
            if (filterWhite) {
                if (blackFirst) {
                    //黑名单优先，白名单中去除黑名单相关项
                    blackList = CollectionFilterUtil.filterDelList(whiteList, balck);
                } else {
                    //白名单优先，黑名单中去除白名单相关项
                    blackList = CollectionFilterUtil.filterDelList(balck, whiteList);
                }
            } else {
                blackList = balck;
            }
        } else {
            blackList = new ArrayList<>();
        }
        BlackWhiteResp<T> resp = new BlackWhiteResp<>();
        resp.setBlack(blackList);
        resp.setUseBlack(filterBlack);
        resp.setWhite(whiteList);
        resp.setUseWhite(filterWhite);
        return resp;
    }

}
