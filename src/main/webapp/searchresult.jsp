<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<script>
  function move(el){
    var URL;
    if (el.getAttribute("name")=="book")
      URL="http://localhost:8081/bookinfo?id="+encodeURIComponent(el.getAttribute("id"));
    else
    if (el.getAttribute("name")=="author")
      URL="http://localhost:8081/authorinfo?id="+encodeURIComponent(el.getAttribute("id"));
    el.setAttribute("href",URL);
    alert("URL: "+URL);
  }
</script>

<button onclick="location.href='http://localhost:8081/main'">На главную страницу</button>
<table border="1" align="center">
  <tr>
    <th>Название книги</th>
    <th>Количество страниц</th>
    <th>Цена</th>
    <th>Язык</th>
    <th>Автор</th>
  </tr>
  <c:forEach var="book" items="${books}">
    <tr>
      <td> <a  id="${book.id}" name="book" href="" onclick="move(this)">${book.name}</a></td>
      <td>${book.pages}</td>
      <td>${book.price}</td>
      <td>${book.language}</td>
      <td><a id="${book.author.id}" name="author" href="" onclick="move(this)">${book.author.name}</a></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
