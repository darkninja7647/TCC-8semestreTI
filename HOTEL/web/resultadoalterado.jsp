<%@page import="model.Cliente"%>
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
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .titulo{
        color: var(--color-titulo);
    }

    .login_form{
        background-color:var(--color-light-50);
        display:block;
        flex-direction: column;
        padding: 40px 50px;
        border-radius: 16px;
        gap: 20px;
        box-shadow: 5px 5px 8px rgb(61, 61, 61);
    }

    .form_header{
        display: flex;
        justify-content: center;

    }

    .input_button{
        display: flex;
    }

    .login_button{
        background-color: var(--color-botao);
        border-radius: 10px;
        color: cornsilk;
        border-style: none;
        padding: 10px 10px ;
        width: 100%;
    }

    .input_field{
        padding: 4%;
    }


</style>
</head>
<body>
    <main class="container">
        <form name="f1" action="Controller_ClienteV2" method="post" class="login_form">
            <div class="form_header">
                <h1 class="titulo">
                    Alterar
                </h1>
            </div>
            <!-- <% Cliente cli = (Cliente) request.getAttribute("cliente"); %>    -->   
            <div class="input_box">
                <div class="input_field">
                    <input type="hidden" name ="txtid" value="<%out.print(cli.getId());%>"><BR>
                    ID:<%out.print(cli.getId());%><BR>        
                    <div class="input">
                        <div class="input_box">
                            <label for="Nome" class="titulo_box">
                                Nome
                                <div class="input_field">
                                    <input type="text" name ="txtnome" value="<%out.print(cli.getNome());%>"><BR>
                                </div>
                            </label>
                        </div>  

                        <div class="input_box">
                            <label for="sobrenome" class="titulo_box">
                                Sobrenome:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txtsobrenome" value="<%out.print(cli.getSobrenome());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <label for="CPF" class="titulo_box">
                                CPF:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txtCPF" value="<%out.print(cli.getCPF());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <label for="Data Nascimento" class="titulo_box">
                                Data de Nascimento:
                                <div class="input_field">
                                    <input type="date" name ="txtdataNascimento" value="<%out.print(cli.getDataNascimento());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <div class="input_field">
                                Sexo:<select name="txtsexo">
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </div>
                        </div>

                        <div class="input_box">
                            <label for="Telefone" class="titulo_box">
                                Telefone:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txttelefone" value="<%out.print(cli.getTelefone());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <label for="Nacionalidade" class="titulo_box">
                                Nacionalidade:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txtnacionalidade" value="<%out.print(cli.getNacionalidade());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <label for="Rg" class="titulo_box">
                                RG:
                                <div class="input_field">
                                    <i class="fa-solid fa-user"></i>
                                    <input type="text" name ="txtRG" value="<%out.print(cli.getRG());%>"><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_box">
                            <label for="cartaodecredito" class="titulo_box">
                                Cartao de credito:
                                <div class="input_field">
                                    <input type="text" name ="txtcartaodecredito" value="<%out.print(cli.getcartaodecredito());%>"><BR><BR>
                                </div>
                            </label>
                        </div>

                        <div class="input_button">
                            <input type="submit" name="btnoperacao" value="EfetivarAlteracao" class="login_button">
                        </div>                
                    </div>
                </div>
            </div>        
        </form>
    </main>
</body>
</html>