package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.RegisterService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        //新建注册业务类
        RegisterService registerService = new RegisterService();
        ResultInfo resultInfo = registerService.register(user);
        System.out.println(resultInfo);
        if(resultInfo.getFlag()==true){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
