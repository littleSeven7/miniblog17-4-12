<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/22
  Time: 上午 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人资料</title>
    <link rel="stylesheet" href="/css/registLogin.css">
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
<%@include file="../h_f/header2.jsp" %>
<div class="container" style="margin-top: 110px">
    <h1>修改个人资料</h1>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form:form action="/users/${id}" modelAttribute="users">

                <label>邮箱</label>
                <input class="form-control" readonly="readonly" type="email"
                       value="${session_user.email}" name="email"/>

                <label>用户名</label>
                <input class="form-control" type="text" value="${session_user.name}" name="name"/>
                <form:errors path="name" cssClass="error" element="p"/>

                <label>密码</label>
                <input class="form-control" type="password" name="password_d"/>
                <form:errors path="password_d" cssClass="error" element="p"/>

                <label>密码确认</label>
                <input class="form-control" type="password" name="password1"/>

                <input type="submit" name="commit" value="保存更改"
                       class="btn btn-primary" data-disable-with="保存更改"/>
            </form:form>
            <div class="gravatar_edit">
                <img class="gravatar" src="/img/2.jpg"/>
                <a href="http://gravatar.com/emails" target="_blank">更换头像</a>
            </div>
        </div>
    </div>
    <%@include file="../h_f/footer.jsp" %>
</div>
</body>
</html>
