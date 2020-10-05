package com.glc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glc.bean.Category;
import com.glc.bean.ResultInfo;
import com.glc.service.CategoryService;
import com.glc.util.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtil.getJedis();
        String categorylist = jedis.get("category");
        if(categorylist!=null){
            response.getWriter().print(categorylist);
        }else{
            CategoryService categoryService = new CategoryService();
            ResultInfo categoryList = categoryService.findAll();
            System.out.println(categoryList);
            String json = new ObjectMapper().writeValueAsString(categoryList);
            jedis.set("category",json);
            response.getWriter().print(json);
        }
        jedis.close();




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
