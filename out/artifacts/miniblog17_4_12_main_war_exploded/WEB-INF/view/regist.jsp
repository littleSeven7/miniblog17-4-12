<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/registLogin.css">
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
<jsp:include page="h_f/header.jsp" flush="true"></jsp:include>

<div class="container" style="margin-top: 110px">
    <h1>用户注册</h1>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form:form action="/users/addHandle" modelAttribute="users">
                <%-- mvc  modelAttribute="users" 是模型  = ‘传递的参数’ --%>
                <%--<div>--%>
                <%--<form:errors path="*" cssClass="error" element="p"/>--%>
                <%--</div>--%>

                <div>
                    <label>用户名</label>
                    <form:input path="name" Class="form-control"/>
                    <form:errors path="name" cssClass="error" element="p"/>
                </div>
                <div>
                    <label>邮箱</label>
                    <form:input path="email" Class="form-control"/>
                    <form:errors path="email" cssClass="error" element="p"/>
                </div>
                <div>
                    <label>密码</label>
                    <form:input type="password" path="password_d" name="password_d" Class="form-control"/>
                    <form:errors path="password_d" cssClass="error" element="p"/>
                </div>

                <input type="submit" value="创建我的账号" Class="btn btn-primary" onsubmit="return check();"/>

            </form:form>
        </div>
    </div>
    <%@include file="h_f/footer.jsp" %>
</div>


<%--<button id="showmsg">点我显示某些东西</button>--%>
<style>
    input {
        margin-bottom: 15px;
        width: 100%;
        box-sizing: border-box;
        font-family: inherit;
    }
</style>

</body>
</html>
