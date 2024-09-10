<%-- 
    Document   : reservationalterado
    Created on : 9 de set. de 2024, 13:41:12
    Author     : LIDIA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Room"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>String </h1>
        <form  action="Controller_Room" method="post">
            <table>
                <caption>Tabela</caption>
                <tr>
                    <td> <% Room cli = (Room) request.getAttribute("room");%></td>
                    <td><%out.print(cli.getId());%></td>
                    <td><input type="hidden" name ="txtidRoom" value="<%out.print(cli.getId());%>"></td>
                    <td><input name ="txtcapacity" value="<%out.print(cli.getCapacity());%>"></td>
                    <td><input name ="txtaccessLevel" value="<%out.print(cli.getAccessLevel());%>"></td>
                    <td><input name ="txtstatusRoom" value="<%out.print(cli.getStatusRoom());%>"></td>
                </tr>
                <input type="submit" name="btnoperacaoroom" value="EfetivarAlteracao">
            </table> 
        </form>
    </body>
</html>