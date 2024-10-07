<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>change Board</title>
    <style>
        .error {
            color : red
        }
    </style>
</head>
<body>
    <form action="/board/update/${board.bid}" method="post">
        <input type="hidden" name="writer" value="${board.writer}">
        <table border="1">
        <tr>
            <td colspan = "2"> <input type="text" name="title" id="" value="${board.title}"> </td>
        </tr>
        <tr>
            <td>${board.writer}</td>
            <td>${board.time}</td>
        </tr>
        <tr>
        <td colspan = "2"><textarea name="description" id="" cols="30">${board.description}</textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="update"></td>
            <td><a href="/board/${board.bid}">[cancel]</a></td>
        </tr>
        
    </table>
    </form>
    <c:if test="${not empty errors}">
        <c:if test="${errors.isTitleBlank}">
            <div class="error">title is Blank</div>
        </c:if>
        <c:if test="${errors.isDescriptionBlank}">
            <div class="error">description is Blank</div>
        </c:if>
    </c:if>
</body>
</html>