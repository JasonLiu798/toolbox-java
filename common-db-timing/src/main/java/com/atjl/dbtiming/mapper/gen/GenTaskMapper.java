package com.atjl.dbtiming.mapper.gen;

import java.util.List;

import com.atjl.dbtiming.domain.gen.GenTaskExample;
import org.apache.ibatis.annotations.Param;
import com.atjl.dbtiming.domain.gen.GenTask;

public interface GenTaskMapper {
    int countByExample(GenTaskExample example);

    int deleteByExample(GenTaskExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(GenTask record);

    int insertSelective(GenTask record);

    List<GenTask> selectByExample(GenTaskExample example);

    GenTask selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") GenTask record, @Param("example") GenTaskExample example);

    int updateByExample(@Param("record") GenTask record, @Param("example") GenTaskExample example);

    int updateByPrimaryKeySelective(GenTask record);

    int updateByPrimaryKey(GenTask record);
}