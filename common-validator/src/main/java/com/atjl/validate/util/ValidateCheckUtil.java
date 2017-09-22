package com.atjl.validate.util;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.ValidateInitException;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.multiparam.RequireWith;
import com.atjl.validate.validator.multiparam.RequireWithAll;
import com.atjl.validate.validator.multiparam.RequireWithOut;
import com.atjl.validate.validator.multiparam.RequireWithOutAll;
import com.atjl.validate.validator.noparam.Optional;
import com.atjl.validate.validator.noparam.Require;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 内部校验 辅助类
 *
 * @author jasonliu
 */
public class ValidateCheckUtil {
    private ValidateCheckUtil() {
        super();
    }

    public static boolean existSameField(List<String> fiedlNames) {
        return CollectionUtil.existDuplicate(fiedlNames);
    }


    public static final String BOOL_YES = "YES";
    public static final String BOOL_Y = "Y";
    public static final String BOOL_TRUE = "TRUE";
    public static final String BOOL_T = "T";
    public static final String BOOL_1 = "1";

    public static final String BOOL_NO = "NO";
    public static final String BOOL_N = "N";
    public static final String BOOL_FALSE = "FALSE";
    public static final String BOOL_F = "F";
    public static final String BOOL_0 = "0";


    public static final Set<String> VALID_BOOL = new HashSet<>();
    public static final Set<String> BOOL_TRUE_SET = new HashSet<>();

    public static final Set<Class> REQUIRE_CLAZZ = new HashSet<>();

    static {
        REQUIRE_CLAZZ.add(Require.class);
        REQUIRE_CLAZZ.add(Optional.class);
        REQUIRE_CLAZZ.add(RequireWith.class);
        REQUIRE_CLAZZ.add(RequireWithAll.class);
        REQUIRE_CLAZZ.add(RequireWithOut.class);
        REQUIRE_CLAZZ.add(RequireWithOutAll.class);

        VALID_BOOL.add(BOOL_Y);
        VALID_BOOL.add(BOOL_YES);
        VALID_BOOL.add(BOOL_T);
        VALID_BOOL.add(BOOL_TRUE);
        VALID_BOOL.add(BOOL_1);

        VALID_BOOL.add(BOOL_N);
        VALID_BOOL.add(BOOL_NO);
        VALID_BOOL.add(BOOL_F);
        VALID_BOOL.add(BOOL_FALSE);
        VALID_BOOL.add(BOOL_0);

        BOOL_TRUE_SET.add(BOOL_Y);
        BOOL_TRUE_SET.add(BOOL_YES);
        BOOL_TRUE_SET.add(BOOL_T);
        BOOL_TRUE_SET.add(BOOL_TRUE);
        BOOL_TRUE_SET.add(BOOL_1);

    }

    public static final boolean isBool(String raw) {
        if (StringCheckUtil.isEmpty(raw)) {
            return false;
        }
        return VALID_BOOL.contains(raw.toUpperCase());
    }

    /**
     * isBool 验证成功后可调用
     *
     * @param raw
     * @return
     */
    public static final boolean boolstr2bool(String raw) {
        return BOOL_TRUE_SET.contains(raw);
    }


    public static void checkValidator(List<Validator> validators) {
        if (CollectionUtil.isEmpty(validators)) {
            throw new ValidateInitException("校验器为空");
        }
        boolean existReq = false;
        for (Validator v : validators) {
            if (existReq) {
                throw new ValidateInitException("Require或Optional校验器只能存在一个");
            }
            if (REQUIRE_CLAZZ.contains(v.getClass())) {
                existReq = true;
            }
        }
    }
}
