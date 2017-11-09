package com.atjl.jfeild.domain;


import java.util.List;

public class JTabMeta {
    private List<JFieldMeta> fieldList;
    private JFieldMeta basic;

    public List<JFieldMeta> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<JFieldMeta> fieldList) {
        this.fieldList = fieldList;
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
