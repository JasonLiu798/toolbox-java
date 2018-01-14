package com.atjl.dbservice.mapper.biz;


import com.atjl.dbservice.api.domain.DataBaseConfig;
import com.atjl.dbservice.api.domain.DataCoverteConfig;
import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.common.domain.KeyValue;
import com.atjl.dbservice.domain.TgtTableDataPkg;
import com.atjl.dbservice.domain.TgtTableDataUpdatePkg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataTransferMapper {

    /**
     * 导数据相关
     */
    List<Map> getRawTableData(@Param("config") DataCpConfig req, @Param("otherCond") SearchCondBase searchCondBase);

    int getRawTableDataCount(@Param("config") DataCpConfig req, @Param("otherCond") SearchCondBase searchCondBase);


    /**
     * 转换相关
     */
    List<Map> getCoverteTableData(@Param("config") DataCoverteConfig config, @Param("otherCond") SearchCondBase searchCondBase);

    int getCoverteTableCount(@Param("config") DataCoverteConfig config, @Param("otherCond") SearchCondBase searchCondBase);


//    int getTgtTableDataCout(@Param("config") DataCpConfig req, @Param("conds") List<CondValue> conds);

    List<Map> getTgtTableData(@Param("config") DataCpConfig req, @Param("conds") List<List<KeyValue>> conds);

    int insertBatch(@Param("config") DataCpConfig req, @Param("dataPkg") TgtTableDataPkg dataPkg);

    int updateBatch(@Param("config") DataBaseConfig req, @Param("dataPkg") TgtTableDataUpdatePkg dataPkg);

//    int updateCovBatch(@Param("config") DataCoverteConfig conf, @Param("dataPkg") TgtTableDataUpdatePkg dataPkg);
}
