<%--
  Created by IntelliJ IDEA.
  User: glc
  Date: 2020/9/30
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>途牛旅游-搜索</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/myFunction.js"></script>
    <script src="js/getParameter.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var search = getParameter('search')
        var cid = getParameter('cid');
        if(search!=null){
            var currentPage =1
            var pageSize=10
            load('RouteServlet','search',search,currentPage,pageSize);
            return
        }else if(cid!=null){
            var currentPage =1
            var pageSize=10
            load('RouteServlet2','cid',cid,currentPage,pageSize);
            return
        }
        // var searchencode = window.location.search.split('=');
        // var search = decodeURI(searchencode[1]);

    })
    function load(url,dataname,info,currentPage,pageSize) {
        $.ajax({
            url:url,//url
            async:true,//true 同步请求
            data:''+dataname+'='+info+'&currentPage='+currentPage+'&pageSize='+pageSize+'',
            type:"get",//指定请求方式
            dataType:"json",//预期返回的数据类型
            success:function (data) {//请求成功后的回调函数。
                if(data.flag==true){
                    routeList = data.data
                    showList(routeList)
                    showPageInfo(url,dataname,info,routeList);
                }
            },
            error:function () {//请求失败时调用此函数。
            }
        });
    }




</script>
<body>
<!--引入头部-->
<div id="header">
    <%@include file="header.jsp"%>
</div>
<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="images/search.png" alt="">
            <p>途牛旅行><span>搜索结果</span></p>
        </div>
        <div class="xinxi clearfix">
            <div class="left">
                <div class="header">
                    <span>商品信息</span>
                    <span class="jg">价格</span>
                </div>

                <ul id="showlist">

                </ul>

                <div class="page_num_inf" id="page_num_info">

                </div>
                <div class="pageNum" id="pageNum">
                    <ul>
                    </ul>
                </div>
            </div>
            <div class="right">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>热门推荐</span>
                </div>
                <ul>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--引入头部-->
<div id="footer">
    <%@include file="footer.jsp"%>
</div>
</body>

</html>