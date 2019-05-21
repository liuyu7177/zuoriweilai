<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<html>
<head>
    <title>validation的使用</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="../common/navigation.jsp" %>
    <div class="panel panel-default">

        <div class="panel-heading">
            <h3 class="panel-title">
                提交交易信息
            </h3>
        </div>
        <div class="panel-body">
            <div class="panel-body">
                <form role="form" id="form1" action="/validation/transaction" method="post">
                    <div class="form-group">
                        <label for="productId">产品编号:</label>
                        <input type="text" class="form-control validate[required]" id="productId" name="productId"
                               value="${transaction.productId}">
                    </div>
                    <div class="form-group">
                        <label for="userId">用户编号:</label>
                        <input type="text" class="form-control validate[required]" id="userId" name="userId"
                               value="${transaction.userId}">
                    </div>
                    <div class="form-group">
                        <label for="date">交易日期:</label>
                        <input type="text" class="form-control validate[required]" id="date" name="date"
                               value="<fmt:formatDate value="${transaction.date}" pattern="yyyy-MM-dd"/>">
                    </div>
                    <div class="form-group">
                        <label for="price">价格:</label>
                        <input type="text" class="form-control validate[required]" id="price" name="price" value="${transaction.price}">
                    </div>
                    <div class="form-group">
                        <label for="quantity">数量:</label>
                        <input type="text" class="form-control validate[required]" id="quantity" name="quantity"
                               value="${transaction.quantity}">
                    </div>
                    <div class="form-group">
                        <label for="amount">交易金额:</label>
                        <input type="text" class="form-control validate[required]" id="amount" name="amount" value="${transaction.amount}">
                    </div>
                    <div class="form-group">
                        <label for="email">用户邮件:</label>
                        <input type="text" class="form-control validate[required]" id="email" name="email" value="${transaction.email}">
                    </div>
                    <div class="form-group">
                        <label for="note">备注:</label>
                        <textarea type="text" class="form-control validate[required]" cols="20" rows="5" id="note"
                                  name="note">${transaction.note}</textarea></div>
                    <input type="submit" value="提交" class="btn btn-default"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#form1").submit(function (e) {
            e.preventDefault();
            var isValid = $(this).validationEngine("validate");
            if (!isValid) {
                return false;
            }
            this.submit();
        });
    });
</script>
</html>
