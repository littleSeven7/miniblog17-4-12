<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/12
  Time: 下午 07:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="/css/registLogin.css">
    <link rel="stylesheet" href="/css/public.css">


</head>
<body>
<%@include file="h_f/header.jsp" %>
<div class="container" style="margin-top: 110px">
    <h1>登录</h1>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/users/login" accept-charset="UTF-8" method="post">
                <div>
                    <%--<form:errors path="*" cssClass="error" element="p"/>--%>
                </div>
                <label>邮箱</label>
                <input class="form-control" type="email" name="email"/>

                <label>密码</label>
                <a href="/password_resets/new">(忘记密码)</a>
                <input class="form-control" type="password" name="password"/>

                <label class="checkbox inline">
                    <input type="checkbox" value="1" name="remember_me]"/>
                    <span>记住密码</span>
                </label>
                <input type="submit" name="commit" value="登录" class="btn btn-primary" data-disable-with="登录"/>
            </form>
            <p>没有账号？ <a href="/users/add">点击注册！</a></p>
        </div>
    </div>
    <%@include file="h_f/footer.jsp" %>
</div>
</body>
<style>

</style>
</html>
