package com.atjl.jetty.util;


import com.atjl.util.collection.CollectionFilterUtil;
import com.atjl.util.collection.CollectionUtil;

import java.util.List;

public class ModulUtil {

    private ModulUtil() {
        throw new UnsupportedOperationException();
    }

    public static List<String> filterExecludeModule(List<String> modules, String[] execludeModule) {
        List<String> nmodules = modules;
        if (CollectionUtil.isNotEmpty(execludeModule)) {
            List<String> excludeModules = CollectionUtil.array2List(execludeModule);
            nmodules = CollectionFilterUtil.filterDelListExistStr(modules, excludeModules);
        }
        return nmodules;
    }
}
