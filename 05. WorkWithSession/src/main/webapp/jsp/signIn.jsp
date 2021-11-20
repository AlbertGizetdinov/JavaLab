<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body style="margin: 20px 0px 0px 20px">
<form method="post" action="/signIn">
    <h1>Войти</h1>
    <label for="email">Введите вашу почту:</label>
    <input id="email" type="email" name="email" placeholder="Почта">
    <br>
    <label for="password">Введите ваш пароль:</label>
    <input id="password" type="password" name="password" placeholder="Пароль">
    <br>
    <br>
    <input type="submit" value="Войти">
</form>
<form action="/signUp">
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>
