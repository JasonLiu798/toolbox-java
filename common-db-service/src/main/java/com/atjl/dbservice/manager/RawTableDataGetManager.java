package com.atjl.dbservice.manager;

import com.atjl.dbservice.domain.DbTableTransferConfig;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class RawTableDataGetManager {
    @Resource
    private DataTransferMapper dataTransferMapper;

    public List<Map> getData(DbTableTransferConfig config) {
        return dataTransferMapper.getRawTableData(config);
    }


    public List<Map> filterInvalid() {
        return null;
    }
}
