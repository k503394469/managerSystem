<%@ page import="com.liu.domain.Manager" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Mr.c
  Date: 2019/06/28
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Manager managerInfo= (Manager) session.getAttribute("userInfo");
    Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
    String time = sdf.format(date);
    Integer times= (Integer) application.getAttribute("times");
%>
Account:<%=managerInfo.getAccount()%>&nbsp;&nbsp;Level:<%=managerInfo.getLevel()%>
Current date:<%=time%><br/>
This site has been visited <%=times%> times<br/>
<jsp:include page="/WEB-INF/publicPage/studentLoginHead.jsp"/>
