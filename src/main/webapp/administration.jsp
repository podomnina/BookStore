<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  var Button="";
  function createField(id,text){
    var p = document.createElement("p");
    var text = document.createTextNode(text); //"Input smth.."
    p.appendChild(text);
    var field=document.createElement("input");
    field.setAttribute("type","text");
    field.setAttribute("id",id);
    p.appendChild(field);
    return p;
  }
  function checkFields(form,bookId,authorName,bookName,pages,price,language,authorId){
    if (document.getElementById("book_id")!=null){
      form.removeChild(bookId);
    }
    if (document.getElementById("author_name")!=null){
      form.removeChild(authorName);
    }
    if (document.getElementById("book_name")!=null){
      form.removeChild(bookName);
    }
    if (document.getElementById("pages")!=null){
      form.removeChild(pages);
    }
    if (document.getElementById("price")!=null){
      form.removeChild(price);
    }
    if (document.getElementById("language")!=null){
      form.removeChild(language);
    }
    if (document.getElementById("author_id")!=null){
      form.removeChild(authorId);
    }
  }
  function createAllForms(el){
    var form=document.querySelector("div");
    if (form!=null){
      document.body.removeChild(form);
    }
    form=document.createElement("div");
    document.body.appendChild(form);
    var bookId=createField("book_id","Введите id книги");
    var authorName=createField("author_name","Введите имя автора");
    var bookName=createField("book_name","Введите название книги");
    var pages=createField("pages","Введите количество страниц");
    var price=createField("price","Введите цену");
    var language=createField("language","Введите язык");
    var authorId=createField("author_id","Введите id автора");

    if (el.getAttribute("id")=="createAuthor"){
      Button="createAuthor";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(authorName);
    } else
    if (el.getAttribute("id")=="removeAuthor"){
      Button="removeAuthor";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(authorId);
    } else
    if (el.getAttribute("id")=="updateAuthor"){
      Button="updateAuthor";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(authorId);
      form.appendChild(authorName);
    } else
    if (el.getAttribute("id")=="createBook"){
      Button="createBook";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(bookName);
      form.appendChild(pages);
      form.appendChild(price);
      form.appendChild(language);
      form.appendChild(authorId);
    } else
    if (el.getAttribute("id")=="removeBook"){
      Button="removeBook";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(bookId);
    } else
    if (el.getAttribute("id")=="updateBook"){
      Button="updateBook";
      checkFields(form,bookId,authorName,bookName,pages,price,language,authorId);
      form.appendChild(bookId);
      form.appendChild(bookName);
      form.appendChild(pages);
      form.appendChild(price);
      form.appendChild(language);
      form.appendChild(authorId);
    }
    var button=document.createElement("input");
    button.setAttribute("type","submit");
    button.setAttribute("value","Ok");
    button.setAttribute("onclick","outputInfo()");
    form.appendChild(button);
  }
  function checkNull(val){
    if (val!=null){
      return val.value;
    } else
      return "";
  }
  function outputInfo(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin", true);
    xhttp.onreadystatechange = function() {
      if(xhttp.status == 200) {
        if (xhttp.readyState==4) {
          if (document.getElementById("response")!=null){
            document.body.removeChild(response);
          }
          var response = document.createElement("p");
          response.setAttribute("id","response");
          var text = document.createTextNode(xhttp.responseText);
          response.appendChild(text);
          document.body.appendChild(response);
        }
      }
    }
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("button="+encodeURIComponent(Button)+
            "&book_id="+encodeURIComponent(checkNull (document.getElementById("book_id")))+
            "&book_name="+encodeURIComponent(checkNull (document.getElementById("book_name")))+
            "&pages="+encodeURIComponent(checkNull (document.getElementById("pages")))+
            "&price="+encodeURIComponent(checkNull (document.getElementById("price")))+
            "&language="+encodeURIComponent(checkNull (document.getElementById("language")))+
            "&author_id="+encodeURIComponent(checkNull (document.getElementById("author_id")))+
            "&author_name="+encodeURIComponent(checkNull (document.getElementById("author_name"))));
  }

</script>
    <button id="createAuthor" onclick="createAllForms(this)">Добавить нового автора</button>
    <button id="removeAuthor" onclick="createAllForms(this)">Удалить автора</button>
    <button id="updateAuthor" onclick="createAllForms(this)">Обновить данные автора</button>
    <button id="createBook" onclick="createAllForms(this)">Добавить новую книгу</button>
    <button id="removeBook" onclick="createAllForms(this)">Удалить книгу</button>
    <button id="updateBook" onclick="createAllForms(this)">Обновить данные книги</button>
    <button id="orders" onclick="location.href='http://localhost:8081/orderlist'">Список заказов</button>

<table align="center" cellspacing="20">
  <td valign="top">
  <table border="1">
    <tr>
      <th>ID</th>
      <th>ФИО</th>
    </tr>
    <c:forEach var="author" items="${authors}">
      <tr>
        <td>${author.id}</td>
        <td>${author.name}</td>
      </tr>
    </c:forEach>
  </table>
  </td>
  <td valign="top">
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Название книги</th>
        <th>Количество страниц</th>
        <th>Цена</th>
        <th>Язык</th>
        <th>ID автора</th>
        <th>Автор</th>
      </tr>
      <c:forEach var="book" items="${books}">
        <tr>
          <td>${book.id}</td>
          <td>${book.name}</td>
          <td>${book.pages}</td>
          <td>${book.price}</td>
          <td>${book.language}</td>
          <td>${book.author.id}</td>
          <td>${book.author.name}</td>
        </tr>
      </c:forEach>
    </table>
  </td>
</table>
</body>
</html>
