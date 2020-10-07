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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

@WebServlet("/RouteServlet/*")
public class RouteServlet extends BaseServlet {
    public void cid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        RouteService routeService = new RouteService();
        PageBean pageBean = routeService.searchById(cid, currentPage, pageSize);
        String json = super.toJson(pageBean);
        response.getWriter().print(json);
    }
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  search = request.getParameter("search");
        System.out.println(search);
        if(search==null||search.length()==0){
            return;
        }
        String currentPagestr = request.getParameter("currentPage");
        String pageSizestr = request.getParameter("pageSize");
        int currentPage=1;
        int pageSize =10;
        try {
            currentPage= Integer.parseInt(currentPagestr) ;
            pageSize = Integer.parseInt(pageSizestr)     ;
        }    catch (Exception e){
            e.printStackTrace();
        }
        RouteService routeService = new RouteService();
        PageBean routeList = routeService.search("%"+search+"%",currentPage,pageSize);
        String json = super.toJson(routeList);
        response.getWriter().print(json);
    }
}
