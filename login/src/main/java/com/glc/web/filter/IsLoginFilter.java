package com.glc.web.filter;

import com.sun.jndi.toolkit.url.Uri;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(uri.contains("/login.jsp")||uri.contains("/images/")||uri.contains("/img/")||uri.contains("/LoginServlet")||uri.contains("/css/")||uri.contains("/error/")||uri.contains("/fonts/")||uri.contains("/js/")||uri.contains("/CheckCodeServlet")||uri.contains("CancelAutoLoginServlet")){
            chain.doFilter(req, resp);
        }else{
            Object user = request.getSession().getAttribute("user");

            if(user!=null){
                chain.doFilter(req,resp);
            }else{
                request.setAttribute("loginmsg","您尚未登录，请先登录");
                request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
