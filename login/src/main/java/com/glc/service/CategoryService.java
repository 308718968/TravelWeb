package com.glc.service;

import com.glc.bean.Category;
import com.glc.bean.ResultInfo;
import com.glc.dao.CategoryDao;
import com.glc.util.MySessionUtils;

import java.util.List;

public class CategoryService {
    public ResultInfo findAll() {
        CategoryDao mapper = MySessionUtils.getMapper(CategoryDao.class);
        ResultInfo resultInfo=new ResultInfo();
        List<Category> list = mapper.findAll();
        if(list!=null){
            resultInfo.setFlag(true);
            resultInfo.setData(list);
        }else {
            resultInfo.setFlag(false);
        }
        return resultInfo;
    }
}
