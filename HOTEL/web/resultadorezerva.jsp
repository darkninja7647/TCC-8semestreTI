<%-- 
    Document   : Resultado
    Created on : 20/03/2024, 21:02:45
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Comodo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>String </h1>
        <%List<Comodo> lista = (List<Comodo>) request.getAttribute("lista");%>
        <%if (lista.size() > 0) {
        %><table>
            <tr>
            <caption>Tabela</caption><%for (Comodo com : lista) {
                %><td><%out.println(com.getNumeroQuarto());%></td>
                <td><%out.println(com.getAndar());%></td>
                <td><%out.println(com.isOcupado());%></td>
                <td><%out.println(com.getId_Cliente());%></td>
            </tr><%}%>
        </table><%
        } else
            out.println("Consulta sem retorno");
    %>
    </body>
</html>
