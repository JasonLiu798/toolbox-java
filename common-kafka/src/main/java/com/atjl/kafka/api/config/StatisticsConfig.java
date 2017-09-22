package com.atjl.kafka.api.config;

import java.io.Serializable;

/**
 * kafka config general
 *
 * @since 1.0
 */
public class StatisticsConfig implements Serializable {
    private static final long serialVersionUID = 7131171734472156742L;

    private boolean fetchOn = false;
    private boolean processOn = false;
    private boolean queueOn = false;
    private int interval = 5000;

    /**
     * construct function
     */
    public StatisticsConfig() {
    }

    public Boolean getFetchOn() {
        return fetchOn;
    }

    public void setFetchOn(Boolean fetchOn) {
        this.fetchOn = fetchOn;
    }

    public Boolean getProcessOn() {
        return processOn;
    }

    public void setProcessOn(Boolean processOn) {
        this.processOn = processOn;
    }

    public Boolean getQueueOn() {
        return queueOn;
    }

    public void setQueueOn(Boolean queueOn) {
        this.queueOn = queueOn;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}

