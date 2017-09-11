<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/12
  Time: 上午 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>南方微博</title>
    <!--     <meta name="viewport" content="width=device-width,initial-scale=1"> -->
    <meta name="csrf-param" content="authenticity_token"/>

    <link rel="stylesheet" href="/css/wedapp-Posts-index.css">
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>

<%--<%@include file="WEB-INF/view/h_f/header.jsp" %>--%>
<jsp:include page="h_f/header.jsp" flush="true"></jsp:include>


<div class="container" style="margin-top: 90px">
    <%-- 判断 activated 会话里是否为空 --%>
    <c:if test="${activated != null}">
        <div class="alert alert-success">
                ${activated}
        </div>
    </c:if>
    <div class="center jumbotron">
        <h1>欢迎使用南方微博！</h1>

        <h2>
            小<a href="https://github.com/s126">老鼠</a>，上灯台，偷油吃，下不来！发条微博叫人来。
        </h2>

        <a class="btn btn-lg btn-primary" href="/users/add">立刻注册！</a>
    </div>

    <div style="height: 5em"></div>

    <%@include file="h_f/footer.jsp" %>


</div>
</body>
<style>
    body {
        margin: 33px;
    }

    h1 {
        font-size: 63px;
        text-align: center;
        margin: 0.67em 0;
        line-height: 1;
    }


</style>
</html>
