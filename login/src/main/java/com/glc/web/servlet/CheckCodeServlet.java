package com.glc.web.servlet;

import com.glc.service.CheckCodeService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

    @WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建验证码服务对象
        CheckCodeService checkCodeService = new CheckCodeService();
        //生成字符串验证码
        String checkcode = checkCodeService.createCheckCode();
        //将字符串验证码放到session域中
        HttpSession session = request.getSession();
        session.setAttribute("code",checkcode);
        System.out.println(checkcode);
        //验证码转图像
        BufferedImage checkcodeimage= checkCodeService.checkcodeSreing2Imag(checkcode);
        //输出到页面
        ImageIO.write(checkcodeimage,"jpeg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
