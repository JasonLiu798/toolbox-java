package com.atjl.jfeild.domain;


import com.atjl.util.collection.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JTabMeta {
//    private List<JFieldMeta> fieldList;
	private Map<String,JFieldMeta> field2meta;
	private Map<String,JFieldMeta> col2meta;
    private JFieldMeta basic;
	
	/**
	 * 是否 obj中的 扩展字段
	 * @param field
	 * @return
	 */
	public boolean isExtObj(String field){
    	return field2meta.containsKey(field);
	}

    public List<JFieldMeta> getFieldList() {
		return CollectionUtil.map2list(field2meta,false);
    }
    
    public JFieldMeta getField(String field){
		return field2meta.get(field);
	}
	
	public JFieldMeta getCol(String colname){
    	return col2meta.get(colname);
	}
    
    public void setFieldList(List<JFieldMeta> fieldList) {
    	if(CollectionUtil.isEmpty(fieldList)){
    		return;
		}
		if(field2meta==null){
			field2meta = new HashMap<>();
		}
		if(col2meta==null){
			col2meta = new HashMap<>();
		}
		for(JFieldMeta f:fieldList){
			field2meta.put(f.getFieldName(),f);
			col2meta.put(f.getColumnName(),f);
		}
    }
    
    public boolean gotBasis(){
    	return this.basic ==null;
	}

    public JFieldMeta getBasic() {
        return basic;
    }

    public void setBasis(JFieldMeta basis) {
        this.basic = basis;
    }
}
