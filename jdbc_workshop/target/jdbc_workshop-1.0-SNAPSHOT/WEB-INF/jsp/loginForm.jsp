<%--
  Created by IntelliJ IDEA.
  User: jieunkim
  Date: 2022/11/21
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <h2>로그인</h2>
</head>
<body>
<form method="post" action="/user/login">
    아이디: <input type="text" name="id" required><br/>
    비밀번호: <input type="password" name="password" required><br/>
    <input type="submit" value="로그인">
</form>

</body>
</html>
