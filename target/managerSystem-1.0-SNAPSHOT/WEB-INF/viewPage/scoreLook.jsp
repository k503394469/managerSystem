<%@ page import="com.liu.domain.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score</title>
</head>
<body>
<%
    Student studentScore = (Student) request.getAttribute("studentScore");
    int result = (int) request.getAttribute("result");
%>
<jsp:include page="/WEB-INF/publicPage/userInfo.jsp"/>
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
    </tr>
    <%
        for (int i = 0; i < studentScore.getScores().size(); i++) {
    %>
    <tr>
        <td><%=studentScore.getCourses().get(i).getCourseName()%>
        </td>
        <td><%=studentScore.getScores().get(i).getPoint()%>
        </td>
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
