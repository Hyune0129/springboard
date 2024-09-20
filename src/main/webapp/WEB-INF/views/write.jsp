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
            <c:if test="title">
                <input type="text" id="title" value="title" name="title" required>
            </c:if>
            <c:otherwise>
                <input type="text" id="title" name="title" required>
            </c:otherwise>
        </div>
        <div>
            <label for="content">Context:</label>
            <textarea id="description" name="description" rows="10" required>
                <c:if test="description">${description}</c:if>
            </textarea>
        </div>
        <div>
            <input type="submit" value="write">
            <a href="/">[cancel]</a>
        </div>
    </form>
    <c:if test="errors">
        <div style="color: red;">
            <c:if test="${errors.isTitleBlank}">please insert title</c:if>
            <c:if test="${errors.isDescriptionBlank}">please insert context</c:if>
        </div>
    </c:if>
</body>
</html>
