package com.atjl.validate.validator.base;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.api.exception.ValidateInitException;
import com.atjl.validate.api.field.ValidateField;

import java.util.Set;

/**
 * 流程控制校验器
 * 指定字段为指定的某个值时，继续后续校验，否则终止
 */
public abstract class FieldIfEqualExistBase extends ValidatorBase {
	
	protected String refField;
	protected Set<String> refVals;

//    public FieldIfEqualExistBase(String refField, Set<String> refVals, String msg) {
//        init(refField, refVals, msg, false);
//    }
	
	protected void init(String refField, Set<String> refVal, String msg, boolean fmt) {
		this.refField = refField;
		this.refVals = refVal;
		chk();
		if (fmt) {
			this.msg = String.format(msg, refField, refVal);
		} else {
			this.msg = msg;
		}
	}
	
	private void chk() {
		if (StringCheckUtil.isEmpty(refField) || CollectionUtil.isEmpty(refVals)) {
			throw new ValidateInitException("参考域字段名或字段值为空");
		}
		if(StringCheckUtil.isExistEmpty(CollectionUtil.set2array(refVals))){
			throw new ValidateInitException("参考域字段值集合存在空");
		}
	}
	
	protected boolean needChk(ValidateForm form) {
		ValidateField refF = form.getField(refField);
		/**
		 * todo：初始化表单时，校验
		 */
		if (refF == null) {
			throw new ValidateInitException("参考域不存在");
		}
		for(String v:this.refVals){
			if (StringCheckUtil.equal(refF.getStrValue(), v)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract void validate(ValidateForm form, ValidateField field);
		
	
	public abstract Validator parse(String str);
}
