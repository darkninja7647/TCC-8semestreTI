<%-- 
    Document   : employee.jsp
    Created on : 3 de out. de 2024, 15:14:16
    Author     : LIDIA
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Employee"%>
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
        <%Account account = (Account) request.getSession().getAttribute("account");

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
            function confirmarDelecao(idEmployee) {
                const confirmacao = confirm("Deseja deletar ?");
                if (confirmacao) {
                    // Redireciona para o Controller com o ID da conta
                    window.location.href = 'Controller_Employee?btnoperacaoemployee=Delete&txtIdEmployee=' + idEmployee;
                } else {
                    console.log("Ação de exclusão cancelada pelo usuário.");
                }
            }
        </script>
        <main class="container">
            <div class="login_form">
                <h1>Funcionários</h1>
                <div class="header">
                    <form name="form_cliente" action="Controller_Employee" method="post" >
                        <div class="search-bar">
                            <input type="text" name="txtIdentifierDocumentEmployee" id="barradepesquisa" placeholder="Coloque o documento para a pesquisa">
                            <button type="submit" class="lupa-botao" name="btnoperacaoemployee" value="FindByDocument">
                                <img src="imagem/lupa (2).png" alt="Buscar" class="lupa">
                            </button>
                        </div>

                    </form>
                     <form action="Controller_Account" method="post">
                        <button name="btnoperacaoaccount" value="Logout" class="logout-button">Encerrar Sessão</button>
                    </form>

                </div>
                <div class="table-container">
                    <%List<Employee> lista = (List<Employee>) request.getAttribute("lista");
                        if (lista.size() > 0) {%>               
                    <table class="fl-table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Matricula</th>
                                <th>Setor</th>
                                <th>Situacao</th>
                                <th>Deletar</th>
                                <th>Alterar</th>
                            </tr>
                        </thead>
                        <tr>
                        <tbody>
                            <%for (Employee employee : lista) {
                            %>
                        <td><%out.print(employee.getNameEmployee());%></td>
                        <td><%out.print(employee.getIdentifierDocumentEmployee());%></td>
                        <td><%out.print(employee.getSector());%></td>
                        <td><%out.print(employee.getStatusEmployee());%></td>
                        <td><a href="#" onclick="confirmarDelecao(<%= employee.getIdEmployee()%>)">
                                <img src="imagem/trash.png" alt="Deletar"></a></td>
                        <td><a href="http://localhost:8080/HOTEL2/Controller_Employee?btnoperacaoemployee=Update&txtIdEmployee=<%out.print(employee.getIdEmployee());%>">
                                <img src="imagem/lapis.png" id="imagemedit"></a>
                        </td>
                        </tr><%}%>
                        </tbody>
                    </table><%
                        } else
                            out.println("Consulta sem retorno");
                    %>
                </div>
                <div class="botoes">
                    <button onclick="window.location.href = 'http://localhost:8080/HOTEL2/employeeRegister.jsp'" class="botao botao-primario" ">Cadastrar funcionario</button>
                    <form action="Controller_Customer" method="post">
                        <button name="btnoperacaocustomer" value="ListAll" class="botao botao-primario">Consultar Cliente</button>
                    </form>
                    <form action="Controller_Room" method="post">
                        <button name="btnoperacaoroom" value="ListAll" class="botao botao-primario">Consultar quartos</button>
                    </form>
                    <form action="Controller_Reservation" method="post">
                        <button name="btnoperacaoreservation" value="FindByActive" class="botao botao-primario">Consultar Reservas</button>
                    </form>
                    <form action="Controller_Request" method="post" >
                        <button name="btnoperacaorequest" value="FindByPending" class="botao botao-primario">Consultar Solicitações</button>
                    </form>
                    <% if (account.getAccessLevel().equals("administrador")) { %>
                    <form action="Controller_Account" method="post">
                        <button name="btnoperacaoaccount" value="ListAll" class="botao botao-primario">Consultar Contas do sistema</button>
                    </form>
                    <% }%>

                </div>
            </div>
        </main>
    </body>
</html>