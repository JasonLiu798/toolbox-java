package com.atjl.dbservice.domain;


import java.util.List;
import java.util.Map;

public class SeparatedDatas {

	//不存在数据列表
    private List<Map> notExistDatas;
	//已经存在数据列表
    private List<Map> existDatas;
	
	public List<Map> getNotExistDatas() {
		return notExistDatas;
	}
	
	public void setNotExistDatas(List<Map> notExistDatas) {
		this.notExistDatas = notExistDatas;
	}
	
	public List<Map> getExistDatas() {
		return existDatas;
	}
	
	public void setExistDatas(List<Map> existDatas) {
		this.existDatas = existDatas;
	}
}
