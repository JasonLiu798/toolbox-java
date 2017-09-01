package com.atjl.validate.form;


import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;

import java.util.Map;

public class BaseForm implements ValidateForm {

    public Map<String, String> valueMap;

    public Map<String, String> errors;

    private Map<String, ValidateField> fieldMap;


    @Override
    public void validate() {
        if (!CollectionUtil.isEmpty(fieldMap)) {
            return;
        }
        for (Map.Entry<String, ValidateField> entry : fieldMap.entrySet()) {
            entry.getValue().getValue();
        }
    }
}
