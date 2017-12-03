package com.atjl.dbservice.domain;

import java.util.List;

/**
 * Created by async on 2017/12/3.
 */
public class TgtTableDataPkg {
	
	List<String> fields;
	
	List<TgtTableData> datas;
	
	public List<String> getFields() {
		return fields;
	}
	
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	public List<TgtTableData> getDatas() {
		return datas;
	}
	
	public void setDatas(List<TgtTableData> datas) {
		this.datas = datas;
	}
}
