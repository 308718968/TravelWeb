package com.glc.service;

import com.glc.bean.PageBean;
import com.glc.bean.Route;
import com.glc.dao.RouteDao;
import com.glc.util.MySessionUtils;

import java.util.List;

public class RouteService2 {
    public PageBean searchById(int cid,int currentPage,int pageSize) {
        RouteDao routeDao = MySessionUtils.getMapper(RouteDao.class);
        int totalCount  = routeDao.findCountById(cid);
        int start = (currentPage-1)*pageSize;
        List<Route> routeList = routeDao.findRouteById(cid,start,pageSize);
        PageBean pageBean = new PageBean();
        pageBean.setList(routeList);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }
}
