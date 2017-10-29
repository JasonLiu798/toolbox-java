package com.atjl.dbfront.mapper.biz;

import com.atjl.dbfront.domain.biz.ContentDomain;
import org.apache.ibatis.annotations.Param;

public interface ContentMapper {
    ContentDomain getContent(@Param("ctype") String ctype, @Param("cname") String cname, @Param("cversion") String ver);


}
