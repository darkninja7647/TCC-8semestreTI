<%-- 
    Document   : Inicio
    Created on : 8 de abr. de 2024, 13:34:44
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>
            <title>TODO supply a title</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                :root {
                    --color-light-50: #f8fafc;
                    --color-botao: #70a6e8;
                    --color-fundo: #D3D3D3;
                    --color-titulo: #2c81c6;
                }

                * {
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }

                body {
                    background-color: var(--color-fundo);
                    font-family: Arial, sans-serif;
                }

                .container {
                    height: 100vh;
                    width: 100%;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }

                .titulo {
                    color: var(--color-titulo);
                    text-align: center;
                    font-size: 28px; /* Aumentando o tamanho do título */
                    margin-bottom: 20px; /* Mais espaço abaixo do título */
                }

                .login_form {
                    background-color: var(--color-light-50);
                    padding: 30px; /* Aumentando o padding interno */
                    border-radius: 16px;
                    box-shadow: 5px 5px 10px rgb(61, 61, 61); /* Aumentando a sombra */
                    max-width: 500px; /* Aumentando a largura do formulário */
                    width: 100%;
                }

                .input_field {
                    margin-bottom: 20px; /* Aumentando o espaçamento entre os campos */
                    display: flex;
                    align-items: center;
                }

                .input_field label {
                    font-size: 16px; /* Aumentando o tamanho do texto do label */
                    color: #333;
                    margin-bottom: 5px;
                }

                .input_field input,
                .input_field select {
                    width: 100%;
                    padding: 12px; /* Aumentando o padding dos inputs */
                    border-radius: 8px;
                    border: 1px solid #ccc;
                    font-size: 16px; /* Aumentando o tamanho da fonte dos inputs */
                }

                .input_field select {
                    width: auto;
                    display: inline-block;
                    margin-right: 10px;
                    padding: 12px;
                    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
                    border-radius: 8px;
                }

                .input_button {
                    display: flex;
                    justify-content: space-between;
                    gap: 1rem;
                    margin-top: 30px; /* Aumentando o espaçamento antes dos botões */
                }

                .login_button {
                    background-color: var(--color-botao);
                    border-radius: 10px;
                    color: cornsilk;
                    border: none;
                    padding: 15px; /* Aumentando o tamanho do botão */
                    width: 48%;
                    cursor: pointer;
                    font-size: 16px;
                }

                .login_button:hover {
                    background-color: #5894d6;
                }

                @media (max-width: 600px) {
                    .input_button {
                        flex-direction: column;
                    }

                    .login_button {
                        width: 100%;
                        margin-bottom: 10px;
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
                </script>
                <%

                    Account account = (Account) request.getSession().getAttribute("account");

                    if (account == null) {
                        response.sendRedirect("index.html"); // Redireciona para a página inicial se o usuário não estiver logado
                    } else if (account.getFirstAccess() == 1) { // Verifica se é o primeiro acesso
                        response.sendRedirect("accountFirstAccess.jsp"); // Redireciona para uma página especial de primeiro acesso
                    }
                %>  
                <% if (request.getAttribute("error") != null) {%>
                <script>
                    alert("<%= request.getAttribute("error")%>");
                </script>
                <% }%>
                <main class="container">
                    <form name="f1" action="Controller_Employee" method="post" class="login_form">
                        <div class="form_header">
                            <h1 class="titulo">
                                Cadastrar Funcionario
                            </h1>
                        </div>
                        <div class="input_box">
                            <label for="Nome" class="titulo_box">
                                Nome:
                                <div class="input_field">
                                    <input type="text" name ="txtNameEmployee" maxlength="100" ><BR>
                                </div>
                            </label>
                        </div>                                
                        <div class="input_box">
                            <label for="CPF" class="titulo_box">
                                Matricula:
                                <div class="input_field">
                                    <input type="text" name ="txtIdentifierDocumentEmployee" maxlength="20" ><BR>
                                </div>
                            </label>
                        </div>
                        <div class="input_box">
                            <label for="Telefone" class="titulo_box">
                                Setor:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txtSector" maxlength="50" ><BR>
                                </div>
                            </label>
                        </div>
                        <div class="input_box">
                            <div class="input_field">
                                Status:<select name="txtStatusEmployee" >
                                    <option value="Ativo">Ativo</option>
                                    <option value="Desligado">Desligado</option>
                                </select>
                            </div>
                        </div>
                        <div class="input_button">
                            <button name="btnoperacaoemployee" value="Register" class="login_button">Cadastrar</button>
                            <button name="btnoperacaoemployee" value="ListAll" class="login_button">voltar</button>
                        </div>
                    </form>
                </main>
            </body>
        </html>

