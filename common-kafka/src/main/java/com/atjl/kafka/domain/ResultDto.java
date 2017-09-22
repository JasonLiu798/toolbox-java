package com.atjl.kafka.domain;


import com.atjl.kafka.api.domain.MQCode;

/**
 * ResponseInnerDto
 *
 * @since 1.0
 */
public class ResultDto<T> {
    private MQCode code;
    private T data;

    public ResultDto() {
        code = MQCode.SUCCESS;
        data = null;
    }

    public ResultDto(T data) {
        code = MQCode.SUCCESS;
        this.data = data;
    }

    public ResultDto(MQCode code) {
        this.code = code;
    }

    public ResultDto(MQCode code, T data) {
        this.code = code;
        this.data = data;
    }

    public MQCode getCode() {
        return code;
    }

    public void setCode(MQCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseInnerDto{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
