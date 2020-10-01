package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.ResultInfo;
import com.glc.bean.User;
import com.glc.service.RegisterService;
import com.glc.util.MailUtils;
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
        System.out.println(request.getRequestURL());
        ResultInfo resultInfo;
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        session.removeAttribute("code");
        if(!code.equalsIgnoreCase(check)){
            resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }else {
            //获取表单数据，通过BeanUtils封装对象
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(user);
            //新建注册业务类
            RegisterService registerService = new RegisterService();
            //调用注册方法，返回结果
            resultInfo = registerService.register(user);
            //获取到注册后的用户数据
            User res_user = (User) resultInfo.getData();
            System.out.println(resultInfo);
            //如果结果对象中为true为注册成功，获取结果对象中的成功注册的用户数据
            if (resultInfo.getFlag() == true && "1".equals(res_user.getStatus())) {//这里的用户状态吗为空
                //这里是为了阻止自动登陆，如果不判断状态码，注册完会自动登陆
                session.setAttribute("user", user);

            }
        }
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
