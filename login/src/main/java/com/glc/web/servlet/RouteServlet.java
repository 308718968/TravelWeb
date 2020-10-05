package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.PageBean;
import com.glc.bean.ResultInfo;
import com.glc.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        RouteService routeService = new RouteService();

        PageBean routeList = routeService.search("%"+search+"%",Integer.parseInt(currentPage),Integer.parseInt(pageSize));
        ResultInfo resultInfo = new ResultInfo();
        if(routeList!=null){
            resultInfo.setData(routeList);
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);

        }
        System.out.println(resultInfo);
        response.getWriter().print(new ObjectMapper().writeValueAsString(resultInfo));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
