<%-- 
    Document   : Resultado
    Created on : 20/03/2024, 21:02:45
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            :root {
                --color-light-50:#f8fafc;
                --color-botao:#70a6e8;
                --color-fundo:#757575;
                --color-titulo:#2c81c6;
                --color-enunciado:#014ba0;
                --color-contrasteenunciado:#1466c3;


            }

            body{
                font-family: Helvetica;
                background-color: var(--color-fundo);
            }

            h2{
                text-align: center;
                font-size: 18px;
                text-transform: uppercase;
                letter-spacing: 1px;
                color: white;
                padding: 30px 0;
            }
            /* PESQUISA */

            /* TABELA */
            .fl-table {

                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                border: none;
                margin:auto;
                white-space: nowrap;
                background-color: white;
            }

            .fl-table td, .fl-table th {
                text-align: center;
                padding: 21px;
            }

            .fl-table td {
                border-right: 1px solid #f8f8f8;
                font-size: 18px;
            }

            .fl-table thead th {
                color: #ffffff;
                background: var(--color-titulo);
            }


            .fl-table thead th:nth-child(odd) {
                color: #ffffff;
                background:var(--color-botao);
            }

            /* BOTOES */
            .botoes{
                border-radius: 5px;
                margin: auto;
                max-width: 50%;
                white-space: nowrap;
                background-color:var(--color-botao);
            }
            .botao{
                border-radius: 5px;
                width: 100%;
                height: 50px;


            }
            .botao-primario{
                background-color:var(--color-botao);
                border: 1px solid #fff;
            }
            .botao-primario:hover{
                background-color:var(--color-titulo);
                color: #fff;
            }

            #barradepesquisa{
                padding: 1rem;
                width: 90%;
                border-radius: 15px;
            }

            #botaopesquisa{
                white-space: nowrap;
                border-radius: 5px;
                background-color: var(--color-botao);
                border-radius: 10px;
                color: black;
                border-style: none;
            }
            #botaopesquisa:hover{
                white-space: nowrap;
                border-radius: 5px;
                background-color: var(--color-titulo);
                border-radius: 10px;
                color: cornsilk;
                border-style: none;
            }
            .nav{
                display:flex;
            }

            #imagemedit{
                width: 30%;
                height: 30%;

            }

            #imagemlixo{
                width: 30%;
                height: 30%;
            }
            
            
                .fl-table tr:nth-child(even) {
                    background: #eeeeee;
                        }
        </style>

    </head>
    <body>
        <main class="container">
            <form name="form_cliente" action="Controller_ClienteV2" method="post" class="nav">
                <input type="text" name ="txtid" id="barradepesquisa" placeholder="coloque o ID para a pesquisa"><BR>
                <input type="submit" name="btnoperacao" value="ConsultarById" id="botaopesquisa">
            </form>
            <%List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
            if (lista.size() > 0) {%>
            <table class="fl-table">  
                <tr>
                <thead>
                <th>ID</th>
                <th>Nome</th>
                <th>sobrenome</th>
                <th>RG</th>
                <th>CPF</th>
                <th>idade</th>
                <th>sexo</th>
                <th>telefone</th>
                <th>nacionalidade</th>     
                <th>Cartão de credito</th>
                <th>Deletar</th> 
                <th>Alterar</th> 

                </thead>

                </tr>
                <tr><h2>Tabela</h2><%for (Cliente cli : lista) {
                %><td><%out.println(cli.getId());%></td>
                <td><%out.println(cli.getNome());%></td>
                <td><%out.println(cli.getSobrenome());%></td>
                <td><%out.println(cli.getRG());%></td>
                <td><%out.println(cli.getCPF());%></td>
                <td><%out.println(cli.getDataNascimento());%></td>
                <td><%out.println(cli.getSexo());%></td>
                <td><%out.println(cli.getTelefone());%></td>
                <td><%out.println(cli.getNacionalidade());%></td>
                <td><%out.println(cli.getcartaodecredito());%></td>
                <td><a href="http://localhost:8080/HOTEL2/Controller_ClienteV2?btnoperacao=Deletar&txtid=<%out.print(cli.getId());%>">
                        <img src="imagem/trash.png" id="imagemlixo"></a>
                </td>
                <td><a href="http://localhost:8080/HOTEL2/Controller_ClienteV2?btnoperacao=Alterar&txtid=<%out.print(cli.getId());%>">
                        <img src="imagem/lapis.png" id="imagemedit"></a>
                </td>
                </tr><%}%>
            </table><%
                } else
                    out.println("Consulta sem retorno");
            %>
            <div class="botoes">
                <button onclick="window.location.href = 'http://localhost:8080/HOTEL2/cadastrocliente.jsp'" class="botao botao-primario ">CADASTRAR</button>
            </div>
        </main>

    </body>
</html>
