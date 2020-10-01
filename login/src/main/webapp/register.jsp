<%--
  Created by IntelliJ IDEA.
  User: glc
  Date: 2020/9/30
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
</head>
<script type="text/javascript">
    //检验密码
    function checkPassword() {
        var password = $("#password").val();
        //定义正则
        var reg = /^\w{6,20}$/;
        //校验
        var flag = reg.test(password);
        if (flag){
            //验证成功
            $("#password").css("border","");
            $("#passwordmsg").text("")

        }else {
            //验证失败
            $("#password").css("border","1px solid red");
            $("#passwordmsg").text("密码长度不能小于6").css("color","red");
        }
        return flag;
    }
    //检验邮箱
    function checkEmail(){
        var email = $("#email").val();

        //定义正则校验邮箱
        var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
        var flag = reg.test(email);
        if (flag){
            //验证成功
            $("#email").css("border","");
            $("#emailmsg").text("");

        }else {
            //验证失败
            $("#email").css("border","1px solid red");
            $("#emailmsg").text("邮箱格式不正确").css("color","red")
        }
        return flag;
    }

</script>
<body>
<!--引入头部-->
<div id="header">
    <%@include file="header.jsp"%>
</div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">

            <!--注册表单-->
            <form id="registerForm" method="post" action="/RegisterServlet" onsubmit="return false" >
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="username">用户名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="username" name="username" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="password" name="password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" placeholder="请输入Email">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
                        </td>
                        <td class=""></td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <input type="date" id="birthday" name="birthday" placeholder="年/月/日">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="check">验证码</label>
                        </td>
                        <td class="td_right check">
                            <input type="text" id="check" name="check" class="check">

                            <img id="code" src="${pageContext.request.contextPath}/CheckCodeServlet" height="32px" alt="" onclick="changeCheckCode(this)">

                            <script type="text/javascript">
                                //图片点击事件
                                function changeCheckCode(img) {
                                    img.src="${pageContext.request.contextPath}/CheckCodeServlet?"+new Date().getTime();
                                }
                            </script>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="submit" class="submit" value="注册" >
                            <span id="msg" style="color: #ff0000;"></span>
                        </td>
                        <script type="text/javascript">
                            $("#registerForm").submit(function () {
                                $.ajax({
                                    url:"${pageContext.request.contextPath}/RegisterServlet",
                                    async:false,//异步提交注册信息
                                    data:$("#registerForm").serialize(),
                                    type:"post",
                                    dataType:"json",
                                    success:function (data) {
                                        if(data.flag==false){//账号密码不正确，页面局部显示错误信息
                                            $("#msg").text(data.errorMsg).css("color","red")
                                        }else {//成功跳转
                                            $(location).attr('href', "${pageContext.request.contextPath}/register_ok.jsp")
                                        }
                                    },
                                    error:function () {
                                    }
                                });
                                changeCheckCode(document.getElementById('code'))
                            })
                        </script>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="${pageContext.request.contextPath}/login.jsp">立即登录</a>
            </p>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer">
    <%@include file="footer.jsp"%>
</div>
<!--导入布局js，共享header和footer-->

</body>
</html>
