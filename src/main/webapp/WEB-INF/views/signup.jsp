<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/user/register" method="post">
        <table>
            <tr>
                <th>ID</th>
                <td><input type="text" name="id" value="${user.id}"></td>
                <td>
                    <c:choose>
                        <c:when test="${errors.isIdBlank}">ID is blank</c:when>
                        <c:when test="${errors.invaildId}">ID is invalid</c:when>
                        <c:when test="${errors.isDuplicatedID}">ID is already exist</c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name" value="${user.name}"></td>
                <td>
                    <c:choose>
                    <c:when test="${errors.isNameBlank}">Name is blank</c:when>
                    <c:when test="${errors.invaildName}">Name is invalid</c:when>
                    <c:when test="${errors.isDuplicatedName}">Name is already exist</c:when>
                </c:choose></td>
            </tr>
            <tr>
                <th>Password</th>
                <td><input type="password" name="password" value="${user.password}"></td>
                <td><c:choose>
                    <c:when test="${errors.isPasswordBlank}">Password is blank</c:when>
                    <c:when test="${errors.invaildPassword}">Password is invalid</c:when>
                </c:choose></td>
            </tr>
            <tr>
                <th>Password confirm</th>
                <td><input type="password" name="confirm" value="${confirm}"></td>
                <td><c:if test="${errors.isNotMatch}">Password is not match</c:if></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="signup"></td>
            </tr>
        </table>
    </form>
</body>
</html>