<%-- 
    Document   : lista
    Created on : 27/11/2014, 17:30:34
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
        <h1>CRUD PESSOA </h1>
        <form action="${pageContext.request.contextPath}/pessoa/deletamassa" method="post">

            <table border="1"> 
                <tr> 
                    <td> </td>
                    <td> Nome </td> 
                    <td> Sobrenome </td> 
                    <td> Operação de Deletar </td> 
                    <td> Operação de Editar </td> 
                    <td> Testando o if da view </td>
                    <c:forEach items="${vetPessoa}" var="pessoa">
                    <tr> 
                        <td> <input name="id[]" value="${pessoa.id}" type="checkbox"> </td>
                        <td> ${pessoa.nome} </td> 
                        <td> ${pessoa.sobrenome} </td> 
                        <td> <a href="${pageContext.request.contextPath}/pessoa/deletar/${pessoa.id}"> Deletar </a> </td>
                        <td> <a href="${pageContext.request.contextPath}/pessoa/telaeditar/${pessoa.id}"> Editar </a>  </td>
                        <td> <c:if test="${pessoa.sobrenome  == 'Pereira'}"> Sobrenonome é pereira </c:if> </td>
                        </tr>       
                </c:forEach>
            </table>
            <br>
            <input type="submit">
        </form>    
        <br>
        <a href="${pageContext.request.contextPath}/pessoa/telaadd">Adicionar</a>
        <h3> ${valorSessao} </h3>
    </body>
</html>


