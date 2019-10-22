<%@ page import="com.liu.domain.Student" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Mr.k
  Date: 2019/7/8
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Student studentInfo= (Student) session.getAttribute("userInfo");
    Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
    String time = sdf.format(date);
    Integer times= (Integer) application.getAttribute("times");
%>
Account:<%=studentInfo.getId()%>&nbsp;&nbsp;Level:<%=studentInfo.getName()%>
Current date:<%=time%><br/>
This site has been visited <%=times%> times<br/>
