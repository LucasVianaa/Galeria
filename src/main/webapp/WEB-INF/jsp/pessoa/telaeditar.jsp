<%-- 
    Document   : telaeditar
    Created on : 03/12/2014, 17:25:19
    Author     : iapereira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- para utilizar o foreach e o if e etc.. -->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <h1> Formulário de Edição </h1>
    <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
    </c:forEach>
    <form action="${pageContext.request.contextPath}/pessoa/editar" method="post">
        <fieldset>
            <legend>Pessoa</legend>

            <label>Nome:</label>
            <input type="text" name="pessoa.nome" value="${pessoa.nome}"/>


            <label>Sobrenome:</label>
            <input type="text" name="pessoa.sobrenome" value="${pessoa.sobrenome}"/>

            <input type="hidden" name="pessoa.id" value="${pessoa.id}">

            <button type="submit">Enviar</button>
        </fieldset>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/pessoa/listar/"> Voltar</a>
    <h3> ${valorSessao} </h3>
</body> 
</html>
