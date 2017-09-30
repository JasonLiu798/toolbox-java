package com.atjl.dbtiming.mapper.gen;

import java.util.List;

import com.atjl.dbtiming.domain.gen.GenTaskHistory;
import com.atjl.dbtiming.domain.gen.GenTaskHistoryExample;
import org.apache.ibatis.annotations.Param;

public interface GenTaskHistoryMapper {
    int countByExample(GenTaskHistoryExample example);

    int deleteByExample(GenTaskHistoryExample example);

    int deleteByPrimaryKey(Long htid);

    int insert(GenTaskHistory record);

    int insertSelective(GenTaskHistory record);

    List<GenTaskHistory> selectByExample(GenTaskHistoryExample example);

    GenTaskHistory selectByPrimaryKey(Long htid);

    int updateByExampleSelective(@Param("record") GenTaskHistory record, @Param("example") GenTaskHistoryExample example);

    int updateByExample(@Param("record") GenTaskHistory record, @Param("example") GenTaskHistoryExample example);

    int updateByPrimaryKeySelective(GenTaskHistory record);

    int updateByPrimaryKey(GenTaskHistory record);
}