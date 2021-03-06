<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<html>
<head>
    <title>开始抢红包</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../common/navigation.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                开始抢红包
            </h3>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包有超发可能"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacket"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 悲观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketForUpdate"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 乐观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketForVersion"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 乐观锁100毫秒重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketForVersionAndTimeMillis"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="常抢红包 乐观锁3次重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketForVersionAndThrice"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="用存储过程执行抢红包"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketByProcedure"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="用redis执行抢红包"
                   data-url="${pageContext.request.contextPath}/userRedPacket/grabRedPacketByRedis"></input>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
    function grab(url) {
        var max = 3000;
        var data = {
            redPackId: 1,
            userId: 1
        };
        for (var i = 0; i < max; i++) {
            $.ajax({
                type: "post",
                url: url,
                // contentType: "application/json",
                data: data,
                success: function (result) {
                    console.log(result.data);
                    console.log(result.msg);
                }
            });
        }
    }

    $(function () {
        $(".btn-default").on('click', function () {
            var url = $(this).data("url");
            grab(url);
        });
    });
</script>
</html>
