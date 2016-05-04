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
  function createInsUpdAuthorForm()
  {
    var form = document.querySelector("form");
    if (document.querySelector("form")!=null){
      document.body.removeChild(form);
    }

    form = document.createElement("form");
    //form.setAttribute("method","POST");
    //form.setAttribute("onsubmit","outputInfo()");
    form.setAttribute("action","");
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
    form.appendChild(p);
    form.appendChild(ok);
    document.getElementsByTagName("body")[0].appendChild(form);
  }
  function outputInfo(){
    var xhttp = new XMLHttpRequest();

    //document.getElementById("request_text").innerHTML = document.getElementById("author_name").value + "ETO ONO!!!";
    xhttp.open("POST", "/", true);
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
</body>
</html>
