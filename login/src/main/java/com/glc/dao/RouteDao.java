package com.glc.dao;

import com.glc.bean.PageBean;
import com.glc.bean.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RouteDao {
    @Select("select * from tab_route where rname like #{name} limit #{start},#{pageSize}")
    List<Route> findRouteByName(@Param("name") String name,@Param("start")int start,@Param("pageSize")int pageSize);
    @Select("select count(*) from tab_route where rname like #{name}")
    int findCountByName(String name);
    @Select("select count(*) from tab_route where cid = #{cid}")
    int findCountById(int cid);
    @Select("select * from tab_route where cid = #{cid} limit #{start},#{pageSize}")
    List<Route> findRouteById(@Param("cid") int cid,@Param("start")int start,@Param("pageSize")int pageSize);
}
