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
            <input type="button" id="grabBtn" class="btn btn-default" value="开始"></input>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
    function grab() {
        var max=3000;
        var data = {
            redPackId:1,
            userId: 1
        };
        for(var i=0;i<max;i++){
            $.ajax({
                type: "post",
                url: "/userRedPacket/grabRedPacket",
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
       $("#grabBtn").click(function () {
           grab();
       });
    });
</script>
</html>
