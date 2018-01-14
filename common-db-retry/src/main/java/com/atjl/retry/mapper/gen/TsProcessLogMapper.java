package com.atjl.retry.mapper.gen;

import com.atjl.retry.domain.gen.TsProcessLog;
import com.atjl.retry.domain.gen.TsProcessLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsProcessLogMapper {
    int countByExample(TsProcessLogExample example);

    int deleteByExample(TsProcessLogExample example);

    int deleteByPrimaryKey(Long dataProcessId);

    int insert(TsProcessLog record);

    int insertSelective(TsProcessLog record);

    List<TsProcessLog> selectByExampleWithBLOBs(TsProcessLogExample example);

    List<TsProcessLog> selectByExample(TsProcessLogExample example);

    TsProcessLog selectByPrimaryKey(Long dataProcessId);

    int updateByExampleSelective(@Param("record") TsProcessLog record, @Param("example") TsProcessLogExample example);

    int updateByExampleWithBLOBs(@Param("record") TsProcessLog record, @Param("example") TsProcessLogExample example);

    int updateByExample(@Param("record") TsProcessLog record, @Param("example") TsProcessLogExample example);

    int updateByPrimaryKeySelective(TsProcessLog record);

    int updateByPrimaryKeyWithBLOBs(TsProcessLog record);

    int updateByPrimaryKey(TsProcessLog record);

    int insertBatchSelective(List<TsProcessLog> records);

    int updateBatchByPrimaryKeySelective(List<TsProcessLog> records);
}