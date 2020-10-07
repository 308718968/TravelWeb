package com.glc.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        int i = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(i+1);
        System.out.println(methodName);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            if(method!=null){
                method.invoke(this,request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public String toJson(Object data) {
        ResultInfo info = new ResultInfo();
        if(data!=null){
            info.setFlag(true);
            info.setData(data);
        }else{
            info.setFlag(false);
        }
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
