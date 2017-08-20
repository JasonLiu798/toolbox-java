package com.atjl.cas.filter;

import com.atjl.cas.service.CasService;
import com.atjl.cas.util.AuthenticationUtil;
import com.atjl.cas.util.CasUrlGenUtil;
import com.atjl.cas.util.Assert;
import com.atjl.cas.util.CasHelper;
import org.jasig.cas.client.util.CommonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CasAuthenticationFilter implements Filter {

    private CasService casService;

    //cas 服务器
    private String casServerUrlPrefix;
    /**
     * 登出uri
     */
    private String logoutUri;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        this.casServerLoginUrl = CasHelper.getPropertyFromInitParams(
//                "casServerLoginUrl", null);
//        this.casServerLogoutUrl = CasHelper.getPropertyFromInitParams(
//                "casServerLogoutUrl", null);

        this.casServerUrlPrefix = CasHelper.getPropertyFromInitParams(
                "casServerUrlPrefix", null);
        this.logoutUri = CasHelper.getPropertyFromInitParams("logoutUri", null);

//        this.loginUri = CasHelper.getPropertyFromInitParams("loginUri", null);
//        this.casSrv = CasHelper.getPropertyFromInitParams("casSrv", null);
//        Assert.notNull(this.casServerLoginUrl, "casServerLoginUrl can not be null");

//        Assert.notNull(this.loginUri, "loginUri can not be null");

        Assert.notNull(this.logoutUri, "logoutUri can not be null");
//        Assert.notNull(this.casSrv, "casSrv can not be null");
        Assert.notNull(this.casServerUrlPrefix, "casServerUrlPrefix can not be null");

        this.casService = CasHelper.getPostCallBack();
    }

    @Override
    public void destroy() {
        // Do nothing because of nothing.
    }

    /**
     * 在重定向到CAS Server之前调用。在此可以进行SSO验证、安全url过滤
     *
     * @param request
     * @return
     *
    private boolean beforeRedirect(HttpServletRequest request) {
    //		boolean isLoginUri = request.getRequestURI().equals(this.loginUri);
    //		boolean isLogoutUri = request.getRequestURI().equals(this.logoutUri);
    boolean isSafeUri = AuthenticationUtil.isInWhiteListUrl(
    request.getRequestURI(), AuthenticationUtil.getWhiteListUrls());

    return isSafeUri;
    //		return isLoginUri || isLogoutUri || isSafeUri;
    }*/

    /**
     * 单点登陆认证，排除白名单和带ticket的请求
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @param filterChain     filterchain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        //登出
        if (request.getRequestURI().equals(this.logoutUri)) {
            CasHelper.deleteToken(request, response);
            //
            //this.casServerUrlPrefix
            ///logout?service=
            String rawUrl = CasUrlGenUtil.getUrl(request);
            String redirect = this.casServerUrlPrefix + "/logout?service=" + rawUrl;
            response.sendRedirect(redirect);
            return;
        }

        //如果没有过期，则通过检查
        if (AuthenticationUtil.cookieChk(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }

        //如果带有ticket参数，则去下一个过滤器通过ticket获取token
        final String ticket = request.getParameter("ticket");
        if (CommonUtils.isNotBlank(ticket)) {
            filterChain.doFilter(request, response);
            return;
        }

//		if (callBack.onAuthenticFail(request, response, filterChain))
//			return;

        //no cookie ,no ticket 重定向到 cas登录
        /*
        StringBuffer url = request.getRequestURL();
        String redirect = url.toString();
//        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
//        uri = uri + "?" + request.getQueryString();
        if (queryString != null && !"".equals(queryString)) {
            redirect = redirect + "?" + queryString;
        }*/
//        url.append("?").append(request.getQueryString());

        String rawUrl = CasUrlGenUtil.getRawUrl(request);
        String redirect = this.casServerUrlPrefix + "/login?service=" + rawUrl;
        response.sendRedirect(redirect);
    }

//        if (CasHelper.isMobileDevice(request)) {
//            //手机端返回302
//            doFailMobile(response);
//        } else {
//            //pc端返回200
//            doFailPc(request, response);


//    private void doFail(HttpServletRequest request, HttpServletResponse response) {
////        response.sendRedirect();
//    }


//		if (callBack.beforeAuthentic(request, response, filterChain))
//			return;
//			if (callBack.onAuthenticSuccess(request, response, filterChain))
//				return;

    /**
     * PC端验证不通过
     *
     * @param request
     * @param response void
     *
    private void doFailPc(final HttpServletRequest request, final HttpServletResponse response) {
    MapResponse ms = new MapResponse();
    Map<String, String> result = new HashMap<>();
    result.put("login", this.casServerLoginUrl);
    result.put("logout", this.casServerLogoutUrl);
    ms.setResult(result);
    if (CasHelper.tokenExist(request)) {
    ms.setMsg("login fail");
    ms.setSucc("logout");
    CasHelper.responseOutWithJson(response, ms);
    } else {
    ms.setMsg("to login");
    ms.setSucc("login");
    CasHelper.responseOutWithJson(response, ms);
    }
    }*/

    /**
     * 手机端验证不通过
     *
     * @param response
     * @throws IOException void
     */
    private void doFailMobile(final HttpServletResponse response)
            throws IOException {
        response.setStatus(302);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("<html><body><input name=\"lt\"></body></html>");
    }

}
