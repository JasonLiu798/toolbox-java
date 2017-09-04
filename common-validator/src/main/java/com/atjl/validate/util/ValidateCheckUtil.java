package com.atjl.validate.util;

import com.atjl.util.collection.CollectionUtil;
import java.util.List;

public class ValidateCheckUtil {
    private ValidateCheckUtil() {
        super();
    }

    public static boolean existSameField(List<String> fiedlNames) {
        return CollectionUtil.existDuplicate(fiedlNames);
    }
}
