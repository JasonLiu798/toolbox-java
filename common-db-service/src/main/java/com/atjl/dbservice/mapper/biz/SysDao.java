package com.atjl.dbservice.mapper.biz;

import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface SysDao {

	void insert(@Param("p_classname") String classname, @Param("p_methodname") String methodname,
                @Param("p_key") String key, @Param("p_content") String content);

	List<LinkedHashMap<String,Object>> search(@Param("sqltext") String sqlText);
	
	int insertUpdate(@Param("sqltext") String sqlText);

	int clearTable(@Param("tableName") String tableName);
	
	/*int updateSequence(@Param("p_code") String code);
	
	int getSequence(@Param("p_code") String code);*/
	
}
