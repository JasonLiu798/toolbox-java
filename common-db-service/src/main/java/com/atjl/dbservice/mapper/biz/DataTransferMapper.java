package com.atjl.dbservice.mapper.biz;


import com.atjl.dbservice.domain.CondValue;
import com.atjl.dbservice.domain.DbTableTransferConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataTransferMapper {

    List<Map> getRawTableData(@Param("config") DbTableTransferConfig req);

//    int getTgtTableDataCout(@Param("config") DbTableTransferConfig req, @Param("conds") List<CondValue> conds);

    List<Map> getTgtTableData(@Param("config") DbTableTransferConfig req, @Param("conds") List<List<CondValue>> conds);


}
