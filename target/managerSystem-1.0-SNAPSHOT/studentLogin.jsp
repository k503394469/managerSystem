<%--
  Created by IntelliJ IDEA.
  User: Mr.k
  Date: 2019/7/8
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Login</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
%>
<h1>Welcome</h1>
<form action="/managerSystem/gotoMyController" method="post">
    Account:<input type="text" name="id"/><br/>
    Password:<input type="password" name="password"/><br>
    <input type="submit" value="login" name="submit"/>
    <%
        if (error != null && error != "" && !(error.isEmpty())) {
    %>
    <p style="color: red"><%=error%></p>
    <%
        }
    %>
</form>

</body>
</html>
