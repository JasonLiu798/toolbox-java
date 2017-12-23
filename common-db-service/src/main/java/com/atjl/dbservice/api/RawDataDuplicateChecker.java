package com.atjl.dbservice.api;


import java.util.Map;

public interface RawDataDuplicateChecker {

    /**
     * true，保留raw1；false保留raw2
     */
    boolean keepWhich(Map raw1, Map raw2);

}
