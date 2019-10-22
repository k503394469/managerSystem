<%@ page import="com.liu.domain.Student" %><%--
  Created by IntelliJ IDEA.
  User: Mr.c
  Date: 2019/06/18
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<%
    Student tempStu = (Student) request.getAttribute("tempStu");
%>
<jsp:include page="/WEB-INF/publicPage/userInfo.jsp"/>
<h1>UpdateStudent</h1>
<form method="post" action="/managerSystem/studentController?method=update">
    <input type="hidden" name="id" value="<%=tempStu.getId()%>">
    Name:<input type="text" name="name" value="<%=tempStu.getName()%>"><br/>
    Age:<input type="text" name="age" value="<%=tempStu.getAge()%>"><br/>
    Sex:<input type="radio" name="sex" value="male" checked="checked">Male <input type="radio" name="sex" value="female"> Female<br/>
    Address:<input type="text" name="address" value="<%=tempStu.getAddress()%>"/><br/>
    Email:<input type="email" name="email" value="<%=tempStu.getEmail()%>"/><br/>
    Dept:<select name="dept">
    <option value="Info">Info</option>
    <option value="Math">Math</option>
    <option value="Mass">Mass</option>
    <option value="Eco">Eco</option>
    </select><br/>
    <input type="submit" value="Update" name="update">
</form>
</body>
</html>
