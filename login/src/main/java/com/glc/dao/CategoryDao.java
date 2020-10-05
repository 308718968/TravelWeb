package com.glc.dao;

import com.glc.bean.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryDao {
    @Select("select * from tab_category order by cid")
    public List<Category> findAll();

}
