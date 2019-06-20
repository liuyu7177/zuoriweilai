<%@ page import="com.liuyu7177.zuoriweilai.model.entitys.UserInfo" %>
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
                <label>userAgent:${userAgent}</label>
            </div>
            <div class="panel-body">
                <label>userName:${userName}</label>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
</html>
