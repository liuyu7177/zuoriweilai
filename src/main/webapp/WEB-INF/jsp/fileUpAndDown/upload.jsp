<%@ page import="com.liuyu7177.zuoriweilai.model.entity.UserInfo" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<html>
<head>
    <title>文件上传</title>
    <%@include file="../common/head.jsp" %>
    <link href="${pageContext.request.contextPath}/resources/plugin_lib/fileinput/fileinput.min.css" rel="stylesheet">
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
            <form role="form" action="/fileUpAndDown/upload" enctype="multipart/form-data" method="post">
                <input type="file" name="file" value="请选择上传的文件"/>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugin_lib/fileinput/fileinput.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugin_lib/fileinput/zh.js"></script>
</html>
