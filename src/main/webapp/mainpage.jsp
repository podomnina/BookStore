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

<button onclick="location.href='http://localhost:8081/search'">Поиск</button>

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


<footer valign="bottom">BookStore &#169; 2016</footer>
</body>
</html>