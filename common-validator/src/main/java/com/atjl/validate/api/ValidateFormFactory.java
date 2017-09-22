package com.atjl.validate.api;


import com.atjl.validate.form.BaseForm;

public class ValidateFormFactory {

    public static ValidateForm build(Class clz) {
        ValidateForm bf = BaseForm.newForm(clz);
        return bf;
    }

    public static ValidateForm buildAndSet(Class clz, Object chkObj) {
        ValidateForm bf = BaseForm.newForm(clz);
        bf.setValue(chkObj);
        return bf;
    }
}
