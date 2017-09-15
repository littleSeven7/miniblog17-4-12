<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/13
  Time: 下午 08:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>

    <script src="/js/jquery-1.11.0.min.js"></script>
    <script src="/js/jquery.js"></script>

    <script src="/js/posts_post_textarea.js"></script>

    <link rel="stylesheet" href="/css/wedapp-Posts-index.css">
    <link rel="stylesheet" href="/css/public.css">
</head>
<body>
<%@include file="../h_f/header2.jsp" %>
<div class="container" style="margin-top: 93px">
    <%-- 判断 info 会话里是否为空 --%>

    <c:if test="${info != null}">
        <div class="alert alert-success">
                ${info}
        </div>
    </c:if>
    <div class="row">
        <aside class="col-md-3">
            <section>
                <h1>
                    <img class="gravatar" src="/img/${session_user.picture}" width="80px" height="80px"/>
                    ${session_user.name}
                </h1>
            </section>
            <input name="session_userID" type="hidden" value="${session_user.id}">
            <section class="stats">
                <div class="stats">
                    <a href="/users/post/${session_user.id}/following">
                        <strong id="following" class="stat">${session_Ruser_id.size()} </strong> 关注
                    </a>
                    <a href="/users/post/${session_user.id}/followers">
                        <strong id="followers" class="stat"> ${session_Ruser_id_p.size()} </strong> 粉丝
                    </a>
                </div>

            </section>
            <section style="width: 50%;">
                <%-- 加一个判断 判断是自己的用户还是别人的 ↓  --%>
                <c:if test="${session_login_user.id != session_user.id}">
                    <%-- 登录会话里的id  和显示的id不致显示 --%>
                    <div id="follow_form">
                            <%-- 判断不是已经是好友 --%>
                        <c:if test="${ruser_id_a == false}">
                            <form class="new_relationship" action="${pageContext.request.contextPath }/posts/relationships/${session_user.id}/"
                                  method="post">
                                <input type="submit" name="commit" value="关注" class="btn btn-primary"/>
                            </form>
                        </c:if>

                            <%-- 判断是已经是好友 --%>
                        <c:if test="${ruser_id_a == true}">
                            <form class="new_relationship" action="${pageContext.request.contextPath }/posts/relationships_f/${session_user.id}"
                                  method="post">
                                <input type="submit" name="commit" value="取消关注" class="btn btn-primary"/>
                            </form>
                        </c:if>
                    </div>
                </c:if>
                <%--  加一个判断 判断是自己的用户还是别人的 ↑  --%>

            </section>
        </aside>
        <div class="col-md-9">
            <h3>微博 (${session_post.size()})</h3>
            <ol class="posts">
                <ul id="pn">
                    <%-- 循环这段 --%>
                    <c:forEach items="${session_post}" var="p" varStatus="status">
                        <form action="${pageContext.request.contextPath }/posts/comments" method="post">
                            <li class="list0">
                                <input name="commId" type="hidden" value="${p.id}">
                                <div class="head">
                                    <a href="/posts/${session_user.id}">
                                        <img class="gravatar" src="/img/${session_user.picture}" width="50px"
                                             height="50px"/>
                                    </a>
                                </div>
                                <div class="content">
                                    <p class="text">
                                    <span class="name">
                                        <a href="/posts/${session_user.id}">${session_user.name} :</a>
                                    </span> ${p.body}
                                    </p>
                                    <div class="good">
                                    <span class="date"> ${p.created_t}
                                        <c:if test="${session_login_user.id == session_user.id}">
                                            <a href="/posts/delete/${p.id}"
                                               class="drop">delete</a><%--登录会话里的id  和显示的id一致显示--%>
                                        </c:if>
                                    </span>
                                    </div>
                                    <div class="comment-list">
                                        <c:forEach items="${p.commentss}" var="c">
                                            <div class="comment" user="self">
                                                <div class="comment-left">
                                                    <a href="/posts/${c.c_user_id.id}">
                                                        <img class="gravatar" src="/img/${c.c_user_id.picture}"/>
                                                    </a>
                                                </div>
                                                <div class="comment-right">
                                                    <div class="comment-text">
                                                        <span class="user">${c.c_user_id.name}</span>回复：${c.body}
                                                    </div>
                                                    <div class="comment-date">${c.created_t}
                                                        <c:if test="${session_login_user.id == c.c_user_id.id}">
                                                            <a href="/posts/delete/${p.id}" class="drop">delete</a>
                                                            <%-- 登录会话里的id  和显示的评论的id一致显示 --%>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <div class="hf">
                                            <textarea type="text" class="hf-text" autocomplete="off" maxlength="100" name="body">评论…</textarea>
                                            <button class="hf-btn">回复</button>
                                            <span class="hf-nub">0/500</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </form>
                    </c:forEach>
                    <%-- 循环这段 --%>
                </ul>
            </ol>

        </div>
    </div>


    <%@include file="../h_f/footer.jsp" %>
</div>
</body>
<style>
    a {
        color: #000;
    }
</style>
</html>
