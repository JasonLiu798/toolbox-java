package com.atjl.util.dailylog;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DailyLogBatchUtil {


    /**
     * count content empty item
     */
    public static Integer countEmptyContent(List<TimeItem> items) {
        Integer cnt = 0;
        if (CollectionUtils.isEmpty(items)) {
            return cnt;
        }
        for (TimeItem ti : items) {
            if (StringUtils.isBlank(ti.getContent())) {
                cnt++;
            }
        }
        return cnt;
    }

}
