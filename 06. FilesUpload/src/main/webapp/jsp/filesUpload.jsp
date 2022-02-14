<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files Upload</title>
</head>
<body>
<form action="/filesUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="File Upload">
</form>
</body>
</html>
