<%-- 
    Document   : customer
    Created on : 20/03/2024, 21:02:45
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Customer"%>
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
                width: 100%;
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
                box-sizing: border-box;
                height: 40px; /* Garantindo que o campo tenha a mesma altura do botão */
                border-right: none; /* Remover a borda direita */
            }



            .lupa-botao {
                background-color: white;
                border: 1px solid #ccc;
                border-left: none; /* Remover a borda esquerda do botão */
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
                background-color: var(--color-botao);
                padding: 10px;
                border-radius: 10px;
                color: white;
                border: none;
                cursor: pointer;
                height: 40px;
            }

            .logout-button:hover {
                background-color: var(--color-titulo);
            }

        </style>
    </head>
    <body>
        <% Account account = (Account) request.getSession().getAttribute("account");

            if (account == null) {
                response.sendRedirect("index.html"); // Redireciona para a página inicial se o usuário não estiver logado
            } else if (account.getFirstAccess() == 1) { // Verifica se é o primeiro acesso
                response.sendRedirect("accountFirstAccess.jsp"); // Redireciona para uma página especial de primeiro acesso
            }%>      

        <%String errorMessage = (String) request.getAttribute("error");%>
        <script>
            // Verifica se a mensagem de erro existe
            const errorMessage = "<%= errorMessage != null ? errorMessage : ""%>";
            if (errorMessage) {
                // Exibe a mensagem de erro como pop-up
                alert(errorMessage);
            }
            function confirmarDelecao(IdCustomer) {
                const confirmacao = confirm("Deseja deletar ?");
                if (confirmacao) {
                    // Redireciona para o Controller com o ID da conta
                    window.location.href = 'Controller_Customer?btnoperacaocustomer=Delete&txtId=' + IdCustomer;
                } else {
                    console.log("Ação de exclusão cancelada pelo usuário.");
                }
            }
        </script>
        <main class="container">
            <div class="login_form">
                <h1>Clientes</h1>
                <div class="header">
                    <form name="form_cliente" action="Controller_Customer" method="post" >

                        <div class="search-bar">
                            <form name="form_cliente" action="Controller_Customer" method="post" >
                                <input type="text" name="txtIdentifierDocument" id="barradepesquisa" placeholder="Coloque o documento para pesquisa">
                                <button type="submit" class="lupa-botao" name="btnoperacaocustomer" value="FindByDocument">
                                    <img src="imagem/lupa (2).png" alt="Buscar" class="lupa">
                                </button>

                        </div>
                    </form>

                    <form action="Controller_Account" method="post">
                        <button name="btnoperacaoaccount" value="Logout" class="logout-button">Encerrar Sessão</button>
                    </form>
                </div>
                <div class="table-container">
                    <% List<Customer> allCustomer = (List<Customer>) request.getAttribute("allCustomer");
                        if (allCustomer != null && allCustomer.size() > 0) { %>
                    <table class="fl-table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Documento</th>
                                <th>Data de nascimento</th>
                                <th>Sexo</th>
                                <th>Telefone</th>
                                <th>Deletar</th>
                                <th>Alterar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Customer customer : allCustomer) {%>
                            <tr>
                                <td><%out.print(customer.getNameCustomer());%></td>
                                <td><%out.print(customer.getIdentifierDocument());%></td>
                                <td><%out.print(customer.formatBirthDate());%></td>
                                <td><%out.print(customer.getGender());%></td>
                                <td><%out.print(customer.getPhoneNumber());%></td>

                                <td><a href="#" onclick="confirmarDelecao(<%= customer.getIdCustomer()%>)">
                                        <img src="imagem/trash.png" alt="Deletar"></a>
                                </td>
                                <td><a href="Controller_Customer?btnoperacaocustomer=Update&txtId=<%= customer.getIdCustomer()%>">
                                        <img src="imagem/lapis.png" alt="Alterar"></a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <% } else { %>
                    <p>Consulta sem retorno</p>
                    <% }%>
                </div>
                <div class="botoes">
                    <button onclick="window.location.href = 'customerRegister.jsp'" class="botao botao-primario">Cadastrar cliente</button>
                    <form action="Controller_Room" method="post">
                        <button name="btnoperacaoroom" value="ListAll" class="botao botao-primario">Consultar quartos</button>
                    </form>
                    <form action="Controller_Reservation" method="post">
                        <button name="btnoperacaoreservation" value="FindByActive" class="botao botao-primario">Consultar Reservas</button>
                    </form>
                    <form action="Controller_Request" method="post">
                        <button name="btnoperacaorequest" value="FindByPending" class="botao botao-primario">Consultar solicitação</button>
                    </form>
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