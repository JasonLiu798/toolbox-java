package com.atjl.dbservice.api.validator;


import com.atjl.dbservice.api.RawDataDuplicateChecker;

import java.util.Map;

public class DftRawDataDuplicateCheckerByLoadTm implements RawDataDuplicateChecker {
    @Override
    public boolean keepWhich(Map raw1, Map raw2) {
        String l1 = String.valueOf(raw1.get("load_tm"));
        String l2 = String.valueOf(raw2.get("load_tm"));
        return l1.compareTo(l2) > 0;
    }
}
