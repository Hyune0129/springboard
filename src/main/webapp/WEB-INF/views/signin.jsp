<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <style>
        .error {
            color : red
        }
    </style>
</head>
<body>
<form action="/user/login" method="post">
    <c:if test="${not empty errors and errors['isNotMatch']}">
        <p class="error">not match id or password!</p>
    </c:if>
    <table>
    <tr>
        <th>id</th>
        <td><input type="text" name="id"></td>
        <td>
            <c:if test="${not empty errors and errors['isIdBlank']}"> 
                <p class="error"> please write ID!</p>
            </c:if>
        </td>
    </tr>
        <tr>
            <th>password</th>
            <td>
                <input type="password" name="password">
            </td>
            <td>
                <c:if test="${not empty errors and errors['isPasswordBlank']}">
                    <p class="error"> please write password!</p>
                </c:if>
            </td>
        </tr>
        <tr>
        <td colspan="3">
        <input type="submit" value="login">
        </td>
        </tr>

    </table>
</form>
</body>
</html>