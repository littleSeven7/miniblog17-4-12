<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/17
  Time: 下午 04:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/headerFooter.css">

</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <a id="logo" href="/">南方微博</a>
        <nav>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/posts/index">首页</a></li>
                <li><a href="/posts/users">查找用户</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        ${session_login_user.name}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/posts/${session_login_user.id}">个人主页</a></li>
                        <li><a href="users/post/${session_login_user.id}/edit">修改资料</a></li>
                        <li class="divider"></li>
                        <li>
                            <a rel="nofollow" data-method="delete" href="/logout">注销</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>

