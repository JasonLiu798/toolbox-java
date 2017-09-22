package com.atjl.kafka.domain;

import com.atjl.kafka.domain.constant.KafkaInnerConstant;

/**
 * offset范围
 *
 * @since 1.0
 */
public class OffsetRange {
    private long startOffset;
    private long endOffset;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void incrCount() {
        this.count++;
    }

    /**
     * 未提交的 offset range 最多在context map，被检查OFFSET_STAY_IN_MAP_COUNT，被强制提交
     *
     * @return
     */
    public boolean isReachMaxCount() {
        return this.count > KafkaInnerConstant.OFFSET_STAY_IN_MAP_COUNT;
    }


    public OffsetRange(long startOffset) {
        this.startOffset = startOffset;
        this.endOffset = startOffset;
        this.count = 0;
    }

    public OffsetRange(long startOffset, long endOffset) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.count = 0;
    }

    public void setStartOffset(long startOffset) {
        this.startOffset = startOffset;
    }

    public void setEndOffset(long endOffset) {
        this.endOffset = endOffset;
    }

    public long getStartOffset() {
        return startOffset;
    }

    public long getEndOffset() {
        return endOffset;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OffsetRange that = (OffsetRange) o;

        if (startOffset != that.startOffset) return false;
        return endOffset == that.endOffset;

    }

    @Override
    public int hashCode() {
        int result = (int) (startOffset ^ (startOffset >>> 32));
        result = 31 * result + (int) (endOffset ^ (endOffset >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OffsetRange{" +
                "startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                '}';
    }

}
