<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/23
  Time: 下午 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发现</title>
    <%--<link rel="stylesheet" href="/css/wedapp-Posts-index.css">--%>
    <link rel="stylesheet" href="/css/registLogin.css">
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
<%@include file="../h_f/header2.jsp" %>

<div class="container" style="margin-top: 112px">
    <h1>发现更大世界，给你更多激情
        <p>这个页面应该包含搜索栏，还应该有各种好友推荐什么的。在这里简化为显示所有用户了。because i'm lazy.</p>
    </h1>
    <br>

    <div class="pagination">
        <ul class="pagination">
            <li class="prev previous_page disabled"><a href="#">&#8592; Previous</a></li>
            <li class="active"><a rel="start" href="/users?page=1">1</a></li>
            <li><a rel="next" href="/users?page=2">2</a></li>
            <li class="next next_page "><a rel="next" href="/users?page=2">Next &#8594;</a></li>
        </ul>
    </div>

    <ul class="users">

        <%-- 循环这段 --%>
        <c:forEach items="${session_users_all}" var="p" varStatus="status">
            <li>
                <img class="gravatar" src="/img/1.jpg"/>
                <a href="/posts/${p.id}">${p.name}</a>
            </li>

        </c:forEach>
        <%-- 循环这段 --%>
    </ul>

    <div class="pagination">
        <ul class="pagination">
            <li class="prev previous_page disabled"><a href="#">&#8592; Previous</a></li>
            <li class="active"><a rel="start" href="/users?page=1">1</a></li>
            <li><a rel="next" href="/users?page=2">2</a></li>
            <li class="next next_page "><a rel="next" href="/users?page=2">Next &#8594;</a></li>
        </ul>
    </div>

    <%@include file="../h_f/footer.jsp" %>
</div>
</body>
</html>
