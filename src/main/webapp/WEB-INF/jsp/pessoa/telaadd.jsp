<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- para utilizar o foreach e o if e etc.. -->
<html>
    <head>
        <title> Tela Adicionar </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/codigo.js"></script>
    </head>
    <body>
        <h1> Formulário de Adição </h1>    
   <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
    </c:forEach>   
    <form action="add" method="post">
        <fieldset>
            <legend>Pessoa</legend>
            <label>Nome:</label>
            <input type="text" name="pessoa.nome"/>
            <span class="error">${errors.from('pessoa.nome')}</span>
            <label>Sobrenome:</label>
            <input type="text" name="pessoa.sobrenome"/>
            <button type="submit">Enviar</button>
        </fieldset>
    </form>
    <a href="${pageContext.request.contextPath}/pessoa/listar/"> Voltar</a>
</body> 
</html>