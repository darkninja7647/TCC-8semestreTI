<%-- 
    Document   : request
    Created on : 7 de out. de 2024, 13:32:03
    Author     : LIDIA
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Request"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
               :root {
                --color-light-50: #f8fafc;
                --color-botao: #70a6e8;
                --color-fundo: #D3D3D3;
                --color-titulo: #2c81c6;
            }

            body {
                font-family: Helvetica, sans-serif;
                background-color: var(--color-fundo);
                margin: 0;
            }

            .container {
                height: 100vh;
                width: 100%;
                background-color: var(--color-fundo);
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
            }

            .login_form {
                background-color: var(--color-light-50);
                display: flex;
                flex-direction: column;
                padding: 40px 60px;
                border-radius: 16px;
                gap: 20px;
                box-shadow: 5px 5px 8px rgb(61, 61, 61);
                width: 90%;
                max-width: 1200px;
            }

            h1 {
                color: var(--color-titulo);
                font-size: 1.5rem;
                text-align: center;
                margin-bottom: 10px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                position: relative;
                padding: 10px;
            }



            .search-bar {
                display: flex;
                align-items: center;
                justify-content: flex-end;
                width: 100%;
            }

            #barradepesquisa {
                padding: 10px;
                width: 100%;
                max-width: 200px;
                border-radius: 15px 0 0 15px;
                background-color: white;
                border: 1px solid #ccc;
                border-right: none;
                box-sizing: border-box;
                height: 40px; /* Garantindo que o campo tenha a mesma altura do botão */
            }

            .lupa-botao {
                background-color: white;
                border: 1px solid #ccc;
                padding: 0 15px;
                border-radius: 0 15px 15px 0;
                cursor: pointer;
                height: 40px; /* Mesma altura que o campo de pesquisa */
                display: flex;
                align-items: center;
                justify-content: center;
                box-sizing: border-box;
            }

            .lupa {
                width: 20px;
                height: 20px;
            }

            /* Tabela responsiva */
            .table-container {
                width: 100%;
                overflow-x: auto;
            }

            .fl-table {
                width: 100%;
                margin-top: 20px;
                background-color: var(--color-light-50);
                border-collapse: collapse;
                box-shadow: 5px 5px 8px rgb(61, 61, 61);
                border: none;
                table-layout: fixed;
                border-radius: 10px;
                overflow: hidden;
            }

            .fl-table th, .fl-table td {
                padding: 10px;
                text-align: center;
                border: none;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
            }

            .fl-table thead th {
                background-color: var(--color-titulo);
                color: white;
            }

            .fl-table tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .fl-table img {
                width: 20px;
                height: 20px;
            }

            .botoes {
                display: flex;
                justify-content: center;
                margin-top: 20px;
                gap: 1rem;
            }

            .botao-primario {
                background-color: var(--color-botao);
                padding: 10px;
                border-radius: 10px;
                color: white;
                border: none;
                cursor: pointer;
                display: flex;
                justify-content: center;
                gap: 10px;
            }

            .botao-primario:hover {
                background-color: var(--color-titulo);
            }

            .logout-button {
                position: absolute;
                top: 10px;
                right: 10px;
                padding: 10px 20px;
                border: none;
                cursor: pointer;

            }

            .logout-button:hover {
                background-color: var(--color-titulo);
            }
            
            
        </style>
    </head>
    <body>
        <%String emailMessage = (String) request.getAttribute("message");%>
        <script>
            // Verifica se a mensagem de erro existe
            const messageMessage = "<%= emailMessage != null ? emailMessage : ""%>";
            if (messageMessage) {
                // Exibe a mensagem de erro como pop-up
                alert(messageMessage);
            }
            function confirmarDelecao(idRequest) {
                const confirmacao = confirm("Deseja encerrar ?");
                if (confirmacao) {
                    // Redireciona para o Controller com o ID da conta
                    window.location.href = 'Controller_Request?btnoperacaorequest=End&txtidrequest=' + idRequest;
                } else {
                    console.log("Ação de exclusão cancelada pelo usuário.");
                }
            }
        </script>
        <%Account account = (Account) request.getSession().getAttribute("account");

            if (account == null) {
                response.sendRedirect("index.html"); // Redireciona para a página inicial se o usuário não estiver logado
            } else if (account.getFirstAccess() == 1) { // Verifica se é o primeiro acesso
                response.sendRedirect("accountFirstAccess.jsp"); // Redireciona para uma página especial de primeiro acesso
            }%>  
        <main class="container">
            <div class="login_form">
                <h1>Solicitações</h1>
                <div class="header">
                    <form action="Controller_Account" method="post">
                        <button name="btnoperacaoaccount" value="Logout" class="logout-button botao-primario">Encerrar Sessão</button>
                    </form>
                </div>
                <%List<Request> listarequest = (List<Request>) request.getAttribute("listarequest");
                    if (listarequest.size() > 0) {%>               
                <table class="fl-table">
                    <thead>
                        <tr>
                            <th>Numero do quarto</th>
                            <th>Remetente</th>
                            <th>Destinatário</th>
                            <th>tipo Solicitação</th>
                            <th>data inicio</th>
                            <th>descricao</th>
                            <th>encerrar</th>
                        </tr>
                    </thead>
                    <tr><%for (Request requestlist : listarequest) {
                        %>
                        <td><%out.print(requestlist.getRoom().getRoomnumber());%></td>
                        <td><%out.print(requestlist.getCollaboratorRequest().getNameEmployee());%></td>
                        <td><%out.print(requestlist.getCollaboratorResponse().getNameEmployee());%></td>
                        <td><%out.print(requestlist.getTypeRequest().getTypeRequest());%></td>
                        <td><%out.print(requestlist.formatStartDate());%></td>
                        <td  class="description-column"><%out.print(requestlist.getDescriptionRequest());%></td>
                        <td>
                            <a href="#" onclick="confirmarDelecao(<%= requestlist.getIdRequest()%>)">
                                <img src="imagem/1828427.png" id="imagemedit"></a>
                        </td>
                    </tr><%}%>
                </table><%
                    } else
                        out.println("Consulta sem retorno");
                %>
                <div class="botoes">
                    <button onclick="window.location.href = 'requestRegister.jsp'" class="botao botao-primario">Cadastrar Solicitação</button>
                    <form action="Controller_TypeRequest" method="post">
                        <button name="btnoperacaotyperequest" value="FindAll" class="botao botao-primario">Consultar Tipo Solicitação</button>
                    </form>
                    <% if (account.getAccessLevel().equals("recepcao") || account.getAccessLevel().equals("administrador")) { %>
                    <form action="Controller_Customer" method="post">
                        <button name="btnoperacaocustomer" value="ListAll" class="botao botao-primario">Consultar Cliente</button>
                    </form>
                    <form action="Controller_Room" method="post">
                        <button name="btnoperacaoroom" value="ListAll" class="botao botao-primario">Consultar quartos</button>
                    </form>
                    <form action="Controller_Reservation" method="post">
                        <button name="btnoperacaoreservation" value="FindByActive" class="botao botao-primario">Consultar Reservas</button>
                    </form>
                    <% }%>
                    <% if (account.getAccessLevel().equals("administrador")) { %>
                    <form action="Controller_Employee" method="post">
                        <button name="btnoperacaoemployee" value="ListAll" class="botao botao-primario">Consultar funcionario</button>
                    </form>
                    <form action="Controller_Account" method="post">
                        <button name="btnoperacaoaccount" value="ListAll" class="botao botao-primario">Consultar Contas do sistema</button>
                    </form>
                    <% }%>
                </div>
            </div>
        </main>
    </body>
</html>
