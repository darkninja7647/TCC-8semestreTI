<%-- 
    Document   : roomTest
    Created on : 9 de nov. de 2024, 11:41:31
    Author     : LIDIA
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Room"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/room.css">
        <script src="js/room.js"></script>
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
            
        </script>
        <div class="parent">
            <!-- Container Principal dos Quartos -->
            <div class="div1">
                <div class="room-grid">
                    <%List<Room> allRoom = (List<Room>) request.getAttribute("allRoom");
                        if (allRoom.size() > 0) {%>
                    <%for (Room room : allRoom) {%>
                    <button class="room-button" data-status1="<%out.print(room.getStatusRoom());%>" data-status2="<%out.print(room.geTyperoom());%>">
                        <img src="imagem/Quarto-<%out.print(room.getStatusRoom());%>.png" id="<%out.print(room.getStatusRoom());%>"
                             onclick="selecionarQuarto(this, '<%out.print(room.getIdRoom());%>')">
                        <span class="room-number"><%out.print(room.getRoomnumber());%></span>
                    </button>
                    <%}%>
                    <% } else { %>
                    <p>Consulta sem retorno</p>
                    <% }%>
                </div>
            </div>
            <!-- Filtro -->
            <div class="filtro">
                <h2>Filtro</h2>
                <label class="checkbox-label"><input type="checkbox" id="ocupado-checkbox" onchange="aplicarFiltro()"><img src="imagem/Quarto-Ocupado.png">Ocupado</label>
                <label class="checkbox-label"><input type="checkbox" id="livre-checkbox" onchange="aplicarFiltro()"><img src="imagem/Quarto-Livre.png">Livre</label>
                <label class="checkbox-label"><input type="checkbox" id="governanca-checkbox" onchange="aplicarFiltro()"><img src="imagem/Quarto-Governança.png">Governança</label>
                <label class="checkbox-label"><input type="checkbox" id="manutencao-checkbox" onchange="aplicarFiltro()"><img src="imagem/Quarto-Manutenção.png">Manutenção</label>

                <label class="checkbox-label"><input type="checkbox" id="single-checkbox" onchange="aplicarFiltro()">Solteiro</label>
                <label class="checkbox-label"><input type="checkbox" id="double-checkbox" onchange="aplicarFiltro()">Casado</label>
                <label class="checkbox-label"><input type="checkbox" id="suite-checkbox" onchange="aplicarFiltro()">Suite</label>
                <label class="checkbox-label"><input type="checkbox" id="family-checkbox" onchange="aplicarFiltro()">Familia</label>

            </div>
            <!-- Funções -->
            <div class="funcoes">
                <h2>Funções</h2>
                <button id="cadastrar-btn" onclick="window.location.href = 'roomRegister.jsp'">Cadastrar</button>
                <button id="alterar-btn" onclick="alterarQuarto()" disabled>Alterar</button>
                <button id="deletar-btn" onclick="deletarQuarto(event)" disabled>Deletar</button>
            </div>
            <!-- Telas -->
            <div class="telas">
                <h2>Telas</h2>

                <form action="Controller_Customer" method="post">
                    <button name="btnoperacaocustomer" value="ListAll" >Consultar Cliente</button>
                </form>
                <form action="Controller_Request" method="post">
                    <button name="btnoperacaorequest" value="FindByPending">Consultar Solicitações</button>
                </form>
                <form action="Controller_Reservation" method="post">
                    <button name="btnoperacaoreservation" value="FindByActive">Consultar Reservas</button>
                </form>
                
                <form action="Controller_Account" method="post">
                    <button name="btnoperacaoaccount" value="Logout">Encerrar Sessão</button>
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
    </body>
</html>