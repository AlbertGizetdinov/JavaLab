<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form method="post" action="/accounts">
    <label for="first_name">Enter your first name:</label>
    <input id="first_name" name="firstName" placeholder="First Name">
    <br>
    <label for="last_name">Enter your last name:</label>
    <input id="last_name" name="lastName" placeholder="Last Name">
    <br>
    <label for="email">Enter your email:</label>
    <input id="email" name="email" placeholder="Email">
    <br>
    <input type="submit" value="Send">
</form>
<h1>Accounts Page</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <#list accounts as acccount>
        <tr>
            <td>${acccount.id}</td>
            <td>${acccount.firstName}</td>
            <td>${acccount.lastName}</td>
        </tr>
    </#list>
</table>
</body>
</html>
