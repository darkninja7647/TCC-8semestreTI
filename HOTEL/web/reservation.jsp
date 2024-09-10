<%-- 
    Document   : reservation
    Created on : 3 de set. de 2024, 17:51:16
    Author     : LIDIA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Room"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <body>
        <main class="container">
            <%List<Room> lista = (List<Room>) request.getAttribute("lista");
                if (lista.size() > 0) {%>               
            <table class="fl-table">
                <tr>
                <thead>
                <th>ID</th>
                <th>Capacidade</th>
                <th>Nível de acesso</th>
                <th>Status</th>
                </thead>
                </tr>
                <tr><h2>Tabela</h2><%for (Room room : lista) {
                %>
                <td><%out.print(room.getId());%></td>
                <td><%out.print(room.getCapacity());%></td>
                <td><%out.print(room.getAccessLevel());%></td>
                <td><%out.print(room.getStatusRoom());%></td>
                <td><a href="http://localhost:8080/HOTEL2/Controller_Room?btnoperacaoroom=Deletar&txtid=<%out.print(room.getId());%>">DELETAR</a></td>
                <td><a href="http://localhost:8080/HOTEL2/Controller_Room?btnoperacaoroom=Alterar&txtid=<%out.print(room.getId());%>">ALTERAR</a></td>
                </tr><%}%>
            </table><%
                } else
                    out.println("Consulta sem retorno");
            %>
        </main>
    </body>
</body>
</html>
