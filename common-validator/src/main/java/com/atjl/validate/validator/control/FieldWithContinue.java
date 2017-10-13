package com.atjl.validate.validator.control;


import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.base.FieldWithBase;

import java.util.Set;

/**
 * todo:
 */
@Deprecated
public class FieldWithContinue extends FieldWithBase {


    public FieldWithContinue(Set<String> refs, String msg) {
        super(refs, msg);
    }


    @Override
    public Validator parse(String str) {
        return null;
    }
}
