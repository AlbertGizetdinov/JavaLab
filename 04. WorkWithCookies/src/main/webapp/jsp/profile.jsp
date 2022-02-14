<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<table style="margin: 20px 0px 0px 20px">
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${users}" var="users">
        <tr>
            <td>${users.getId()}</td>
            <td>${users.getName()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
