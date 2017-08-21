package com.atjl.configdb.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ConfigPlainMapper {
    String get(String pathKey);

    List<Map<String, String>> gets(String path);

    List<Map<String, String>> getBatch(@Param("list") List<String> param);

    int set(@Param("key") String pathKey, @Param("value") String val);

}
