<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profiles</title>
</head>
<body style="margin: 20px 0px 0px 20px">
<h1>Таблица всех пользователей</h1>
<form action="/profile">
    <input type="submit" value="Профиль">
</form>
<form action="/logout">
    <input type="submit" value="Выйти">
</form>
<table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
        </tr>
    </c:forEach>
</body>
</html>
