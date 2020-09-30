package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AutoLoginServlet")
public class AutoLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if("JSESSIONID".equals(cookie.getName())){
                User user = (User) session.getAttribute("user");
                LoginService loginService = new LoginService();
                ResultInfo resultInfo = loginService.findUserByName(user);
                response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
