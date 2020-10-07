package com.glc.service;

import com.glc.bean.PageBean;
import com.glc.bean.Route;
import com.glc.bean.PageBeanHelper;
import com.glc.dao.RouteDao;
import com.glc.util.MySessionUtils;

import java.util.List;

public class RouteService {
    public PageBean search(String name,int currentPage,int pageSize) {
        RouteDao routeDao = MySessionUtils.getMapper(RouteDao.class);
        int start = (currentPage-1)*pageSize;
        //查询路线
        List<Route> routeList= routeDao.findRouteByName(name,start,pageSize);
        System.out.println(routeList);
        //查询总条目数
        int totalCount= routeDao.findCountByName(name);
        MySessionUtils.commitAndClose();
        PageBean pageBean = PageBeanHelper.create(currentPage, pageSize).count(totalCount).list(routeList);
        System.out.println(pageBean);
        return pageBean;
    }
    public PageBean searchById(int cid,int currentPage,int pageSize) {
        RouteDao routeDao = MySessionUtils.getMapper(RouteDao.class);
        int totalCount  = routeDao.findCountById(cid);
        int start = (currentPage-1)*pageSize;
        List<Route> routeList = routeDao.findRouteById(cid,start,pageSize);
        MySessionUtils.commitAndClose();
        PageBean pageBean = PageBeanHelper.create(currentPage,pageSize).count(totalCount).list(routeList);
        return pageBean;
    }
}
