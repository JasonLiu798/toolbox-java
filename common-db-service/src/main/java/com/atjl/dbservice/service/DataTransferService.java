package com.atjl.dbservice.service;

import com.atjl.dbservice.manager.RawTableDataGetManager;
import com.atjl.dbservice.manager.TgtTableDataManager;
import com.atjl.dbservice.mapper.biz.DataTransferMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataTransferService {

    /**
     * 取数
     * <p>
     * 判断
     * <p>
     * 插表 / 更新
     */


    @Resource
    private DataTransferMapper dataTransferMapper;

    @Resource
    private RawTableDataGetManager rawTableDataGetManager;
    @Resource
    private TgtTableDataManager tgtTableDataManager;

    public void process(){




    }

//    public void
}
