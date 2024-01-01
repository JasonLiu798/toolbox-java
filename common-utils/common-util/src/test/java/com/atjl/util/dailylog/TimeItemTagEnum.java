package com.atjl.util.dailylog;

import org.apache.commons.lang3.StringUtils;

public enum TimeItemTagEnum {

    WASTE("R","Waste"),

    EFFICIENT("Y","Efficient"),

    GAME("B","entertainment"),

    REST("G","rest"),

    FORCE("F","force work"),

    ;

    private TimeItemTagEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public TimeItemTagEnum parseByCode(String code){
        if(StringUtils.isBlank(code)){
            return  null;
        }
        for(TimeItemTagEnum e: TimeItemTagEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
}
