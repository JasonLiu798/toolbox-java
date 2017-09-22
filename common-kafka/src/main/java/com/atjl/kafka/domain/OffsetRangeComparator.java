package com.atjl.kafka.domain;

import java.util.Comparator;

/**
 * CommitDataComparator
 *
 * @since 1.0
 */
public class OffsetRangeComparator implements Comparator<OffsetRange> {

    @Override
    public int compare(OffsetRange o1, OffsetRange o2) {
        long offsetDiff = o1.getStartOffset() - o2.getStartOffset();
        int offsetDiffInt = Integer.parseInt(String.valueOf(offsetDiff));
        return offsetDiffInt;
    }
}
