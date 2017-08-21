package com.atjl.cas.filter;

import com.atjl.cas.domain.CasConstant;
import com.atjl.cas.service.CasService;
import com.atjl.cas.util.AuthenticationUtil;
import com.atjl.cas.util.CasHelper;
import com.atjl.cas.util.CasUrlGenUtil;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CasTicketValidationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CasTicketValidationFilter.class);

    /**
     * ticket验证器
     */
    private TicketValidator ticketValidator;

    private CasService casService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String casServerUrlPrefix = CasHelper.getPropertyFromInitParams("casServerUrlPrefix", null);
        this.ticketValidator = new Cas20ServiceTicketValidator(casServerUrlPrefix);
        this.casService = CasHelper.getPostCallBack();
    }

    @Override
    public void destroy() {
        // Do nothing because of nothing.
    }


    /**
     * 票据验证
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /**
         * 进入此filter前提
         * 1.cookie校验通过
         * 		直接放行
         * 2.有ticket，还未校验
         * 		成功：redirect 设置cookie
         * 		失败：
         */
        //有cookie或 白名单，直接放行
        if (AuthenticationUtil.cookieChk(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }

//        boolean isMobileDevice = CasHelper.isMobileDevice(request);

        //有ticket，还未校验
        String ticket = request.getParameter(CasConstant.TICKET_NAME);

        //ticket null
        if (StringUtils.isBlank(ticket)) {
            sendRedirectErr(request, response, "500");
            return;
        }

        //有效性校验 ，有效则直接设置cookie
        boolean valid = false;
        try {
            String noticketUrl = CasUrlGenUtil.getRawUrlNoTicket(request);
            Assertion assertion = ticketValidator.validate(ticket, noticketUrl);
            //验证结果，成功设置 cookie
            boolean chkUserRes = casService.checkUser(request, response, assertion);
            if (chkUserRes) {
                String userName = assertion.getPrincipal().getName();
                //添加 cookie ,session添加name
                CasHelper.addToken(request, response, userName);
                logger.info("{} login succ ! ", userName);
                valid = true;
            }
        } catch (Exception e) {
            logger.warn("login error", e);
            valid = false;
        }
        if (!valid) {
            sendRedirectErr(request, response, String.valueOf(500));
        }

        //有效
        filterChain.doFilter(request, response);
        return;
    }

    /**
     * 登陆错误页
     *
     * @param request
     * @param response
     * @param errorCode
     * @throws IOException
     */
    private void sendRedirectErr(HttpServletRequest request, HttpServletResponse response,
                                 String errorCode) throws IOException {
        CasHelper.deleteToken(request, response);
        String domainUrl = CasUrlGenUtil.getRawUrl(request);
        String toUrl = domainUrl + "/error/" + errorCode + ".html";
        response.sendRedirect(toUrl);
    }

}

//		int chkRes = validate(request,response,ticket);

        //无效
//		if(chkRes!=302){
//			sendRedirectErr(request, response, String.valueOf(chkRes));
//		}


//		String redirectUrl = CasUrlGenUtil.getRawUrlNoTicket(request);
//		response.sendRedirect(redirectUrl);

        //filterChain.doFilter(request, response);

//		int code;
//		if (isMobileDevice) {
//			code = handleMobile(request, response, ticket);
//		} else {
//			code = handle(request, response, ticket);
//		}
//		handleCode(request, response, filterChain, code);

//	private int handleCommon(HttpServletRequest request, HttpServletResponse response, String ticket) {
////		if(StringUtils.isNotBlank(ticket)) {
//		return validate(request, response, ticket);
////		} else if (!isLoginUrl(request)) {
////			return 200;
////		} else {
//			//当直接访问后台登陆地址 会在此处跳转
////			return 302;
////		}
//	}

    /*
    private void handleCode(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, int code)
            throws IOException, ServletException {

        switch (code) {

            case 302:
                if (casService.onTicketValidateFail(request, response, filterChain))
                    break;
                do302(request, response);
                break;

            case 403:
                if (casService.onTicketValidateError(request, response, filterChain))
                    break;
                sendRedirectErr(request, response, "403");
                break;

            case 500:
                if (casService.onTicketValidateError(request, response, filterChain))
                    break;
                sendRedirectErr(request, response, "500");
                break;

            case 200:
            default:
                if (casService.onTicketValidateSuccess(request, response, filterChain))
                    break;
                filterChain.doFilter(request, response);
                break;
        }
    }

    private void do302(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(302);
        if (CasHelper.isMobileDevice(request)) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("<html><body><input name=\"lt\"></body></html>");
        } else {
            response.sendRedirect(indexUrl);
        }
    }
*/
    /**
     * 处理手机端
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     *
    private int handleMobile(HttpServletRequest request, HttpServletResponse response, String ticket) {
        if (StringUtils.isNotBlank(ticket)) {
            return validateMobile(request, response, ticket);
        } else {
            return 200;
        }
    }*/

    /**
     * 处理pc端
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     *
    private int handle(HttpServletRequest request, HttpServletResponse response, String ticket) {
        if (StringUtils.isNotBlank(ticket)) {
            return validate(request, response, ticket);
        } else if (!isLoginUrl(request)) {
            return 200;
        } else {
            //当直接访问后台登陆地址 会在此处跳转
            return 302;
        }
    }*/

    /**
     * 手机端ticket验证逻辑
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     *
    private int validateMobile(HttpServletRequest request, HttpServletResponse response, String ticket) {
        try {
            Assertion assertion = casService.onTicketValidate(request, response,
                    ticketValidator, ticket, serverName, loginUri);
            if (!validateAssertion(request, response, assertion)) {
                return 302;
            }
        } catch (Exception e) {
            logger.warn("login error", e);
            return 302;
        }
        return 200;
    }*/

    /**
     * pc端ticket验证逻辑
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     *
    private int validate(HttpServletRequest request, HttpServletResponse response, String ticket) {

        try {
            String rawUrl = CasUrlGenUtil.getRawUrl(request);
            int ticketIdx = rawUrl.indexOf("ticket");
            //移除ticket 做校验
            rawUrl = rawUrl.substring(0, ticketIdx - 1);

            String noticketUrl = CasUrlGenUtil.getRawUrlNoTicket(request);
            Assertion assertion = ticketValidator.validate(ticket, noticketUrl);
//			Assertion assertion = casService.onTicketValidate(request, response, ticketValidator, ticket, serverName, loginUri);
//			Assertion assertion = casService.onTicketValidate(request, response, ticketValidator, ticket, serverName, loginUri);

            //验证结果，成功设置 cookie
            if (!validateAssertion(request, response, assertion)) {
                return 403;
            }
        } catch (Exception e) {
            logger.warn("login error", e);
            return 500;
        }
        return 302;
    }*/


    /**
     * 当CAS校验成功时，将用户ID设置到FSOPEN的上下文
     *
    private boolean validateAssertion(HttpServletRequest req,
                                      HttpServletResponse resp, Assertion assertion) {
        try {
            boolean success = casService.checkUser(req, resp, assertion);
            if (success) {
                String userName = assertion.getPrincipal().getName();
                //添加 cookie
                CasHelper.addToken(req, resp, userName);
                logger.info("{} login succ ! ", userName);
            }
            return success;
        } catch (Exception e) {
            logger.error("validate callback error.", e);
            return false;
        }
    }*/

    /**
     * 是否是登陆页面
     *
     * @param request
     * @return
     *
    private boolean isLoginUrl(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri.equals(this.loginUri);
    }*/



