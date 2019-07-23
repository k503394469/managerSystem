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
    String cid= (String) request.getAttribute("cid");
    String sid= (String) request.getAttribute("sid");
%>
<p><%=comment%></p>
<form action="/managerSystem/coursesController?method=deleteComm" method="post">
    <input type="hidden" name="comment" value="<%=comment%>">
    <input type="hidden" name="cid" value="<%=cid%>">
    <input type="hidden" name="sid" value="<%=sid%>">
    <input type="submit"  name="delete" value="Delete">
</form>
<form action="/managerSystem/coursesController?method=updateComm" method="post">
    <input type="hidden" name="comment" value="<%=comment%>">
    <input type="hidden" name="cid" value="<%=cid%>">
    <input type="hidden" name="sid" value="<%=sid%>">
    <input type="submit"  name="update" value="Update">
</form>
<a href="/managerSystem/coursesController?method=viewLesson">back to view</a>
</body>
</html>
