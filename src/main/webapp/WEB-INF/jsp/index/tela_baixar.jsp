<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Tag Example</title>
    </head>
    <body>   
        ${mensagem} 
        <br>
        Valor Sessão: ${sessao}
        <br> 
        <table border="1"> 
            <tr> 
                <td> Imagem </td> 
                <td> Arquivo </td> 
                <td> Baixar </td> 
                <td> Remover </td> 
            </tr>
            <c:forEach items="${files}" var="file">
                <tr> 
                    <td> <img src="${pageContext.request.contextPath}/index/baixar/${file.name}"> </td> 
                    <td> ${file.name} </td> 
                    <td> <a href="${pageContext.request.contextPath}/index/baixar/${file.name}" download="${file.name}"> Baixar </a> </td>
                    <td><a href="${pageContext.request.contextPath}/index/remover/${file.name}"> Remover </a> </td>
                </tr>

            </c:forEach>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/index/tela_upload/">Adicionar Arquivo</a>
        <br>
        <br>
        <br>
        <br>
        <br>
        <hr>
        <br>
        <!--
                <a href="${linkTo[IndexController].baixar('sandero.jpg')}">baixar - imagem -linkto</a> 
        
                <br>        
        
                <a href="${linkTo[IndexController].baixar('apres.pdf')}">baixar - pdf - linkto</a> 
        
                <br>
        
                <a href="${pageContext.request.contextPath}/index/baixar/sandero.jpg"> Baixar - imagem </a>              
        
                <br>
        -->

        <a href="${pageContext.request.contextPath}/usuario/logout"> Logout</a>              

        <br>

    </body>
</html>