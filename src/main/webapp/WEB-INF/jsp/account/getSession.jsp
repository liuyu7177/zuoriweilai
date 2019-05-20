<%@ page import="com.liuyu7177.zuoriweilai.model.entity.UserInfo" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<html>
<head>
    <title>Session信息展示界面</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../common/navigation.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                Session信息展示
            </h3>
        </div>
        <div class="panel-body">
            <div class="panel-body">
                <label>user:${user}</label>
            </div>
            <div class="panel-body">
                <label>id:${id}</label>
            </div>
            <div class="panel-body">
                <label>name:${name}</label>
            </div>
            <div class="panel-body">
                <label>json:${json}</label>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                Session信息展示 JSP的输出方式
            </h3>
        </div>
        <div class="panel-body">
            <div class="panel-body">
                <%
                    UserInfo u = (UserInfo) session.getAttribute("userInfo");
                    int userId = (Integer) session.getAttribute("userId");
                    String userName = (String) session.getAttribute("userName");
                    String userJson = "";
                    if (session.getAttribute("userJson") != null) {
                        userJson = session.getAttribute("userJson").toString();
                    }
                %>
                <label>id:<%= u.getUserId()%>
                </label>
                <label>name:<%= u.getUserName()%>
                </label>
            </div>
            <div class="panel-body">
                <label>userId:<%= userId%>
                </label>
            </div>
            <div class="panel-body">
                <label>userName:<%= userName%>
                </label>
            </div>
            <div class="panel-body">
                <label>userJson:<%= userJson%>
                </label>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
