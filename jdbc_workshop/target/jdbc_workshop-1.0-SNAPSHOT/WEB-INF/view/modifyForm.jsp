<%--
  Created by IntelliJ IDEA.
  User: jieunkim
  Date: 2022/11/22
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify</title>
</head>
<body>
<form method="post" action="/course/modify">
    Id: <input type="text" name="id" value="${course.getId()}" disabled/><br/>
    Subject: <input type="text" name="subject" value="${course.getSubject().getName()}"/><br/>
    Teacher: <input type="text" name="teacher" value="${course.getTeacher().getName()}"/>
    <input type="submit">
</form>

</body>
</html>
