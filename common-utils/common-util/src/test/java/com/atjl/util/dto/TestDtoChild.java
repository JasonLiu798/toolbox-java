package com.atjl.util.dto;


public class TestDtoChild extends com.atjl.util.test.TestDto {
    private Long childField;

    public TestDtoChild(){}
	
	public Long getChildField() {
		return childField;
	}
	
	public void setChildField(Long childField) {
		this.childField = childField;
	}
}
