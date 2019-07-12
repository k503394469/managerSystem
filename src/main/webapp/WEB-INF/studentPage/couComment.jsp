<%--
  Created by IntelliJ IDEA.
  User: Mr.k
  Date: 2019/7/12
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>comment</title>
</head>
<body>
<%
    String comment= (String) request.getAttribute("comment");
%>
<p><%=comment%></p>
</body>
</html>
