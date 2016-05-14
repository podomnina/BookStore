<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>

<table align="center">
  <tr>
    <th colspan="2"><p>Ваш заказ №<c:out value="${order.id}"/> был успешно оформлен!</p></th>
  </tr>
  <tr>
    <td colspan="2">Контактная информация:</td>
  </tr>
  <tr>
    <td>ФИО:</td><td>${order.customer.name}</td>
  </tr>
  <tr>
    <td>Email</td><td><c:out value="${order.customer.email}"/></td>
  </tr>
  <tr>
    <td colspan="2">Содержание заказа:</td>
  </tr>
  <tr>
    <td>Название книги</td><td><c:out value="${order.book.name}"/></td>
  </tr>
  <tr>
    <td>Автор</td><td><c:out value="${order.book.author.name}"/></td>
  </tr>
  <tr>
    <td>Цена</td><td><c:out value="${order.book.price}"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><button onclick="location.href='http://localhost:8081/main'">На главную страницу</button></td>
  </tr>
</table>
</body>
</html>
