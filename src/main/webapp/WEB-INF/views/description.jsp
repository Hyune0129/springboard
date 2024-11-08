<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
    <script>
        function deleteConfirm() {
            if(confirm("delete board?")) {
                alert("page deleted");
                window.location.replace("/board/delete/${board.bid}");
            }
        }
    </script>
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
        <tr>
            <td colspan="2"><a href="/">[index]</a></td>
        </tr>
        <c:if test="${isWriter}">
            <tr>
                <td><a href="/board/update/${board.bid}">[update]</a></td>
                <td><input type="button" value="delete" onclick="deleteConfirm()"></td>
            </tr>
        </c:if>
    </table>
</body>
</html>