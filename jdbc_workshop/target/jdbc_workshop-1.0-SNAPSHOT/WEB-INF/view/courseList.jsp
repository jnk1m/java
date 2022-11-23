<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Course List</title>
</head>
<body>
<h2>Course List</h2>

<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Subject</th>
        <th>Teacher</th>
        <th>Creation Time</th>
        <th></th>

    </tr>
    </thead>

    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.getId()}</td>
            <td>${course.getSubject().getName()}</td>
            <td>${course.getTeacher().getName()}</td>
            <td>${course.getCreatedAt()}</td>
            <td colspan="2">
                <button type="button" onclick="location.href='/course/modify?id=${course.getId()}'">Modify</button>
                <button type="button" onclick="location.href='/course/delete?id=${course.getId()}'">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<button type="button" onclick="location.href='/course/registration'">Registration</button>
</body>
</html>
