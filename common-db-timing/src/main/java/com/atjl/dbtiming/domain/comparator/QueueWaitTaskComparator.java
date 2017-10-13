package com.atjl.dbtiming.domain.comparator;


import com.atjl.dbtiming.domain.biz.QueueWaitTask;

import java.util.Comparator;

/**
 * 小->大 排列
 */
public class QueueWaitTaskComparator implements Comparator<QueueWaitTask> {
    @Override
    public int compare(QueueWaitTask o1, QueueWaitTask o2) {
        return Math.toIntExact(o1.getNextExecuteTs() - o2.getNextExecuteTs());
    }
}
