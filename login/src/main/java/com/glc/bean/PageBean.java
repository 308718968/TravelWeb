package com.glc.bean;

import java.awt.*;
import java.util.List;

public class PageBean {
    //总记录数 100
    private int totalCount;
    //每页记录数 20
    private  int pageSize;
    //总页数 5
    private int totalPage;
    //当前页号
    private int currentPage;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", list=" + list +
                '}';
    }

    public PageBean() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Route> getList() {
        return list;
    }

    public void setList(List<Route> list) {
        this.list = list;
    }

    public PageBean(int totalCount, int pageSize, int totalPage, int currentPage, List<Route> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.list = list;
    }

    //当前页的数据
    private List<Route> list;

    public PageBean count(int totalCount) {
        this.setTotalCount(totalCount);
        this.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        return this;
    }

    public PageBean list(List<Route> routeList) {
        this.setList(routeList);
        return this;
    }
}
