<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<button onclick="location.href='http://localhost:8081/admin'">На главную страницу</button>

<table border="1" align="center">
  <tr>
    <th>Номер заказа</th>
    <th>ID покупателя</th>
    <th>ФИО покупателя</th>
    <th>Email</th>
    <th>ID книги</th>
    <th>Название книги</th>
    <th>Автор</th>
    <th>Цена</th>
  </tr>
  <c:forEach var="order" items="${orders}">
    <tr>
      <td>${order.id}</td>
      <td>${order.customer.id}</td>
      <td>${order.customer.name}</td>
      <td>${order.customer.email}</td>
      <td>${order.book.id}</td>
      <td>${order.book.name}</td>
      <td>${order.book.author.name}</td>
      <td>${order.book.price}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
