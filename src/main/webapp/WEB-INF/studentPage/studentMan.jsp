<%--
  Created by IntelliJ IDEA.
  User: Mr.k
  Date: 2019/7/8
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>studentMan</title>
</head>
<jsp:include page="/WEB-INF/publicPage/studentInfo.jsp"></jsp:include>
<body>
<h1>My Manager</h1>
<a href="/managerSystem/coursesController?method=viewLesson">Look My lesson</a>&nbsp;<br/>
<a href="/managerSystem/stuExitServlet">Back to Student Login</a>
</body>
</html>
