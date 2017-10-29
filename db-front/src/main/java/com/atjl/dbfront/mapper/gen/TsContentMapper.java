package com.atjl.dbfront.mapper.gen;

import com.atjl.dbfront.domain.gen.TsContent;
import com.atjl.dbfront.domain.gen.TsContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsContentMapper {
    int countByExample(TsContentExample example);

    int insert(TsContent record);

    int insertSelective(TsContent record);

    List<TsContent> selectByExampleWithBLOBs(TsContentExample example);

    List<TsContent> selectByExample(TsContentExample example);

    int updateByExampleSelective(@Param("record") TsContent record, @Param("example") TsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TsContent record, @Param("example") TsContentExample example);

    int updateByExample(@Param("record") TsContent record, @Param("example") TsContentExample example);
}