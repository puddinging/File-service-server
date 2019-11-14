package com.fire.Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        权限校验
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (rsaFilter(servletRequest,servletResponse)){
//        放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
//            servletResponse.setCharacterEncoding("utf-8");
//            servletResponse.setContentType("application/json; charset=utf-8");
//            HttpServletResponse resp = (HttpServletResponse) servletRequest;
//            writeResponse(resp,403,"a");
            resp.setStatus(403);
        }

    }

    public static void writeResponse(HttpServletResponse response, int status, String json) {
        try {
            response.reset();//很重要
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 权限校验
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    private boolean rsaFilter(ServletRequest servletRequest, ServletResponse servletResponse) {
        String xSid = servletRequest.getParameter("X-SID");
        String xSignature = servletRequest.getParameter("X-Signature");
//        时间紧张没有校验参数为空，
//        公钥校验
        return checkBypub(xSid,xSignature);
    }

//    签名校验
    private boolean checkBypub(String xSid, String xSignature) {
        return false;
    }

    @Override
    public void destroy() {

    }
}
