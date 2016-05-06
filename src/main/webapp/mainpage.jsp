<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<header>
    <h1 align="center">Книжный интернет-магазин</h1>
</header>

<button onclick="location.href='http://localhost:8081/search'">Поиск</button>

<table border="1">
    <tr>
        <th>Название книги</th>
        <th>Количество страниц</th>
        <th>Цена</th>
        <th>Язык</th>
        <th>Автор</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td> <a href="http://localhost:8081/admin">${book.name}</a></td>
            <td>${book.pages}</td>
            <td>${book.price}</td>
            <td>${book.language}</td>
            <td>${book.author.name}</td>
        </tr>
    </c:forEach>
</table>


<footer >&#169; 2016</footer>
</body>
</html>