<%-- 
    Document   : resultadocadastrar
    Created on : 15/03/2024, 10:16:20
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Resultado CADASTRO</h1>
        <h2><% 
            String message = (String) request.getAttribute("message");
            out.println(message);
            %></h2>
    </body>
</html>
