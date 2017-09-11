<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微博列表</title>

    <link rel="stylesheet" href='/css/wedapp-Posts-index.css'>
    <link rel="stylesheet" href="/css/public.css">
</head>

<body>

<%@include file="../h_f/header2.jsp" %>
<div class="container" style="margin-top: 93px">

    <c:if test="${info != null}">
        <div class="alert alert-success">
                ${info}
        </div>
    </c:if>
    <div class="row">
        <aside class="col-md-4">
            <section class="user_info">
                <a href="/posts/${session_user.id}">
                    <img class="gravatar" src="/img/${session_user.picture}"/>

                </a>
                <h1>${session_user.name}</h1>
                <span><a href="/${session_user.id}">查看主页</a></span>
                <span>共 ${session_post.size()} 则微博</span>
            </section>
            <section class="stats">
                <div class="stats">
                    <a href="/${id}/following">
                        <strong id="following" class="stat"> ${session_Ruser_id.size()} </strong> 关注
                    </a>
                    <a href="/${id}/followers">
                        <strong id="followers" class="stat"> ${session_Ruser_id_p.size()} </strong> 粉丝
                    </a>
                </div>

            </section>
            <section class="post_form">
                <form action="/users/posts" method="post">

                    <div class="field">
                        <textarea placeholder="发布新微博..." name="content" required></textarea>
                    </div>
                    <input type="submit" id="commit" name="commit" value="发布" class="btn btn-primary"
                           data-disable-with="发布"/>
                    <%-- <span class="picture">
                         <input accept="image/jpeg,image/gif,image/png" type="file" name="post_picture"
                                id="post_picture"/>
                         <img id="preview" width="100%" height="350">
                     </span>--%>
                </form>

            </section>
        </aside>

        <div class="col-md-8">
            <h3>微博动态</h3>
            <ol class="posts">
                <ul id="pn">
                    <%-- 循环这段 --%>
                    <c:forEach items="${session_post}" var="p" varStatus="status">
                        <li class="list0">
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
                                            <a href="/posts/delete/${p.id}" class="drop">delete</a>
                                            <%--登录会话里的id  和显示的id一致显示--%>
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
                                                        <a href="javascript:;" ms-click='reply_click($event,$index)'>
                                                            回复<span class="user">${c.c_user_id.name}</span></a>
                                                        <a href="/posts/delete/${p.id}" class="drop">delete</a>
                                                        <%-- 登录会话里的id  和显示的评论的id一致显示 --%>
                                                    </c:if>
                                                </div>

                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                    <%-- 循环这段 --%>
                </ul>
            </ol>

        </div>
    </div>

    <%@include file="../h_f/footer.jsp" %>
</div>
</body>
<script type="text/javascript">

    $('#post_picture').bind('change', function () {
        var size_in_megabytes = this.files[0].size / 1024 / 1024;
        if (size_in_megabytes > 5) {
            alert('文件的最大限制是 5M, 请重新选择');
        }
    });
    $("#post_picture").change(function () {
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#preview");

        if (fileObj && fileObj.files && fileObj.files[0]) {
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            $img.attr('src', dataURL);
        } else {
            dataURL = $file.val();
            var imgObj = document.getElementById("preview");
            // 两个坑:
            // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
            // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
            imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

        }
    });

</script>
<%--<script>
    function deletePost(id) {
        $.ajax({
            method: 'delete',
            url: 'users/' + id,
            dataType: 'text',
            success: function (data) {
                if (data == "success") {
                    alert("删除成功");
                    location.reload();
                }
            }
        });
    }
</script>--%>
</html>