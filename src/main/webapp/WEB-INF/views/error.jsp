<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error page</title>
</head>
<body>
    <h1>Page Error!</h1>
    <hr>
    <p>error : ${errorMessage}</p>
    <a href="/">[index page]</a>
</body>
</html>