package com.glc.service;

import com.glc.bean.PageBean;
import com.glc.bean.Route;
import com.glc.dao.RouteDao;
import com.glc.util.MySessionUtils;

import java.util.List;

public class RouteService {
    public PageBean search(String name,int currentPage,int pageSize) {
        RouteDao routeDao = MySessionUtils.getMapper(RouteDao.class);
        int start = (currentPage-1)*pageSize;
        //查询路线
        List<Route> routeList= routeDao.findRouteByName(name,start,pageSize);
        //查询总条目数
        int totalCount= routeDao.findCountByName(name);

        PageBean pageBean = new PageBean();
        pageBean.setList(routeList);
        pageBean.setTotalCount(totalCount);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
