<%-- 
    Document   : reservationalterado
    Created on : 9 de set. de 2024, 13:41:12
    Author     : LIDIA
--%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Room"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                font-size: 22px; /* Mantém o título menor */
                margin-bottom: 15px; /* Reduz o espaçamento abaixo do título */
            }

            .login_form {
                background-color: var(--color-light-50);
                display: block;
                padding: 20px; /* Reduz o espaçamento interno do formulário */
                border-radius: 16px;
                box-shadow: 5px 5px 8px rgb(61, 61, 61);
                max-width: 350px; /* Definindo uma largura menor para deixar mais quadrado */
                width: 100%;
            }

            .input_field {
                margin-bottom: 15px; /* Reduz o espaçamento entre os campos */
            }

            .input_field label {
                display: block;
                font-size: 14px; /* Reduz o tamanho da fonte dos rótulos */
                color: #333;
                margin-bottom: 3px;
            }

            .input_field input,
            .input_field select {
                width: 100%;
                padding: 8px; /* Reduz o padding dos campos */
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 14px; /* Mantém o tamanho da fonte dos campos menor */
            }

            .input_button {
                display: flex;
                justify-content: space-between; /* Distribui os botões com espaço entre eles */
                gap: 1rem; /* Espaçamento entre os botões */
                margin-top: 20px; /* Espaço acima dos botões */
            }

            .login_button {
                background-color: var(--color-botao);
                border-radius: 10px;
                color: cornsilk;
                border: none;
                padding: 10px; /* Ajuste do padding */
                width: 48%; /* Cada botão ocupará quase metade do espaço */
                cursor: pointer;
                font-size: 14px; /* Tamanho da fonte dos botões */
            }

            .login_button:hover {
                background-color: #5894d6; /* Cor de fundo ao passar o mouse */
            }

            /* Ajustes em telas menores */
            @media (max-width: 600px) {
                .input_button {
                    flex-direction: column; /* Empilha os botões em telas menores */
                    align-items: stretch; /* Faz com que os botões ocupem toda a largura */
                }

                .login_button {
                    width: 100%; /* Botões ocupam toda a largura em telas menores */
                    margin-bottom: 10px; /* Espaçamento abaixo de cada botão */
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
        <%

            Account account = (Account) request.getSession().getAttribute("account");

            if (account == null) {
                response.sendRedirect("index.html"); // Redireciona para a página inicial se o usuário não estiver logado
            } else if (account.getFirstAccess() == 1) { // Verifica se é o primeiro acesso
                response.sendRedirect("accountFirstAccess.jsp"); // Redireciona para uma página especial de primeiro acesso
            }
        %>  

        <%String errorMessage = (String) request.getAttribute("error");%>
        <script>
            // Verifica se a mensagem de erro existe
            const errorMessage = "<%= errorMessage != null ? errorMessage : ""%>";
            if (errorMessage) {
                // Exibe a mensagem de erro como pop-up
                alert(errorMessage);
            }
        </script>
        <main class="container">
            <form name="f1" action="Controller_Room" method="post" class="login_form">
                <div class="form_header">
                    <h1 class="titulo">
                        Alterar quartos
                    </h1>
                </div>
                <% Room room = (Room) request.getAttribute("room");%>
                <div class="input_box">
                    <div class="input_field">

                        <input type="hidden" name="txtIdRoom" value="<%= room.getIdRoom()%>"><BR>     

                        <div class="input_box">
                            <label for="capacidade" class="titulo_box">
                                Numero do quarto
                                <div class="input_field">
                                    <input type="text" name ="txtRoomNumber" value="<%out.print(room.getRoomnumber());%>" ><BR>
                                </div>
                            </label>
                        </div>
                        <div class="input_box">
                            <div class="input_field">
                                Capacidade
                            <select id="capacidade" name="txtCapacity">
                                <option value="1" <%= room.getCapacity() == 1 ? "selected" : ""%>>1</option>
                                <option value="2" <%= room.getCapacity() == 2 ? "selected" : ""%>>2</option>
                                <option value="3" <%= room.getCapacity() == 3 ? "selected" : ""%>>3</option>
                                <option value="4" <%= room.getCapacity() == 4 ? "selected" : ""%>>4</option>
                                <option value="5" <%= room.getCapacity() == 5 ? "selected" : ""%>>5</option>
                                <option value="6" <%= room.getCapacity() == 6 ? "selected" : ""%>>6</option>
                            </select>
                            </div>
                        </div>
                        <div class="input_box">
                            <label for="nivel de acesso" class="titulo_box">
                                Andar
                                <div class="input_field">
                                    <input type="number" name ="txtFloor" value="<%out.print(room.getFloor());%>" ><BR>
                                </div>
                            </label>
                        </div>
                        <div class="input_box">
                            <div class="input_field">
                                Tipo do quarto
                                <select id="tipoQuarto" name="txtTypeRoom">
                                    <option value="Solteiro" <%= "Solteiro".equals(room.geTyperoom()) ? "selected" : ""%>>Solteiro</option>
                                    <option value="Casado" <%= "Casado".equals(room.geTyperoom()) ? "selected" : ""%>>Casado</option>
                                    <option value="Suite" <%= "Suite".equals(room.geTyperoom()) ? "selected" : ""%>>Suite</option>
                                    <option value="Familia" <%= "Familia".equals(room.geTyperoom()) ? "selected" : ""%>>Família</option>
                                </select>
                            </div>
                        </div>
                        <div class="input_button">
                            <button name="btnoperacaoroom" value="ApplyChanges" class="login_button">Alterar</button>
                            <button name="btnoperacaoroom" value="ListAll" class="login_button">Voltar</button>
                        </div>
                    </div>
                </div>
                </div>
            </form>
        </main>
    </body>
</html>