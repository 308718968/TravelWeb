package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.LoginService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String rememberme = request.getParameter("rememberme");
        System.out.println(session.getId());
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(rememberme!=null){
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(3600*24*7);
            Cookie cookie = new Cookie("JSESSIONID",session.getId());
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        LoginService loginService = new LoginService();
        ResultInfo resultInfo = loginService.findUserByName(user);
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
