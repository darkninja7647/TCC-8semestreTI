<%-- 
    Document   : bookingcadastro
    Created on : 9 de out. de 2024, 11:15:39
    Author     : LIDIA
--%>

<%@page import="model.Room"%>
<%@page import="dao.RoomDAO"%>
<%@page import="model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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



            input[type="number"]::-webkit-outer-spin-button,
            input[type="number"]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            .dropdowninput {
                display: flex;
                align-items: center;
                justify-content: flex-end;
                width: 100%;
            }

            .dropdowninput input {
                flex: 1;
                padding: 12px;
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 16px;
                margin-right: 10px; /* Espaçamento entre o input e a lupa */
            }

            .dropdownicon {
                width: 24px;
                height: 24px;
                cursor: pointer;
                position: absolute;
                right: 10px; /* Alinha a lupa ao lado direito do input */
            }

            /* Estilização da lista suspensa */
            .dropdownlist {
                position: absolute;
                top: 100%;
                left: 0;
                width: 100%;
                background-color: var(--color-light-50); /* Cor de fundo mais clara */
                border: 1px solid #ccc;
                border-radius: 8px;
                max-height: 150px;
                overflow-y: auto;
                display: none;
                z-index: 1000;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                margin-top: 5px; /* Espaçamento entre o input e a lista */
            }

            /* Estilo dos itens da lista */
            .dropdownlist li {
                padding: 12px;
                font-size: 16px;
                border-bottom: 1px solid #f0f0f0;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .dropdownlist li:last-child {
                border-bottom: none;
            }

            .dropdown-list li:hover {
                background-color: #f0f0f0;
                color: #007BFF; /* Cor para destacar ao passar o mouse */
            }
        }
        .input_field.dropdown {
            display: flex;
            align-items: center;
            position: relative;
        }

        .dropdowninput {
            display: flex;
            align-items: center;
            width: 100%;
            background-color: #fff; /* Cor de fundo igual ao input */
            border: 1px solid #ccc;
            border-radius: 8px;
            overflow: hidden; /* Garante que a lupa fique dentro do contorno */
        }

        .dropdowninput input {
            flex: 1;
            padding: 12px;
            border: none; /* Remove a borda do input */
            font-size: 16px;
            outline: none; /* Remove o contorno ao focar */
        }

        .dropdownicon {
            padding: 8px; /* Ajuste o padding para centralizar a imagem */
            background-color: #fff;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;

        }

        .dropdownicon img {
            width: 20px;
            height: 20px;
            object-fit: contain;
        }

        .dropdownlist {
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            background-color: var(--color-light-50);
            border: 1px solid #ccc;
            border-radius: 8px;
            max-height: 150px;
            overflow-y: auto;
            display: none;
            z-index: 1000;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 5px;
        }

        .dropdownlist li {
            padding: 15px; /* Mais espaço interno */
            font-size: 16px;
            margin-bottom: 5px; /* Espaçamento entre as linhas */
            border-bottom: 1px solid #f0f0f0;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .dropdownlist li:last-child {
            margin-bottom: 0; /* Remove o espaço para o último item */
            border-bottom: none;
        }

        .dropdownlist li:hover {
            background-color: #f0f0f0;
            color: #007BFF;
        }

    </style>  
</head>
<body>
    <% String errorMessage = (String) request.getAttribute("error");%>
    <% CustomerDAO custdao = new CustomerDAO();
        List<Customer> custlist = custdao.CustomerDocument();
        RoomDAO roomdao = new RoomDAO();
        List<Room> roomlist = roomdao.RoomNumbersAndTypes();
    %>
    <script>
        // Verifica se a mensagem de erro existe
        const errorMessage = "<%= errorMessage != null ? errorMessage : ""%>";
        if (errorMessage) {
            // Exibe a mensagem de erro como pop-up
            alert(errorMessage);
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
        <form name="f1" action="Controller_Reservation" method="post" class="login_form">
            <div class="form_header">
                <h1 class="titulo">
                    Cadastrar Reserva
                </h1>
            </div>

            <div class="input_box">
                <label for="identifier-document" class="titulo_box">Coloque o Documento do Cliente:</label>
                <div class="input_field dropdown">
                    <div class="dropdowninput">
                        <input type="text" id="identifier-document-input" name="txtdocumentoidentifiers" placeholder="Selecione um documento">
                        <div class="dropdownicon" onclick="toggleDropdown(this)">
                            <img src="imagem/lupa (2).png" alt="Ícone de Lupa" style="width: 30px; height: auto;">
                        </div>
                    </div>
                    <ul class="dropdownlist">
                        <% for (Customer customer : custlist) {%>
                        <li data-value="<%= customer.getIdentifierDocument()%>">
                            <%= customer.getNameCustomer()%> - <%= customer.getIdentifierDocument()%>
                        </li>
                        <% }%>
                    </ul>
                </div>
            </div>

            <div class="input_box">
                <label for="room-number" class="titulo_box">Coloque o número do quarto:</label>
                <div class="input_field dropdown">
                    <div class="dropdowninput">
                        <input type="text" id="room-number-input" name="txtroomnumber" placeholder="Selecione um quarto">
                        <div class="dropdownicon" onclick="toggleDropdown(this)">
                            <img src="imagem/lupa (2).png" alt="Ícone de Lupa" style="width: 30px; height: auto;">
                        </div>
                    </div>
                    <ul class="dropdownlist">
                        <% for (Room room : roomlist) {%>
                        <li data-value="<%= room.getRoomnumber()%>">
                            <%= room.getRoomnumber()%> - <%= room.geTyperoom()%>
                        </li>
                        <% }%>
                    </ul>
                </div>
            </div>

            <div class="input_box">
                <label for="Data Nascimento" class="titulo_box">
                    <div class="input_field">
                        Data do check in
                        <input type="date" name="txtstartdate">
                    </div>
                </label>
            </div>


            <div class="input_box">
                <label for="Data Nascimento" class="titulo_box">
                    <div class="input_field">
                        Data do check out
                        <input type="date" name="txtenddate">
                    </div>
                </label>
            </div>

            <div class="input_button">
                <button name="btnoperacaoreservation" value="Register" class="login_button">Cadastrar</button>
                <button name="btnoperacaoreservation" value="FindByActive" class="login_button">voltar</button>
            </div>

        </form>
    </main>
    <script src="js/reservationRegister.js"></script>
</body>
</html>