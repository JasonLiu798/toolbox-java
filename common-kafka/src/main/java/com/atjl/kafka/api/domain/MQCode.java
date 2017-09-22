package com.atjl.kafka.api.domain;

/**
 * kafka error code ,status code
 *
 * @since 1.0
 */
public enum MQCode {
    NO_ERROR,
    OFFSET_OUT_OF_RANGE,
    INVALID_MESSAGE,
    UNKNOWN_TOPIC_OR_PARTITION,
    INVALID_FETCH_SIZE,
    LEADER_NOT_AVAILABLE,
    NOT_LEADER_FOR_PARTITION,
    REQUEST_TIMED_OUT,
    BROKER_NOT_AVAILABLE,
    REPLICA_NOT_AVAILABLE,
    MESSAGE_SIZE_TOO_LARGE,
    STALE_CONTROLLER_EPOCH,
    OFFSET_METADATA_TOO_LARGE,
    UNKNOWN,
    INIT_SIMPLE_CONSUMER_ERROR,
    RE_PROCESS,
    HAS_NOT_BEEN_CONSUME,
    NO_DATA_GET,
    FETCH_DATA_ERROR,
    SUCCESS;

    public static MQCode getError(int errorCode) {
        if (errorCode < 0 || errorCode >= UNKNOWN.ordinal()) {
            return UNKNOWN;
        } else {
            return values()[errorCode];
        }
    }
}
