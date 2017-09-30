package com.atjl.dbtiming.mapper.gen;

import java.util.List;

import com.atjl.dbtiming.domain.gen.GenTaskManager;
import com.atjl.dbtiming.domain.gen.GenTaskManagerExample;
import org.apache.ibatis.annotations.Param;

public interface GenTaskManagerMapper {
    int countByExample(GenTaskManagerExample example);

    int insert(GenTaskManager record);

    int insertSelective(GenTaskManager record);

    List<GenTaskManager> selectByExample(GenTaskManagerExample example);

    GenTaskManager selectByPrimaryKey(Long mid);

    int updateByExampleSelective(@Param("record") GenTaskManager record, @Param("example") GenTaskManagerExample example);

    int updateByExample(@Param("record") GenTaskManager record, @Param("example") GenTaskManagerExample example);

    int updateByPrimaryKeySelective(GenTaskManager record);

    int updateByPrimaryKey(GenTaskManager record);
}