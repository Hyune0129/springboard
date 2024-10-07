<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>write post</title>
</head>
<body>
    <form action="/board/create" method="post">
        <div>
            <label for="title">Title:</label>
                <input type="text" value="${board.title}" name="title" required>
        </div>
        <div>
            <label for="content">Context:</label>
            <textarea id="description" name="description" rows="10" required><c:if test="${board.description}">${board.description}</c:if></textarea>
        </div>
        <div>
            <input type="submit" value="write">
            <a href="/">[cancel]</a>
        </div>
    </form>
    <c:if test="${not empty errors}">
        <div style="color: red;">
            <c:if test="${errors.isTitleBlank}">please insert title</c:if>
            <c:if test="${errors.isDescriptionBlank}">please insert context</c:if>
        </div>
    </c:if>
</body>
</html>
