<%--
  Created by IntelliJ IDEA.
  User: Mr.c
  Date: 2019/07/23
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<%
    String sid= (String) request.getAttribute("sid");
    String cid= (String) request.getAttribute("cid");
    String comment= (String) request.getAttribute("comment");
%>
<form action="/managerSystem/coursesController?method=update" method="post" id="commUp">
    <textarea name="comment" form="commUp"><%=comment%></textarea>
    <input type="hidden" name="sid" value="sid"/>
    <input type="hidden" name="cid" value="cid"/>
    <input type="submit" name="update" value="update"/>
</form>
</body>
</html>
