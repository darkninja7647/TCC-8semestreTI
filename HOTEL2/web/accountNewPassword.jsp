<%-- 
    Document   : accountesqueciminhasenha
    Created on : 21 de out. de 2024, 05:50:04
    Author     : darkn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            .input_field input,
            .input_field select {
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
                width: 100%;
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

        </style>
    </head>
    <body>
        <main class="container">
            <form id="forml" action="Controller_Account" method="Post" class="login_form">
                <div class="form_header">
                    <h1 class="titulo">
                        Digite seu e-mail cadastrado 
                    </h1>
                </div>
                <div class="input_box">
                    <label for="Nome" class="titulo_box">
                        E-mail:
                        <div class="input_field">
                            <input type="text" name ="txtEmail" maxlength="100"><BR>
                        </div>
                    </label>
                </div>                                

                <button name="btnoperacaoaccount" value="RecoverPassword" class="login_button">Enviar o e-mail</button>
            </form>
        </main>
    </body>
</html>
