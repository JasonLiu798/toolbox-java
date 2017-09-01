package com.atjl.validate.core;

import java.util.*;

import com.atjl.validate.domain.constants.ValidateConstants;
import com.atjl.validate.dto.ValidateResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校验
 */
public class ValidateParamAdvice {

    private static Logger logger = LoggerFactory.getLogger(ValidateParamAdvice.class);
//    private static JSONHelper jsonHelper = JSONHelper.buildNormalIgnoreBinder();

	/*
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String requestURI = jp.getTarget().getClass().getName();
        Object res = null;
        Object[] args = jp.getArgs();
        logger.info("validator {}" , requestURI);
        Map<String, ActionValidate> vm = LoadValidate.getValidateMap();
        if(vm!=null){
            ActionValidate actionValidate = vm.get(requestURI);
            if (actionValidate != null && args != null && args.length == 1) {//校验规则存在，参数存在，且为1，则获取参数进行校验
                long t1 = System.currentTimeMillis();
                res = this.validate(requestURI, BeanHelper.getFieldValueMap( args[0]));
                logger.info("validator {} runtime is : {} ms,RES: {}", requestURI, (System.currentTimeMillis() - t1), res);
            }
        }
        if (res == null) {//校验通过
            res = jp.proceed();
        }
        logger.info("{} executeTime: {} ms", requestURI, (System.currentTimeMillis() - startTime));
        return res;
    }*/

    /**
     * 验证方法
     *
     * @param requestURI
     * @param paramMap
     * @return
     *
     *
    private ValidateResultDto validate(String requestURI, Map paramMap) {
        ActionValidate actionValidate = LoadValidate.getValidateMap().get(requestURI);
        ValidateResultDto res = null;
        // 没有找到验证规则
        if (actionValidate == null) {
            return new ValidateResultDto(ValidateConstants.system_returncode_not_validate_error, "rule not find");
        }
        // 获取当前接口的验证规则
        HashMap<String, ParamValidator> ruleMap = actionValidate.getParam2rules();
        try {
            long t1 = System.currentTimeMillis();
            res = validateParam(paramMap, ruleMap);
            logger.debug("validator ParamCost {}", (System.currentTimeMillis() - t1));
        } catch (Exception e) {
            logger.error("validator praram validate error {}", e);
            res = new ValidateResultDto(ValidateConstants.system_returncode_validate_error, "rule error");
        }
        return res;
    }

    /**
     * 参数验证
     *
     * @param param
     * @param validatorMap
     * @return
     *
    private ValidateResultDto validateParam(Map<String, Object> param, Map<String, ParamValidator> validatorMap) throws Exception {
        logger.debug("validator ParamMap {} Validator {}",param , validatorMap);
        for (String key : param.keySet()) {
            ParamValidator v = validatorMap.get(key);
            if (v == null) {
                logger.debug("validator Param Validator null,key {}" , key);
                continue;
            }
            String res = v.check(param);
            logger.debug("validator check result:" + res);
            if (res != null) {
                return new ValidateResultDto(ValidateConstants.system_returncode_parmas_error, "error:" + res);
            }
        }
        return null;//默认为空
    }*/

}