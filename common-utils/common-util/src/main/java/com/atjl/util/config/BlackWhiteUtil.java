package com.atjl.util.config;

import com.atjl.util.collection.CollectionFilterUtil;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class BlackWhiteUtil {

    public BlackWhiteDto gen(String[] black, String[] white) {
        boolean filterWhite = false;
        List<String> whiteList = null;
        if (white != null && white.length != 0) {
            filterWhite = true;
            whiteList = Arrays.asList(white);
        }

        boolean filterBlack = false;
        List<String> blackList = null;
        if (black != null && black.length != 0) {
            filterBlack = true;
            blackList = Arrays.asList(black);
            if (filterWhite) {
                blackList = CollectionFilterUtil.filterDelList(blackList, whiteList);
            }
        }

        BlackWhiteDto res = new BlackWhiteDto();
        res.setGotBlack(filterBlack);
        res.setGotWhite(filterWhite);
        res.setBlack(blackList);
        res.setWhite(whiteList);
        return res;
    }
}
