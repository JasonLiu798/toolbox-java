package com.atjl.dbservice.domain;

import java.util.List;

/**
 * Created by async on 2017/12/3.
 */
public class TgtTableData {
	
	//update需要
	private KeyValue pk;
	
//	private List<String> fields;
	
	private List<KeyValue> items;
	
//	public List<String> getFields() {
//		return fields;
//	}
	
//	public void setFields(List<String> fields) {
//		this.fields = fields;
//	}
	
	public boolean isUpdate(){
		return pk!=null;
	}
	
	public KeyValue getPk() {
		return pk;
	}
	
	public void setPk(KeyValue pk) {
		this.pk = pk;
	}
	
	public List<KeyValue> getItems() {
		return items;
	}
	
	public void setItems(List<KeyValue> items) {
		this.items = items;
	}
}
