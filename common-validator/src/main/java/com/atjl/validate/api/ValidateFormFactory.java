package com.atjl.validate.api;

import com.atjl.validate.context.FormCache;
import com.atjl.validate.form.BaseForm;

public class ValidateFormFactory {

    private static boolean useCache = true;
    private static FormCache cache = new FormCache();

    public static ValidateForm build(Class clz) {
        if (useCache) {
            ValidateForm f = cache.get(clz.getName());
            if (f != null) {
                return f;
            }
        }
        ValidateForm bf = BaseForm.newForm(clz);
        if (useCache) {
            cache.put(clz.getName(), bf);
        }
        return bf;
    }

    public static ValidateForm buildAndSet(Class clz, Object chkObj) {
        ValidateForm bf = build(clz);
        bf.setValue(chkObj);
        return bf;
    }
}
