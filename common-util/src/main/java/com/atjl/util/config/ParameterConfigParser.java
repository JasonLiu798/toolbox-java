package com.atjl.util.config;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * int类型 转 boolean list
 * <p>
 * Created by JasonLiu798 on 16/5/31.
 */
public class ParameterConfigParser {
	private ParameterConfigParser() {
		throw new UnsupportedOperationException();
	}
    private static final Logger logger = LoggerFactory.getLogger(ParameterConfigParser.class);

    /**
     * "1101"
     * => 13
     *
     * @param binary
     * @return
     */
    public static int binaryString2Int(String binary) {
        int res = 0;
        if (StringCheckUtil.isEmpty(binary)) {
            return res;
        }
        byte[] bytes = binary.getBytes();
        int mask = 1;
        for (byte binaryByte : bytes) {
            if (binaryByte != '0') {
                res = res | mask;
            }
            mask = mask << 1;
        }
        return res;
    }

    /**
     * true,false,true
     * =>5
     *
     * @param list
     * @return
     */
    public static int booleanList2Int(List<Boolean> list) {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }
        int len = list.size();
        int res = 0;
        if (list.size() > 32) {
            len = 32;
        }
        int mask = 1;
        for (Boolean item : list) {
            if (item) {
                res = res | mask;
            }
            mask = mask << 1;
        }
        return res;
    }

    /**
     * 12,4
     * =>1100
     * =>true,true,false,false
     *
     * @param configNum
     * @return
     */
    public static List<Boolean> int2bits(int configNum, int size) {
        List<Boolean> res = new ArrayList<>(size);
        if (size < 0) {
            return res;
        }
        int max = (int) Math.pow(2, size) - 1;
        if (configNum < 0) {
            throw new IllegalArgumentException("config num must > 0,config num " + configNum);
        }
        if (configNum > max && logger.isWarnEnabled()) {
            logger.warn("config num {} out of range 0~{},will only use the low bit", configNum, max);
        }
        int mask = 1;
        for (int i = 0; i < size; i++) {
            int bitVal = mask & configNum;
            if (bitVal == 0) {
                res.add(false);
            } else {
                res.add(true);
            }
            mask = mask << 1;
        }
        return res;
    }


}
