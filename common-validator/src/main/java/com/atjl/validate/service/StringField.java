package com.atjl.validate.service;


import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.Validator;

import java.util.List;

public class StringField implements ValidateField {

    public StringField(String label) {
        this.label = label;
    }


    public StringField(String label, Validator... validators) {
        this.label = label;

    }


    private String label;
    private String value;

    private List<Validator> validators;

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
