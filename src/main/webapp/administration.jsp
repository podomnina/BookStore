<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  function createForm(){
    var struct = document.querySelector("div");
    if (document.querySelector("div")!=null){
      document.body.removeChild(struct);
      document.getElementById("request_text").innerHTML = "";
    }
    struct = document.createElement("div");
    struct.setAttribute("id","struct");
    var p = document.createElement("p");
    var text = document.createTextNode("Введите имя автора");
    p.appendChild(text);
    var name = document.createElement("input");
    name.setAttribute("type","text");
    name.setAttribute("id","author_name");
    p.appendChild(name);
    var ok = document.createElement("input");
    ok.setAttribute("type","submit");
    ok.setAttribute("value","Ok");
    ok.setAttribute("onclick","outputInfo()");
    struct.appendChild(p);
    struct.appendChild(ok);
    document.getElementsByTagName("body")[0].appendChild(struct);
    var text = document.createElement("p");
    text.setAttribute("id","request_text");
    document.body.appendChild(text);
  }
  function outputInfo(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin", true);
    xhttp.onreadystatechange = function() {
        if(xhttp.status == 200) {
          if (xhttp.readyState==4) {
            alert(xhttp.responseText);
            var response = document.createElement("p");
            var text = document.createTextNode(xhttp.responseText);
            response.appendChild(text);
            document.body.appendChild(response);
          }
        }
    }
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("author_name="+encodeURIComponent(document.getElementById("author_name").value));
  }
</script>
    <button onclick="createForm()">Добавить нового автора</button>
    <button onclick="">Удалить автора</button>
    <button onclick="">Обновить данные автора</button>
    <button onclick="">Добавить новую книгу</button>
    <button onclick="">Удалить книгу</button>
    <button onclick="">Обновить данные книги</button>
    <button onclick="">Список заказов</button>

<table>
  <tr>
    <th>Авторы:</th>
  </tr>
  <c:forEach var="author" items="${authors}">
    <tr>
      <td>${author.name}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
