<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../../common/tag.jsp" %>
<html>
<head>
    <title>新增角色表单</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../../common/navigation.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                新增角色By PoJo
            </h3>
        </div>
        <div class="panel-body">
            <form role="form" action="/spring/ssm/addRoleForPojo" method="post">
                <div class="form-group">
                    <label for="roleName">角色名称</label>
                    <input type="text" class="form-control" id="roleName" name="roleName" value="${role.roleName}" placeholder="请输入角色名称">
                </div>
                <div class="form-group">
                    <label for="roleNote">备注</label>
                    <input type="text" class="form-control" id="roleNote" name="roleNote" value="${role.roleNote}" placeholder="请输入角色名称">
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="../../common/foot.jsp" %>
</html>
