<%--
  Created by IntelliJ IDEA.
  User: glc
  Date: 2020/9/29
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>途牛旅游网-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入angularJS文件-->
    <script src="js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function loginorcheck() {
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"LoginServlet",//url
                async:false,//true 同步请求
                data:$("#loginForm").serialize(),
                type:"post",//指定请求方式
                dataType:"json",//预期返回的数据类型
                success:function (data) {//请求成功后的回调函数。
                    if(data.flag==false){
                        $("#errorMsg").text("");

                        $("#errorMsg").text(data.errorMsg);
                    }
                    else{
                        $("#errorMsg").text("");
                         window.location = "index.jsp"
                    }
                },
                error:function () {//请求失败时调用此函数。
                    alert("服务器发生了错误")
                }
            });
        }
        function Autologin() {
            $.ajax({
                url:"AutoLoginServlet",//url
                async:true,//true 同步请求
                type:"post",//指定请求方式
                dataType:"json",//预期返回的数据类型
                success:function (data) {//请求成功后的回调函数。
                         window.location = "index.jsp"
                },
                error:function () {//请求失败时调用此函数。
                }
            });
        }
        $(function () {
            Autologin()
            $("#btn").click(loginorcheck)
            $("#errorMsg").text(${loginmsg})
        })


    </script>
</head>

<body>
<!--引入头部-->
<div id="header">
    <%@include file="header.jsp"%>
</div>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg" style="background: url(images/login_bg.png);height: 532px;">

    </div>
    <div class="login-box">
        <div class="title">
            <img src="images/login_logo.png" alt="">
            <span>欢迎登录途牛旅游账户</span>
        </div>
        <div class="login_inner">

            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger" ></div>
            <form id="loginForm" action="" method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input id="username" name="username" type="text" placeholder="请输入账号" autocomplete="off">
                <input id= "password" name="password" type="text" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input name="check" type="text" placeholder="请输入验证码" autocomplete="off">
                   <span> <img src="${pageContext.request.contextPath}/CheckCodeServlet" alt="" onclick="changeCheckCode(this)"></span>
                    <script type="text/javascript">
                        //图片点击事件
                        function changeCheckCode(img) {
                            img.src="${pageContext.request.contextPath}/CheckCodeServlet?"+new Date().getTime();
                        }
                    </script>
                </div>
                <div class="submit_btn">
                    <button id="btn" type="button">登录</button>
                    <div class="auto_login">
                        <input id="rememberme" type="checkbox" name="rememberme" class="checkbox">
                        <span>自动登录</span>
                    </div>
                </div>
            </form>
            <div class="reg">没有账户？<a href="javascript:;">立即注册</a></div>
        </div>
    </div>
</section>
<!--引入尾部-->
<div id="footer">
    <%@include file="footer.jsp"%>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
</body>

</html>
