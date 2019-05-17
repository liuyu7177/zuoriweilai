<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../../common/tag.jsp" %>
<html>
<head>
    <title>传递JSON参数</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">

    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                通过Json传递参数
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-2"><span class="label label-success">返回结果：</span></div>
                <div class="col-xs-10">
                    <pre id="resultDiv"></pre>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../../common/foot.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var data = {
            roleName: "rolefds房貸首付",
            roleNote: "note",
            pageParams: {
                start: 1,
                limit: 20
            },
            pageParamsList:[{
                start: 1,
                limit: 20
            },{
                start: 1,
                limit: 20
            },{
                start: 1,
                limit: 20
            }]
        };
        $.ajax({
            type: "post",
            url: "/spring/ssm/addParamByJson",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (result) {
                $("#resultDiv").html(JSON.stringify(result))
            }
        });
    });
</script>
</html>
