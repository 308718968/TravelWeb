<%--
  Created by IntelliJ IDEA.
  User: glc
  Date: 2020/9/29
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 头部 start -->
<header id="header">
    <div class="top_banner">
        <img src="images/top_banner.jpg" alt="">
    </div>
    <div class="shortcut">
        <!-- 未登录状态  -->
        <div class="login_out">
            <c:if test="${user==null}">
                <a href="login.jsp" >未登录,请登录 </a>
                <a href="register.jsp" style="color:#ff4444">免费注册</a>
            </c:if>

        </div>
        <!-- 登录状态  -->
        <div class="login">
            <c:if test="${user!=null}">
                <span>欢迎回来,${user.name}</span>
                <a href="myfavorite.jsp" class="collection">我的收藏</a>
                <a href="${pageContext.request.contextPath}/CancelAutoLoginServlet">退出</a>
            </c:if>


        </div>
    </div>
    <div class="header_wrap">
        <div class="topbar">
            <div class="logo">
                <a href="/"><img src="images/logo.jpg" alt=""></a>
            </div>
            <div class="search">
                <input name="" type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                <a href="javascript:;" class="search-button">搜索</a>
            </div>
            <div class="hottel">
                <div class="hot_pic">
                    <img src="images/hot_tel.jpg" alt="">
                </div>
                <div class="hot_tel">
                    <p class="hot_time">客服热线(9:00-6:00)</p>
                    <p class="hot_num">400-000-0000</p>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- 头部 end -->
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"CategoryServlet",//url
            async:true,//true 同步请求
            type:"get",//指定请求方式
            dataType:"json",//预期返回的数据类型
            success:function (data) {//请求成功后的回调函数。
                if(data.flag==true){
                    var list = data.data;
                    lis='<li class="nav-active"><a href="index.jsp">首页</a></li>';
                    for(var i=0;i<list.length;i++){
                        var li = '<li><a href="route_list.jsp">'+list[i].cname+'</a></li>'
                        lis+=li;
                    }
                    lis+='<li><a href="favoriterank.jsp">收藏排行榜</a></li>'
                    $("#nav").html(lis);
                }
            },
            error:function () {//请求失败时调用此函数。
            }
        });
    })
</script>
<!-- 首页导航 -->
<div class="navitem">
    <ul class="nav" id="nav">
<%--        <li class="nav-active"><a href="index.jsp">首页</a></li>--%>
<%--        <li><a href="route_list.jsp">门票</a></li>--%>
<%--        <li><a href="route_list.jsp">酒店</a></li>--%>
<%--        <li><a href="route_list.jsp">香港车票</a></li>--%>
<%--        <li><a href="route_list.jsp">出境游</a></li>--%>
<%--        <li><a href="route_list.jsp">国内游</a></li>--%>
<%--        <li><a href="route_list.jsp">港澳游</a></li>--%>
<%--        <li><a href="route_list.jsp">抱团定制</a></li>--%>
<%--        <li><a href="route_list.jsp">全球自由行</a></li>--%>
<%--        <li><a href="favoriterank.jsp">收藏排行榜</a></li>--%>
    </ul>
</div>