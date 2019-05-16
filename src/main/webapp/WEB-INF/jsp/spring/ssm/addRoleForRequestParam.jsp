<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../../common/tag.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>新增角色表单</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../../common/navigation.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                新增角色By RequestParam
            </h3>
        </div>
        <div class="panel-body">
            <form role="form" action="/spring/ssm/addRoleForRequestParam" method="post">
                <div class="form-group">
                    <label for="role_name">角色名称</label>
                    <input type="text" class="form-control" id="role_name" name="role_name" value="${roleName}" placeholder="请输入角色名称">
                </div>
                <div class="form-group">
                    <label for="role_note">备注</label>
                    <input type="text" class="form-control" id="role_note" name="role_note" value="${roleNote}" placeholder="请输入角色名称">
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="../../common/foot.jsp" %>
</html>
