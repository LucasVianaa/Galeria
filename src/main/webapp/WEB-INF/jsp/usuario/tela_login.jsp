<%-- 
    Document   : index
    Created on : 11/06/2014, 16:00:08
    Author     : Valdinei.Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${mensagem}
        <form action="${pageContext.request.contextPath}/usuario/login" method="post">
            Login: <input type="text" name="usuario.email">
            Senha: <input type="password" name="usuario.senha">
            <input type="submit">
        </form>
    </body>
</html>