<%-- 
    Document   : cadastro
    Created on : 7 de abr. de 2024, 18:03:59
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de novos usuários</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
 :root { 
   --color-light-50:#f8fafc;
   --color-botao:#70a6e8;
   --color-fundo:#D3D3D3;
   --color-titulo:#2c81c6;

}

body{

}

*{
  margin: 0;
  box-sizing: border-box;
}
        

.container{
  height: 100vh;
  width: 100%;
  background: var(--color-fundo);
  background-image:url(imagem/pexels-thorsten-technoman-338504.jpg);
  background-repeat: no-repeat;
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


h1{ 
   color:var(--color-titulo); 
   margin-right: 110px;
   margin-left: 100px;
}

.login_button{
    background-color: var(--color-botao);
    width: 100%;
    border-radius: 10px;
    color: cornsilk;
    border-style: none;
    padding: 10px 10px ;
}



input{
    width: 100%;
    border-radius: 5px;
    padding: 5px;;
}
#inputemail{
    background: url("Novapasta/email.png") no-repeat left / 1.3em;
    background-repeat: no-repeat;
    padding-left: 1.5em;   
    background-position: 3px;
}

#inputsenha{
    background: url("Novapasta/senha.png") no-repeat left / 1.3em;
    background-repeat: no-repeat;
    padding-left: 1.5em;   
    background-position: 3px;
}
.input_field{
    gap: 10px;
    display: flex;
}

    </style>
    </head>
      <body>
    <main class="container">
           <form id="forml" action="Controller_Usuario" method="Post" class="login_form">
                <div class="form_header">
                    <h1 class="titulo">
                       Crie sua conta 
                    </h1>
                </div>
                    <div class="input_box">
                        <label for=" emai">
                            <div class="input_field">
                                <input type="text"  name="txtlogin" placeholder=" insira seu e-mail" id="inputemail">
                            </div>
                        </label>
                    </div>

                    <div class="input_box">
                        <label for="password">
                            <div class="input_field">
                                <input type="text" name="txtsenha" placeholder=" crie sua senha" id="inputsenha" >
                            </div>
                        </label>
                    </div>
                <input type="submit" name="btnop" class="login_button" value="Cadastrar">
           </form>
    </main>
</body>
</html>