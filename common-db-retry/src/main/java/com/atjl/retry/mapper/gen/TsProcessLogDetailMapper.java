package com.atjl.retry.mapper.gen;

import com.atjl.retry.domain.gen.TsProcessLogDetail;
import com.atjl.retry.domain.gen.TsProcessLogDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsProcessLogDetailMapper {
    int countByExample(TsProcessLogDetailExample example);

    int deleteByExample(TsProcessLogDetailExample example);

    int deleteByPrimaryKey(Long processLogDetailId);

    int insert(TsProcessLogDetail record);

    int insertSelective(TsProcessLogDetail record);

    List<TsProcessLogDetail> selectByExampleWithBLOBs(TsProcessLogDetailExample example);

    List<TsProcessLogDetail> selectByExample(TsProcessLogDetailExample example);

    TsProcessLogDetail selectByPrimaryKey(Long processLogDetailId);

    int updateByExampleSelective(@Param("record") TsProcessLogDetail record, @Param("example") TsProcessLogDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") TsProcessLogDetail record, @Param("example") TsProcessLogDetailExample example);

    int updateByExample(@Param("record") TsProcessLogDetail record, @Param("example") TsProcessLogDetailExample example);

    int updateByPrimaryKeySelective(TsProcessLogDetail record);

    int updateByPrimaryKeyWithBLOBs(TsProcessLogDetail record);

    int updateByPrimaryKey(TsProcessLogDetail record);

    int insertBatchSelective(List<TsProcessLogDetail> records);

    int updateBatchByPrimaryKeySelective(List<TsProcessLogDetail> records);
}