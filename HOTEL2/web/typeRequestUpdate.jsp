<%-- 
    Document   : typerequestalterado
    Created on : 1 de nov. de 2024, 16:55:56
    Author     : darkn
--%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TypeRequest" %>
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

        </style>
    </head>
    <body>
        <%Account account = (Account) request.getSession().getAttribute("account");

             if (account == null) {
        response.sendRedirect("index.html"); // Redireciona para a página inicial se o usuário não estiver logado
    } else if (account.getFirstAccess()== 1) { // Verifica se é o primeiro acesso
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
        </script>
    <main class="container">
            <form name="f1" action="Controller_TypeRequest" method="post" class="login_form">
                <div class="form_header">
                    <h1 class="titulo">
                        Alterar Tipo Solicitação
                    </h1>
                </div>
                <% TypeRequest typeRequest = (TypeRequest) request.getAttribute("typerequest");%>
                <div class="input_box">
                    <div class="input_field">
                        <input type="hidden" name ="txtidtyperequest" value="<%out.print(typeRequest.getIdTypeRequest());%>"><BR>  
                        <div class="input">
                            <div class="input_box">
                                <label for="capacidade" class="titulo_box">
                                    Tipo Solicitação
                                    <div class="input_field">
                                        <input type="text" name ="txttyperequest" value="<%out.print(typeRequest.getTypeRequest());%>" maxlength="100"><BR>
                                    </div>
                                </label>
                            </div>
                            <div class="input_button">
                                <button name="btnoperacaotyperequest" value="ApplyChanges" class="login_button">Alterar</button>
                                <button name="btnoperacaotyperequest" value="FindAll" class="login_button">Voltar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
    </body>
</html>
