package com.atjl.cas.util;

import com.atjl.cas.api.UserContext;
import com.atjl.cas.domain.Params;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.atjl.cas.domain.VerifyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class CasJwt {
	
	private CasJwt() {
		throw new IllegalAccessError("Utility class");
	}
	
    private static final Logger logger = LoggerFactory.getLogger(CasJwt.class);

    public static CasSysConfig config=new CasSysConfig();

    public static String create(Params pam) {
        try {
            int msecond = Integer.valueOf(CasHelper.getProperty("CookieMaxage")) * 1000 ;
            return JWT.create().withIssuer(pam.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + msecond))
                    .sign(Algorithm.HMAC256("secret"));
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
        	logger.error("CasJwt create error", e);
        }
        return null;
    }


    public static VerifyStatus Verify(HttpServletRequest req,String token) {

		if ("true".equals(CasHelper.getProperty("isDebugger"))) { //调试模式
			return doIfDebugger(req);
		}
        VerifyStatus status = new VerifyStatus();
        JWTVerifier verifier;
        if (token == null) {
            status.setMsg("token is null!");
            return status;
        }

        try {
            verifier = JWT.require(Algorithm.HMAC256("secret")).build();
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            status.setMsg("build JWTVerifier fail.");
            logger.error("require IllegalArgumentException or UnsupportedEncodingException", e);
            return status;
        }

        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            status.setMsg(e.getMessage());
        	logger.warn("vertify JWTVerificationException ", e);
            return status;
        }
        String iss = jwt.getIssuer();
        if (jwt.getIssuer() != null) {

        	UserContext.setCurrentUserName(req,iss);
            status.setIss(iss);
            status.setStatus(true);
        }
        return status;
    }


	private static VerifyStatus doIfDebugger(HttpServletRequest req) {
		VerifyStatus status = new VerifyStatus();
        String defaultUser = config.getKey("debuggerUser");
        
//        Long userId = BeanFactory.getUniqueBean(BeanFactory.BEAN_CAS_USER_SERVICE)
//        		.getUserId(defaultUser);
//        if (userId == null) {
//            throw new CasException("系统不存在调试默认用户,请检查数据库的用户表" + defaultUser);
//        }
        UserContext.setCurrentUserName(req,defaultUser);
        status.setStatus(true);
        status.setMsg("Debugger model is started......");
        return status;
	}
}
