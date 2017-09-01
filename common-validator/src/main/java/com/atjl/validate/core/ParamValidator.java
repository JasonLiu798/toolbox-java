package com.atjl.validate.core;


/**
 * 参数-校验规则
 */
public class ParamValidator {
    // 参数名称
    private String name;
    // 验证规则
    private Rule rule;
    // 是否可以为空(Y 是 N 否)
    private boolean none;

    /**
     * 校验
     *
     * @return
     *
    public String check(Map<String, Object> paramMap) {
        Object vo = paramMap.get(this.name);
        if(RegrexUtil.isEmpty(vo)){//值为空
            if(none){//可以为空
                return null;//正常
            }else{//不能为空
                return "paramName [ " + this.name + " ] not null or empty!";
            }
        }else{//值不为空
            if (rule!=null && rule.isValidate()) {//规则存在，且需要校验
                if (!rule.regFilter(String.valueOf(vo))){
                    return "paramName [ " + this.name + " ] format error,rule:" + rule;
                }
            }//无规则/不需要校验
        }
        //else 无规则不校验
        return null;
    }*/

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    public boolean isNone() {
        return none;
    }


    @Override
    public String toString() {
        return "ParamValidator{" +
                "name='" + name + '\'' +
                ", rules=" + rule +
                ", canBeNull=" + none +
                '}';
    }
}
