package com.atjl.validate.service;


import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.Validator;

import java.util.List;

public class StringField implements ValidateField {

    private String label;
    private String rawValue;
    private List<Validator> validators;

    public StringField(String label) {
        this.label = label;
    }

    public StringField(String label, Validator... validators) {
        this.label = label;
    }

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

    @Override
    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }
}
