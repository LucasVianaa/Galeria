<%-- 
    Document   : upload
    Created on : 03/04/2015, 22:02:28
    Author     : iapereira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>${mensagem}</h2>
                <h2>${sessao}</h2>
        <form action="${pageContext.request.contextPath}/index/upload/" enctype="multipart/form-data" method="post">
            Arquivo: <input type="file" name="file" accept="image/*"/> <input type="submit"/>
        </form>
    </body>
</html>
