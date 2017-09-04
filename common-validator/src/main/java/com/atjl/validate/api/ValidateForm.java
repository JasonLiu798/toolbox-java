package com.atjl.validate.api;


import java.util.Map;

public interface ValidateForm {

    boolean validate();

    /**
     * validate = false时，有值
     * @return
     */
    Map<String, String> getErrors();

}
