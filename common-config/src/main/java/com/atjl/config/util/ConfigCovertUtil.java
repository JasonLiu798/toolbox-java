package com.atjl.config.util;

import com.atjl.config.api.ConfigException;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class ConfigCovertUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConfigCovertUtil.class);

    private static final Set<Class> SUPPORT_CLZ = new HashSet<>();

    public static final String DFT_SEP = ",";

    static {
        SUPPORT_CLZ.add(Long.class);
        SUPPORT_CLZ.add(Integer.class);
        SUPPORT_CLZ.add(String[].class);
    }

    public static <T> T covert(String rawVal, Class<T> tgtClz) {
        return covert(rawVal, tgtClz, null);
    }

    public static <T> T covert(String rawVal, Class<T> tgtClz, T dft) {
        if (!SUPPORT_CLZ.contains(tgtClz)) {
            if (logger.isInfoEnabled()) {
                logger.info("config covert unsupport clz,raw {},tgt {},dft {}", rawVal, tgtClz, dft);
            }
            return dft;
        }
        if (StringCheckUtil.isEmpty(rawVal)) {
            if (logger.isInfoEnabled()) {
                logger.info("config covert null,raw {},tgt {},dft {}", rawVal, tgtClz, dft);
            }
            return dft;
        }
        if (tgtClz == Long.class || tgtClz == Integer.class) {
            try {
                Method m = tgtClz.getDeclaredMethod("parse" + tgtClz.getSimpleName(), String.class);
                Object res = m.invoke(rawVal, rawVal);
                return (T) res;
            } catch (Exception e) {
                logger.error("str to long fail,{}", e.getMessage());
                return dft;
            }
        } else if (tgtClz == String[].class) {
            String[] res = rawVal.split(DFT_SEP);
            if (CollectionUtil.isEmpty(res)) {
                return dft;
            } else {
                return (T) res;
            }
        }
        throw new ConfigException("unsupported type " + tgtClz);//never reached
    }


}
