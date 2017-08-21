package com.atjl.cas.service;


import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CasService {

    /**
     * ticket验证通过后验证用户
     *
     * @param request
     * @param response
     * @param assertion
     * @return
     */
    boolean checkUser(HttpServletRequest request, HttpServletResponse response, Assertion assertion);

}
