<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>@RequestAttribute forward</title>
</head>
<body>
<%
    request.setAttribute("idTest", 1L);
    request.getRequestDispatcher("/spring/ssm/showRequestAttributeValue").forward(request,response);
%>

</body>
</html>
