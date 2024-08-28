<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${board.title}</title>
</head>
<body>
    <table border="1">
        <tr>
            <td colspan = "2">${board.title}</td>
        </tr>
        <tr>
            <td>${board.writer}</td>
            <td>${board.time}</td>
        </tr>
        <tr>
        <td colspan = "2"><textarea name="" id="" cols="30" readonly>${board.description}</textarea></td>
        </tr>
    </table>
</body>
</html>