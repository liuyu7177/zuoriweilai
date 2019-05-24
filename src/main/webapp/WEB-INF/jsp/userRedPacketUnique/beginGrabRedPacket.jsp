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
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacket"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 悲观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForUpdate"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 乐观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersion"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 乐观锁100毫秒重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersionAndTimeMillis"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="常抢红包 乐观锁3次重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersionAndThrice"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="用存储过程执行抢红包"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketByProcedure"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 调整代码顺序测试 悲观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForUpdateOrder"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 调整代码顺序测试 乐观锁机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersionOrder"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="正常抢红包 调整代码顺序测试 乐观锁100毫秒重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersionAndTimeMillisOrder"></input>
        </div>
        <div class="panel-body">
            <input type="button" class="btn btn-default" value="常抢红包 调整代码顺序测试 乐观锁3次重入机制"
                   data-url="${pageContext.request.contextPath}/userRedPacketUnique/grabRedPacketForVersionAndThriceOrder"></input>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
    function grab(url) {
        var max = 3000;
        var beginUserId=${userId};
        for (var i = 0; i < max; i++) {
            var data = {
                redPackId: 1,
                userId: beginUserId+i
            };
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
