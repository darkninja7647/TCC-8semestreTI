<%@page import="java.time.ZoneId"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="model.Account"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redefinir Senha</title>
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
        max-width: 500px;
    }

    h1 {
        color: var(--color-titulo);
        font-size: 1.5rem;
        text-align: center;
        margin-bottom: 10px;
    }

    .input_field {
        display: flex;
        flex-direction: column;
    }

    .input_field label {
        font-size: 16px;
        color: #333;
        margin-bottom: 10px; /* Aumentando o espaço entre a label e o campo de entrada */
    }

    .input_field input {
        padding: 12px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 16px;
    }

    .input_button {
        display: flex;
        justify-content: center;
        margin-top: 30px;
    }

    .login_button {
        background-color: var(--color-botao);
        border-radius: 10px;
        color: white;
        border: none;
        padding: 15px;
        width: 100%;
        cursor: pointer;
        font-size: 16px;
    }

    .login_button:hover {
        background-color: #5894d6;
    }
</style>


    </head>
    <body>
        <%
            String token = request.getParameter("token");
            if (token != null) {
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.findByToken(token); // Agora retorna um objeto Account

                if (account != null) {
                    LocalDateTime dataExpiracao = account.getExpirationDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();

                    LocalDateTime agora = LocalDateTime.now();

                    if (agora.isBefore(dataExpiracao)) {
        %>
        <main class="container">
            <div class="login_form">
                <h1>Redefinir Senha</h1>
                <form action="Controller_Account" method="POST">   
                    <input type="hidden" name="token" value="<%= token%>">
                    <div class="input_field">
                        <label for="newPassword">Nova Senha:</label>
                        <input type="password" name="newPassword" required>
                    </div>
                    <div class="input_field">
                        <label for="confirmPassword">Confirmar Nova Senha:</label>
                        <input type="password" name="confirmPassword" required>
                    </div>                             
                    <div class="input_button">
                        <button type="submit" name="btnoperacaoaccount" value="ResetPassword" class="login_button">Atualizar a senha</button>
                    </div>
                </form>
            </div>
        </main>
        <%
                    } else {
                        out.println("<div class='container'><p>Token inválido ou expirado.</p></div>");
                    }
                } else {
                    out.println("<div class='container'><p>Token inválido ou expirado.</p></div>");
                }
            } else {
                out.println("<div class='container'><p>Token inválido ou expirado.</p></div>");
            }
        %>
    </body>
</html>