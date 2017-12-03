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
    	//取数
		
		//查询 目标表 已经存在的
		
		//分离 已经存在 和 不存在 数据
		
		//已经存在的 更新
		
		
		//不存在的  插入
		
		//



    }

//    public void
}
