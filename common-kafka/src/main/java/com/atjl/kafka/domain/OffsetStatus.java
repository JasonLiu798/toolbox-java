package com.atjl.kafka.domain;

/**
 * OffsetStatus
 *
 * @since 1.0
 */
public class OffsetStatus {
    private OffsetRange offsetRange;
    private Boolean processed;

    public OffsetStatus(OffsetRange offsetRange, Boolean processed) {
        this.offsetRange = offsetRange;
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "OffsetStatus{" +
                "offsetRange=" + offsetRange +
                ", processed=" + processed +
                '}';
    }

    public OffsetRange getOffsetRange() {
        return offsetRange;
    }

    public void setOffsetRange(OffsetRange offsetRange) {
        this.offsetRange = offsetRange;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }
}
