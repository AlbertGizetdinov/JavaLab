<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body style="margin: 20px 0px 0px 20px">
<div>
    <h1>Профиль</h1>
    <h3>Имя: ${user.getFirstName()}</h3>
    <h3>Фамилия: ${user.getLastName()}</h3>
    <h3>Почта: ${user.getEmail()}</h3>
</div>
<form action="/logout">
    <input type="submit" value="Выйти">
</form>
<form action="/allProfiles">
    <input type="submit" value="Таблица всех пользователей">
</form>
</body>
</html>
