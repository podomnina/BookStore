<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
<script>
  function xmlHTTPRequest()
  {
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function takeResponse()
    {
      if (xmlhttp.readyState==4 && xmlhttp.status==200)
      {
        var response = xmlhttp.responseText;
        return location.href='http://localhost:8081/orderinfo?id='+response;
      }
    }
    xmlhttp.open("POST","order",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send('name='+encodeURIComponent(document.getElementById("name").value)+
    "&email="+encodeURIComponent(document.getElementById("email").value));
  }
</script>
<div>
  <p>Введите фамилию, имя и отчество <input id="name" type="text"></p>
  <p>Введите email <input id="email" type="text"></p>
  <p><input id="button" type="submit" value="Ok" onclick="xmlHTTPRequest()"></p>
</div>


</body>
</html>
