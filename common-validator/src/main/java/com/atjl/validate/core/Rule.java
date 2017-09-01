package com.atjl.validate.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验器
 * Created by liujianlong on 16/4/1.
 */
public class Rule {
    private static Logger logger = LoggerFactory.getLogger(Rule.class);
    private int type;
    private List<Pattern> patterns;
    private boolean validate;

    public Rule(){

    }

    public Rule(int type, List<Pattern> patterns, boolean validate) {
        this.type = type;
        this.patterns = patterns;
        this.validate = validate;
    }

    public boolean regFilter(String value) {
        for (Pattern p : this.patterns) {
            logger.debug("-------CHKV:" + value + "RULE:" + p.toString());
            Matcher m = p.matcher(value);
            if (!m.find()) {
                return false;//"paramName [ " + value + " ] format error,rule:" + ;
            }
        }
        return true;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public void setType(int type) {
        this.type = type;
    }



    public int getType() {
        return type;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "type=" + type +
                ", patterns=" + patterns +
                ", validate=" + validate +
                '}';
    }
}
