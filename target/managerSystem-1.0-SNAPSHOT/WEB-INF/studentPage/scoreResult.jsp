<%--
  Created by IntelliJ IDEA.
  User: Mr.c
  Date: 2019/07/16
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>result</title>
</head>
<body>
<%
    String result = (String) request.getAttribute("result");
%>
<h3>${requestScope.result}</h3>
<a href="/managerSystem/coursesController?method=viewLesson">back to view</a>
</body>
</html>
