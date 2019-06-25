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
%>
<form action="/managerSystem/studentController?method=fuzzy" method="post">
    Name:<input type="text" name="name"/>
    <input type="submit" name="search" value="search">
</form>
<table border="1">
    <tr>
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
        System.out.println(studentList.size());
        for (Student student : studentList) {
    %>
    <tr>
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
        <td><a href="/managerSystem/gotoStudentManager?method=delete&id=<%=student.getId()%>" onclick="return cfm();">Delete</a>
        </td>
        <td><a href="/managerSystem/gotoStudentManager?method=look&id=<%=student.getId()%>">checkScore</a></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/managerSystem/gotoStudentManager?method=view&page=1">FirstPage</a>
<%
    for (int i = 1; i <= totalPage; i++) {
%>
<a href="/managerSystem/gotoStudentManager?method=view&page=<%=i%>"><%=i%>
</a>
<%
    }
%>
<a href="/managerSystem/gotoStudentManager?method=view&page=<%=totalPage%>">LastPage</a>
</body>
</html>
