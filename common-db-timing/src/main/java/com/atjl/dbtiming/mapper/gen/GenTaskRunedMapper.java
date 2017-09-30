package com.atjl.dbtiming.mapper.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.atjl.dbtiming.domain.gen.GenTaskRuned;
import com.atjl.dbtiming.domain.gen.GenTaskRunedExample;

public interface GenTaskRunedMapper {
    int countByExample(GenTaskRunedExample example);

    int deleteByExample(GenTaskRunedExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(GenTaskRuned record);

    int insertSelective(GenTaskRuned record);

    List<GenTaskRuned> selectByExample(GenTaskRunedExample example);

    GenTaskRuned selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") GenTaskRuned record, @Param("example") GenTaskRunedExample example);

    int updateByExample(@Param("record") GenTaskRuned record, @Param("example") GenTaskRunedExample example);

    int updateByPrimaryKeySelective(GenTaskRuned record);

    int updateByPrimaryKey(GenTaskRuned record);
}