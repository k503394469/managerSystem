<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
%>
<h1>Welcome</h1>
<form action="/managerSystem/gotoIndex" method="post">
    Account:<input type="text" name="username"/><br/>
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
