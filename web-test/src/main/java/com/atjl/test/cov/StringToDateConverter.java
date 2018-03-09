package com.atjl.test.cov;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory
            .getLogger(StringToDateConverter.class);

    private final DateFormat DF_LONG = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final DateFormat DF_MEDIUM = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    private final DateFormat DF_SHORT = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 短类型日期长度
     */
    private static final int SHORT_DATE = 10;

    private static final int EDIUM_DATE = 16;

    @Override
    public Date convert(String source) {
        Date date = null;
        if (StringUtils.isBlank(source)) {
            return date;
        }
        try {
            if (source.length() <= SHORT_DATE) {
                date = DF_SHORT.parse(source);
            } else if (EDIUM_DATE == source.length()) {
                date = DF_MEDIUM.parse(source);
            } else {
                date = DF_LONG.parse(source);
            }
        } catch (ParseException e) {
            logger.error("request data String parse to Date error :", e);
            throw new RuntimeException(e);
        }

        return date;
    }
}
