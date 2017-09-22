package com.atjl.validate.validator.base;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateInitException;
import com.atjl.validate.api.exception.ValidateException;
import com.atjl.validate.util.ValidateMsgFmtUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 枚举校验器
 *
 * @author jasonliu
 */
public abstract class EnumBase extends ValidatorBase {

    protected Set<String> refs = new HashSet<>();

//    public EnumBase(Set<String> refs, String msg) {
//        init(refs, msg, false);
//    }

    protected void init(Set<String> refs, String msg, boolean fmtMsg) {
        this.refs = refs;
        chk();
        if (fmtMsg) {
            this.msg = ValidateMsgFmtUtil.fmtAnyOfMsg(msg, this.refs);
        } else {
            this.msg = msg;
        }
    }

    private void chk() {
        if (CollectionUtil.isEmpty(refs)) {
            throw new ValidateInitException("枚举校验器，参考值为空");
        }
        for (String s : refs) {
            if (StringCheckUtil.isEmpty(s)) {
                throw new ValidateInitException("枚举校验器，参考值存在空值");
            }
        }
    }

    public void validate(ValidateForm form, ValidateField field) {
        String raw = field.getRawValue();
        if (!refs.contains(raw)) {
            throw new ValidateException(this.msg);
        }
    }
}
