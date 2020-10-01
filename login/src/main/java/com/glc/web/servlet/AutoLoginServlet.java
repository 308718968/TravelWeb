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
        //获取session对象，如果cookie中存放着jsessionid，就代表着登入过且记住密码，没有的话会形成新的jsessionid
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        //获取浏览器携带的jsessionid
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if("JSESSIONID".equals(cookie.getName())){
                //如果该sessionid下的域中存放着user对象就进行登录操作
                User user = (User) session.getAttribute("user");
                LoginService loginService = new LoginService();
                ResultInfo resultInfo = loginService.login(user);
                if(resultInfo.getFlag()==true){
                    response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
                }

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
