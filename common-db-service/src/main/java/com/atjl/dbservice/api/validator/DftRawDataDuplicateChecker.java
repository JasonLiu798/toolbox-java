package com.atjl.dbservice.api.validator;


import com.atjl.dbservice.api.RawDataDuplicateChecker;

import java.util.Map;

public class DftRawDataDuplicateChecker implements RawDataDuplicateChecker {
    @Override
    public boolean keepWhich(Map raw1, Map raw2) {
        return true;
    }
}
