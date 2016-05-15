<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<button onclick="location.href='http://localhost:8081/main'">На главную страницу</button>
<table align="center">
  <tr>
    <th colspan="2"><h1><c:out value="${book.name}"/></h1></th>
  </tr>
  <tr>
    <td>Автор</td><td><a href="http://localhost:8081/authorinfo?id=${book.author.id}">${book.author.name}</a></td>
  </tr>
  <tr>
    <td>Количество страниц</td><td><c:out value="${book.pages}"/></td>
  </tr>
  <tr>
    <td>Язык</td><td><c:out value="${book.language}"/></td>
  </tr>
  <tr>
    <td>Цена</td><td><c:out value="${book.price}"/></td>
  </tr>
  <tr align="center">
    <td colspan="2"><button onclick="location.href='http://localhost:8081/order?id=${book.id}'">Добавить в корзину</button></td>
  </tr>
</table>
</body>
</html>
