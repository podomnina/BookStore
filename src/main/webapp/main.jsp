<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  function createInsUpdAuthorForm()
  {
    var form = document.querySelector("form");
    if (document.querySelector("form")!=null){
      document.body.removeChild(form);
    }

    form = document.createElement("form");
    form.setAttribute("method","POST");
    form.setAttribute("action","");
    var p = document.createElement("p");
    var text = document.createTextNode("Введите имя автора");
    p.appendChild(text);
    var name = document.createElement("input");
    name.setAttribute("type","text");
    name.setAttribute("name","author_name");
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

  }
</script>



    <button onclick="createInsUpdAuthorForm()">Добавить нового автора</button>
    <button onclick="">Удалить автора</button>
    <button onclick="">Обновить данные автора</button>
    <button onclick="">Добавить новую книгу</button>
    <button onclick="">Удалить книгу</button>
    <button onclick="">Обновить данные книги</button>
</body>
</html>
