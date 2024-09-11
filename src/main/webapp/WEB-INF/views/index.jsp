<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>board index</title>
  </head>
  <body>
    <c:choose>
      <c:when test="${user != null}">
        WelCome ${user.name}!
        <a href="/user/logout">[logout]</a>
        <a href="/board/write">[write]</a>
        <hr>
      </c:when>
      <c:otherwise>
        <a href="/user/login">[login]</a>
        <a href="/user/register">[signup]</a>
      </c:otherwise>
    </c:choose>
    <table border="1">
      <tr>
        <th>title</th>
        <th>time</th>
        <th>writer</th>
      </tr>
        <c:forEach items="${boards}" var="board">
          <tr>
            <td><a href="/board/${board.bid}">${board.title}</td>
            <td>${board.time}</td>
            <td>${board.writer}</td>
          </tr>
        </c:forEach>
    </table>
  </body>
</html>
