<%@ page import="com.liu.domain.Student" %><%--
  Created by IntelliJ IDEA.
  User: Mr.k
  Date: 2019/7/8
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
</head>
<body>
<%
    Student studentScore = (Student) request.getAttribute("studentScore");
    int result = (int) request.getAttribute("result");
%>
<h1>Student Information</h1>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Number</td>
        <td>Dept</td>
    </tr>
    <tr>
        <td><%=studentScore.getName()%>
        </td>
        <td><%=studentScore.getId()%>
        </td>
        <td><%=studentScore.getDept()%>
        </td>
    </tr>
</table>
<br/>
<table border="1">
    <tr>
        <td>Lesson</td>
        <td>LPoint</td>
        <td>Comments</td>
    </tr>
    <%
        for (int i = 0; i < studentScore.getScores().size(); i++) {
    %>
    <tr>
        <td><%=studentScore.getCourses().get(i).getCourseName()%>
        </td>
        <td><%=studentScore.getScores().get(i).getPoint()%>
        </td>
        <td><a href="/managerSystem/coursesController?method=checkComm&cid=<%=studentScore.getScores().get(i).getCid()%>&sid=<%=studentScore.getScores().get(i).getSid()%>">My Comments</a></td>
    </tr>
    <%
        }
    %>
</table>
<br/>
TotalScore:<%=result%>
<%
    if (result < 50) {
%>
<p style="color: red;font-size: 12px">This student will not be promoted to third grade</p>
<%
} else if (result >= 50) {
%>
<p style="color: green;font-size: 12px">This student will be promoted to third grade</p>
<%
    }
%>
</body>
</html>
