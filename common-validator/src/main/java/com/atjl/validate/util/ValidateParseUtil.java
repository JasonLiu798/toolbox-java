package com.atjl.validate.util;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.reflect.ReflectFieldUtil;
import com.atjl.validate.api.ValidateParseException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.Validators;

/**
 *
 */
public class ValidateParseUtil {
    private ValidateParseUtil() {
        throw new UnsupportedOperationException();
    }

    public static final String MSG_ = "字符串初始化校验器";

    public static Validator simpleParse(Class clz, String validateStr) {
        String valStr = preCheckAndGetKey(clz, validateStr);
        validateStr = validateStr.toLowerCase();
        if (valStr.equals(validateStr)) {
            Object validatObj = ReflectUtil.getInstance(clz);
            if (validatObj == null) {
                throw new ValidateParseException(MSG_ + ",新建校验器对象失败");
            }
            return (Validator) validatObj;
        }
        throw new ValidateParseException(MSG_ + ",字符串不符合校验器");
    }


    public static Validator oneParamParse(Class clz, String validateStr) {
        String key = preCheckAndGetKey(clz, validateStr);
        validateStr = validateStr.toLowerCase();
        if (!validateStr.contains(key)) {
            throw new ValidateParseException(MSG_ + ",字符串不符合校验器");
        }
        int left = validateStr.indexOf("(");
        int right = validateStr.lastIndexOf(")");
        int len = validateStr.length();
        if (left < 0 || right < 0 || left > right || right != len - 1) {
            throw new ValidateParseException(MSG_ + ",字符串不合规，请参考文档");
        }
        String keyRaw = validateStr.substring(key.length());
        if (!StringCheckUtil.equal(key, keyRaw)) {
            throw new ValidateParseException(MSG_ + ",字符串不合规，请参考文档");
        }
        String param = validateStr.substring(key.length() + 1, len - 1);
        Object validatObj = ReflectUtil.getInstance(clz);

        return null;
    }


    private static String preCheckAndGetKey(Class clz, String validateStr) {
        if (StringCheckUtil.isEmpty(validateStr)) {
            throw new ValidateParseException(MSG_ + "，字符串空");
        }
        validateStr = validateStr.toLowerCase();

        String clzName = clz.getSimpleName();
        String clzNameUpper = clzName.toUpperCase();
        Object val = ReflectFieldUtil.getDeclaredFieldValue(Validators.class, clzNameUpper);
        if (val == null) {
            throw new ValidateParseException(MSG_ + ",不存在的校验器");
        }
        return String.valueOf(val);
    }

}
