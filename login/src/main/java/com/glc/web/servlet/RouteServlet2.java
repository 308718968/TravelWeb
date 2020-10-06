package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.PageBean;
import com.glc.bean.ResultInfo;
import com.glc.service.RouteService2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RouteServlet2")
public class RouteServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidstr = request.getParameter("cid");
        String currentPagestr = request.getParameter("currentPage");
        String pageSizestr = request.getParameter("pageSize");
        System.out.println(cidstr+"===="+currentPagestr+"===="+pageSizestr);

        int cid=0;
        int currentPage=0;
        int pageSize=0;
        //避免传过来的参数不是正常的数字字符串
        try {
             cid = Integer.parseInt(cidstr);
            currentPage = Integer.parseInt(currentPagestr);
            pageSize = Integer.parseInt(pageSizestr);
        }catch (Exception e){
            e.printStackTrace();
        }
        RouteService2 routeService2 = new RouteService2();
        PageBean pageBean = routeService2.searchById(cid, currentPage, pageSize);
        ResultInfo resultInfo = new ResultInfo();
        if(pageBean!=null){
            resultInfo.setData(pageBean);
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
        }
        System.out.println(pageBean);
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
