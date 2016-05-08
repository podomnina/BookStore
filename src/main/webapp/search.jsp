<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  function moveToAnotherPage(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/search", true);
    //alert("opened!");
    if (document.getElementById("authorRB").checked) {
      var authorRB = document.getElementById("authorRB").value;
    }
    if (document.getElementById("bookRB").checked) {
      var bookRB = document.getElementById("bookRB").value;
    }

    xhttp.onreadystatechange = function() {
      //alert("status: "+xhttp.status+"state: "+xhttp.readyState);
      if(xhttp.status == 200) {
        if (xhttp.readyState==4) {
          //alert(xhttp.responseText);
          var resp="По запросу '"+document.getElementById("text").value+"' ничего не найдено";
          if (xhttp.responseText != resp) {
            var URL;
            if (bookRB == "book")
              URL = "http://localhost:8081/bookinfo?name=" + encodeURIComponent(document.getElementById("text").value);
            else if (authorRB == "author")
              URL = "http://localhost:8081/authorinfo?name=" + encodeURIComponent(document.getElementById("text").value);
            document.getElementById("button").setAttribute("href", URL);
            //alert("URL: " + URL);
            return location.href = URL;
          } else {
            var p=document.getElementById("responseText");
            p.innerHTML = resp;
          }
        }
      }
    }
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("text="+encodeURIComponent(document.getElementById("text").value)+
            "&authorRB="+encodeURIComponent(authorRB)+
            "&bookRB="+encodeURIComponent(bookRB));
  }
</script>
<div >
  <p><input id="authorRB" name="radio" type="radio" value="author" checked>Поиск по автору</p>
  <p><input id="bookRB" name="radio" type="radio" value="book">Поиск по названию книги</p>
  <p><input id="text" type="text"></p>
  <p><input id="button" type="submit" value="Ok" href="" onclick="moveToAnotherPage()"></p>
</div>

<p id="responseText"></p>

</body>
</html>
