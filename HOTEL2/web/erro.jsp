<%-- 
    Document   : Erro
    Created on : 8 de abr. de 2024, 13:34:27
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            :root {
                --color-light-50:#f8fafc;
                --color-botao:#70a6e8;
                --color-fundo:#D3D3D3;
                --color-titulo:#2c81c6;

            }
            *{
                margin: 0;
                box-sizing: border-box;
            }


            .container{
                height: 100vh;
                width: 100%;
                background: var(--color-fundo);
                background-image:url(imagem/swimming-pool-2128578_1280.jpg);
                background-repeat: no-repeat;
                background-size:cover;
                background-position:center;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .login_form{
                background-color:var(--color-light-50);
                display:flex;
                flex-direction: column;
                padding: 40px 50px;
                border-radius: 16px;
                gap: 20px;
                box-shadow: 5px 5px 8px rgb(61, 61, 61);
               
            }

            .input_box{
                display:flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                gap:1rem;
            }
            h2{
                color: var(--color-titulo);
                font-size:20px ;
            }

            form
            .input_field{
                padding: 1.5rem;
            }

            .botao{
                width: 100%;
                background-color: var(--color-botao);
                border-radius: 10px;
                color: cornsilk;
                border-style: none;
                padding: 10px 10px ;
            }
        </style>
    </head>
    <body>
        <main class="containcer">
            <div class="login_form">
                <div class="input_box">
                    <h2>                                       
                        Deu um erro no login Tente novamente
                    </h2>
                    <div class="input_field">
                        <input type="submit" onclick="window.location.href = 'index.html'" class="botao">                       
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
