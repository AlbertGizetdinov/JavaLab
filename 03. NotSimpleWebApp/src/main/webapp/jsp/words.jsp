<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${words}" var="words">
    <h1>${words.key}</h1>
    <table border='1'>
        <tr>
            <th>Word</th>
            <th>Count</th>
        </tr>
    <c:forEach items="${words.value}" var="values">
            <tr>
                <td>${values.key}</td>
                <td>${values.value}</td>
            </tr>
    </c:forEach>
    </table>
</c:forEach>
</body>
</html>
