package com.atjl.util.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class LunarDTO implements Serializable {

    private static final long serialVersionUID = 1624024247871341040L;

    private Integer year;

    private Integer month;

    private Integer day;


}
