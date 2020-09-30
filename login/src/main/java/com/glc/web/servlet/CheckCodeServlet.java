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
        CheckCodeService checkCodeService = new CheckCodeService();
        String checkcode = checkCodeService.createCheckCode();
        HttpSession session = request.getSession();
        session.setAttribute("code",checkcode);
        System.out.println(checkcode);
        BufferedImage checkcodeimage= checkCodeService.checkcodeSreing2Imag(checkcode);
        ImageIO.write(checkcodeimage,"jpeg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
