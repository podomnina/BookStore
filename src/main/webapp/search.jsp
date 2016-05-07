<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  function outputInfo(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/search", true);
    if (document.getElementById("authorRB").checked){
      var authorRB=document.getElementById("authorRB").value;
    }
    if (document.getElementById("bookRB").checked){
      var bookRB=document.getElementById("bookRB").value;
    }
    alert("text:"+document.getElementById("text").value);
    alert("authorRB: "+document.getElementById("authorRB").value);
    alert("bookRB: "+document.getElementById("bookRB").value);


    xhttp.onreadystatechange = function() {
      alert("status: "+xhttp.status+"state: "+xhttp.readyState);
      if(xhttp.status == 200) {
        if (xhttp.readyState==4) {
          alert(xhttp.responseText);
          //var response = document.createElement("p");
          //var text = document.createTextNode(xhttp.responseText);
          //response.appendChild(text);
          //document.body.appendChild(response);
          createTable();
        }
      }
    }


    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("text="+encodeURIComponent(document.getElementById("text").value)+
            "&authorRB="+encodeURIComponent(authorRB)+
            "&bookRB="+encodeURIComponent(bookRB));
  }
  function createTable(){
    var table = document.querySelector("table");
    if (document.querySelector("table")!=null){
      document.body.removeChild(table);
    }
    table = document.createElement("table");
    var columns=["Название книги","Количество страниц","Цена","Язык","Автор"];
    var tr = document.createElement("tr");
    for (var i = 0; i < columns.length; i++) {
      var th = document.createElement("th");
      th.setAttribute("id","th");
      txt = document.createTextNode(columns[i]);
      th.innerText = txt.textContent;
      tr.appendChild(th);
    }
    table.appendChild(tr);

    var foreach = document.createElement("c:forEach");
    foreach.setAttribute("var","book");
    foreach.setAttribute("items","${books}");
    var tr = document.createElement("tr"), td = document.createElement("td"), a = document.createElement("a");
    a.setAttribute("href","");
    txt = document.createTextNode("${book.name}");
    a.innerText = txt.textContent;
    td.appendChild(a);
    tr.appendChild(td);
    columns = ["${book.pages}","${book.price}","${book.language}","${book.author.name}"];
    for (var i = 0; i < columns.length; i++){
      td = document.createElement("td");
      txt = document.createTextNode(columns[i]);
      td.innerText = txt.textContent;
      tr.appendChild(td);
    }
    table.appendChild(tr);
    document.body.appendChild(foreach);
    document.body.appendChild(table);
  }


</script>
<div >
  <p><input id="authorRB" name="radio" type="radio" value="author" checked>Поиск по автору</p>
  <p><input id="bookRB" name="radio" type="radio" value="book">Поиск по названию книги</p>
  <p><input id="text" type="text"></p>
  <p><input type="submit" value="Ok" onclick="outputInfo()"></p>
</div>



</body>
</html>
