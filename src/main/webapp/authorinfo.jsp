<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<h1 align="center"><c:out value="${author.name}"/></h1>
<table cellpadding="20" align="center" border="1">
  <tr>
    <th>Название книги</th>
    <th>Количество страниц</th>
    <th>Цена</th>
    <th>Язык</th>
  </tr>
  <c:forEach var="book" items="${books}">
    <tr>
      <td> <a href="http://localhost:8081/bookinfo?id=${book.id}">${book.name}</a></td>
      <td>${book.pages}</td>
      <td>${book.price}</td>
      <td>${book.language}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>