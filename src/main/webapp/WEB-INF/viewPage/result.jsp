<%--
  Created by IntelliJ IDEA.
  User: Mr.c
  Date: 2019/06/18
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result</title>
</head>
<body>
<%
    String result = (String) request.getAttribute("result");
    Integer totalPage= (Integer) request.getSession().getAttribute("totalPage");
%>
    <h3><%=result%></h3>
<a href="/managerSystem/gotoStudentManager?method=view&page=<%=(totalPage==null)?1:totalPage%>">back to view</a>
</body>
</html>
