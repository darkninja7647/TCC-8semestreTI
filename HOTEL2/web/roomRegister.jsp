<%-- 
    Document   : cadastroroom
    Created on : 3 de out. de 2024, 17:15:53
    Author     : LIDIA
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/roomRegister.js" defer></script>
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
                flex-direction: column;
            }

            .input_field label {
                font-size: 16px; /* Aumentando o tamanho do texto do label */
                color: #333;
                margin-bottom: 5px;
            }

            .input_field input {
                width: 100%;
                padding: 12px; /* Aumentando o padding dos inputs */
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 16px; /* Aumentando o tamanho da fonte dos inputs */
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
            }
            select {
                width: 200px;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                outline: none;
                transition: border-color 0.3s ease-in-out;
            }

            /* Estilo quando o input está focado */
            select:focus {
                border-color: #007BFF;
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
        <% if (request.getAttribute("error") != null) {%>
        <script>
            alert("<%= request.getAttribute("error")%>");
        </script>
        <% }%>
        <main class="container">
            <form name="f1" action="Controller_Room" method="post" class="login_form" onsubmit="return confirmCapacity()">
                <div class="form_header">
                    <h1 class="titulo">
                        Cadastrar Quartos
                    </h1>
                </div>
                <div class="input_box">
                    <div class="input_field">
                        <div class="input">
                            <div class="input_box">
                                <label class="titulo_box">
                                    Numero do quarto
                                    <div class="input_field">
                                        <input type="text" name ="txtRoomNumber"><BR>
                                    </div>
                                </label>
                            </div>
                            <div class="input_box">
                                <div class="input_field">
                                    <label for="capacidade">Capacidade:</label>
                                    <select id="capacidade" name="txtCapacity">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </select>
                                </div>
                            </div>
                            <div class="input_box">
                                <label class="titulo_box">
                                    Andar:
                                    <div class="input_field">
                                        <input type="number" name ="txtFloor"><BR>
                                    </div>
                                </label>
                            </div>
                            <div class="input_box">
                                <div class="input_field">
                                    <label for="tipoQuarto">Tipo de Quarto:</label>
                                    <select id="tipoQuarto" name="txtTypeRoom">
                                        <option value="Solteiro">Solteiro</option>
                                        <option value="Casado">Casado</option>
                                        <option value="Suite">Suite</option>
                                        <option value="Familia">Família</option>
                                    </select>
                                </div>
                            </div>
                            <div class="input_button">
                                <button name="btnoperacaoroom" value="Register" class="login_button">Cadastrar</button>
                                <button name="btnoperacaoroom" value="ListAll" class="login_button">Voltar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
    </body>
</html>
