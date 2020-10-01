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
        //获取session对象
        HttpSession session = request.getSession();
        //声明结果对象
        ResultInfo resultInfo;
        //是否选中记住密码
        String rememberme = request.getParameter("rememberme");
        //获得用户输入的验证码
        String check = request.getParameter("check");
        //获取session域中的正确的验证码码值
        String code = (String) session.getAttribute("code");
        //获取到就删除，一次性验证码
        session.removeAttribute("code");
        //如果验证码正确，就获取用户的账号密码
        if(code.equalsIgnoreCase(check)){
            System.out.println(session.getId());
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            //使用beanUtils将用户的账号密码封装成user对象
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(user);
            //创建登录服务对象
            LoginService loginService = new LoginService();
            //进行账号状态与密码验证，返回结果
            resultInfo = loginService.login(user);
            //如果账号密码正确，将用户信息放入session中
            if(resultInfo.getFlag()==true){
                session.setAttribute("user",resultInfo.getData());
                //如果点了记住密码，将cookie发送给浏览器，让其下一次携带着
                if(rememberme!=null){
                    //设置七天免登
                    session.setMaxInactiveInterval(3600*24*7);
                    //将jsessionid发送给浏览器，下次浏览器会携带此id给服务器
                    Cookie cookie = new Cookie("JSESSIONID",session.getId());
                    //设置cookie存活时间
                    cookie.setMaxAge(60*60*24*7);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }

        }else{
            //验证码错误，设置结果对象
            resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }
        //放回结果给浏览器
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
