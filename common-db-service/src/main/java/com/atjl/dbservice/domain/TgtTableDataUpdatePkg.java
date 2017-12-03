package com.atjl.dbservice.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by async on 2017/12/3.
 */
public class TgtTableDataUpdatePkg {
	
	/**
	 key:propertyName
	 value:
	 	List
	 		key:pkValue
	 		value:property's value
	 */
	Map<String,List<KeyValue>> items;

	
	public static TgtTableDataUpdatePkg buildFrom(TgtTableData data){
		
		return null;
	}
	
}
