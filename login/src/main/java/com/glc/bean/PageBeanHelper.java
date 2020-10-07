package com.glc.bean;

import com.glc.bean.PageBean;


public class PageBeanHelper {
    public static PageBean create(int currentPage, int pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }
}
