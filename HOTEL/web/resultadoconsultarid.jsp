<%-- 
    Document   : resultadoconsultarid
    Created on : 25 de mar. de 2024, 11:21:34
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>String </h1>
        <table>
            <tr>
            <caption>Tabela</caption>
            <td> <% Cliente cli = (Cliente) request.getAttribute("cliente");%> </td> <%
            %><td><%out.println(cli.getId());%></td><%
            %><td><%out.println(cli.getNome());%></td><%
            %><td><%out.println(cli.getSobrenome());%></td>
            %><td><%out.println(cli.getRG());%></td><%
            %><td><%out.println(cli.getCPF());%></td><%
            %><td><%out.println(cli.getDataNascimento());%></td><%
            %><td><%out.println(cli.getSexo());%></td><%
            %><td><%out.println(cli.getTelefone());%></td><%
            %><td><%out.println(cli.getNacionalidade());%></td>
            %><td><%out.println(cli.getcartaodecredito());%></td>
            </tr>
        </table>   
</body>
</html>
