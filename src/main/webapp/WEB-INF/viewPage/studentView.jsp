<%@ page import="java.util.List" %>
<%@ page import="com.liu.domain.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>student</title>
    <script language="JavaScript">
        function cfm() {
            var msg = "Do you really want delete this?"
            if (confirm(msg) == true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
<%
    List<Student> studentList = (List<Student>) request.getAttribute("allStudent");
    int totalPage = (Integer) request.getAttribute("totalPage");
    Integer pageNow = (Integer) request.getAttribute("pageNow");
    String method = (String) request.getAttribute("method");
    String name = (String) request.getAttribute("name");
    String error= (String) request.getAttribute("error");
%>
<jsp:include page="/WEB-INF/publicPage/userInfo.jsp"/>
<form action="/managerSystem/studentController?method=fuzzy&page=1" method="post">
    Name:<input type="text" name="name"/>
    <input type="submit" name="search" value="search">
</form>
<%
    if (studentList == null || studentList.isEmpty()) {
%>
<p style="color: red;font-size: 20px">Data doesn't exist</p>
<%
} else {
%>
<form action="/managerSystem/studentController?method=batchDelete" method="post">
    <table border="1">
        <tr>
            <td>check</td>
            <td>Number</td>
            <td>Name</td>
            <td>Age</td>
            <td>Sexuality</td>
            <td>Address</td>
            <td>Email</td>
            <td>Department</td>
            <td>Update</td>
            <td>Delete</td>
            <td>CheckPoint</td>
        </tr>
        <%
            for (Student student : studentList) {
        %>
        <tr>
            <td><input type="checkbox" name="ids" value="<%=student.getId()%>"></td>
            <td><%=student.getId()%>
            </td>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getAge()%>
            </td>
            <td><%=student.getSex()%>
            </td>
            <td><%=student.getAddress()%>
            </td>
            <td><%=student.getEmail()%>
            </td>
            <td><%=student.getDept()%>
            </td>
            <td><a href="/managerSystem/gotoStudentManager?method=update&id=<%=student.getId()%>">Update</a></td>
            <td><a href="/managerSystem/gotoStudentManager?method=delete&id=<%=student.getId()%>"
                   onclick="return cfm();">Delete</a>
            </td>
            <td><a href="/managerSystem/gotoStudentManager?method=look&id=<%=student.getId()%>">checkScore</a></td>

        </tr>
        <%
            }
        %>
    </table>
    <p style="color: red;font-size: 12px"><%=(error!=null&&!"".equals(error)?error:"")%></p>
    <input type="submit" name="delete" value="BatchDelete" onclick="return cfm();">( Please check the [check]option above)
</form>
<%
    if ("view".equals(method)) {
%>
<a href="/managerSystem/gotoStudentManager?method=view&page=1">FirstPage</a>
<%
    for (int i = 1; i <= totalPage; i++) {
%>
<a href="/managerSystem/gotoStudentManager?method=view&page=<%=i%>"><%=i%>
</a>
<%
    }
%>
<a href="/managerSystem/gotoStudentManager?method=view&page=<%=totalPage%>">LastPage</a><br/>
<%
} else if ("fuzzy".equals(method)) {
%>
<a href="/managerSystem/studentController?method=fuzzy&page=1&name=<%=name%>">FirstPage</a>
<%
    for (int i = 1; i <= totalPage; i++) {
%>
<a href="/managerSystem/studentController?method=fuzzy&page=<%=i%>&name=<%=name%>"><%=i%>
</a>
<%
    }
%>
<a href="/managerSystem/studentController?method=fuzzy&page=<%=totalPage%>&name=<%=name%>">LastPage</a><br/>

<%
        }
    }
%>
<a href="/managerSystem/gotoIndex?method=back">Back to Manager Page</a>
</body>
</html>
