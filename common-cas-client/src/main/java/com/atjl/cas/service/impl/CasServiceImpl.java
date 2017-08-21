package com.atjl.cas.service.impl;


import com.atjl.cas.api.BeanFactory;
import com.atjl.cas.api.CasUserService;
import com.atjl.cas.service.CasService;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CasServiceImpl implements CasService {

    private static final Logger logger = LoggerFactory.getLogger(CasService.class);

    private CasUserService casUserService;

    public CasServiceImpl(){
        // 初始化userDao
        this.casUserService = BeanFactory.getBean(BeanFactory.BEAN_CAS_USER_SERVICE, true);
    }

    @Override
    public boolean checkUser(HttpServletRequest request, HttpServletResponse response, Assertion assertion) {
        String userName = assertion.getPrincipal().getName();
        try {
            return casUserService.exist(userName);
        } catch (Exception e) {
            logger.error("can't find user [" + userName + "] in system.", e);
            return false;
        }
    }
}
