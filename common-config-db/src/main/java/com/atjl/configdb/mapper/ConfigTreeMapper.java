package com.atjl.configdb.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ConfigTreeMapper {
    String get(String pathKey);

    List<Map<String, String>> gets(String path);

    List<Map<String, String>> getBatch(@Param("list") List<String> param);

    int set(@Param("key") String pathKey,@Param("value") String val);
//    List<Map<String, String>> getBatch(List<String> param);

}
