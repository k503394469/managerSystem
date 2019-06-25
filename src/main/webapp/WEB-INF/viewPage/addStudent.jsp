<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<h1>AddStudent</h1>
<form method="post" action="/managerSystem/studentController?method=insertStu">
    Name:<input type="text" name="name"><br/>
    Age:<input type="text" name="age"><br/>
    Sex:<input type="radio" name="sex" value="male">Male <input type="radio" name="sex" value="female"> Female<br/>
    Address:<input type="text" name="address"/><br/>
    Email:<input type="email" name="email"/><br/>
    Dept:<select name="dept">
    <option value="Info">Info</option>
    <option value="Math">Math</option>
    <option value="Mass">Mass</option>
    <option value="Eco">Eco</option>
    </select><br/>
    <input type="submit" value="Insert" name="add">
</form>
</body>
</html>
