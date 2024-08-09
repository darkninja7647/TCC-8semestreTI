<%-- 
    Document   : Inicio
    Created on : 8 de abr. de 2024, 13:34:44
    Author     : Leonardo Ferreira/Gabriel Boschi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
        <head>
            <title>TODO supply a title</title>
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
                    background-image:url(ovapasta/sasha-kaunas-xEaAoizNFV8-unsplash.jpgN);
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
                    padding: 30px 40px;
                    border-radius: 16px;
                    gap: 5px;
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
                    padding: 4px;
                    ;
                }


                .input_field{
                    padding: 2%
                }

                .input_button{
                    padding-top: 2%;
                }

            </style>  
        </head>
        <body>
            <main class="container">
                <form name="f1" action="Controller_ClienteV2" method="post" class="login_form">
                    <div class="form_header">
                        <h1 class="titulo">
                            Cadastrar
                        </h1>
                    </div>
                    <div class="input_box">
                        <div class="input_field">
                            <div class="input">
                                <div class="input_box">
                                    <label for="ID" class="titulo_box">
                                        Id:
                                        <div class="input_field">
                                            <input type="text" name ="txtid"><BR>
                                        </div>
                                    </label>
                                </div>  
                                <div class="input_box">
                                    <label for="Nome" class="titulo_box">
                                        Nome:
                                        <div class="input_field">
                                            <input type="text" name ="txtnome"><BR>
                                        </div>
                                    </label>
                                </div>  

                                <div class="input_box">
                                    <label for="sobrenome" class="titulo_box">
                                        Sobrenome:
                                        <div class="input_field">
                                            <input type="text" name ="txtsobrenome"><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <label for="CPF" class="titulo_box">
                                        CPF:
                                        <div class="input_field">
                                            <input type="text" name ="txtCPF"><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <label for="Data Nascimento" class="titulo_box">
                                        <div class="input_field">
                                            Data de Nascimento:<select name="txtdia">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                                <option value="13">13</option>
                                                <option value="14">14</option>
                                                <option value="15">15</option>
                                                <option value="16">16</option>
                                                <option value="17">17</option>
                                                <option value="18">18</option>
                                                <option value="19">19</option>
                                                <option value="20">20</option>
                                                <option value="21">21</option>
                                                <option value="22">22</option>
                                                <option value="23">23</option>
                                                <option value="24">24</option>
                                                <option value="25">25</option>
                                                <option value="26">26</option>
                                                <option value="27">27</option>
                                                <option value="28">28</option>
                                                <option value="29">29</option>
                                                <option value="30">30</option>
                                                <option value="31">31</option>                                                                                               
                                            </select>
                                            <select name="txtmes">
                                                <option value="1">Janeiro</option>
                                                <option value="2">Fevereiro</option>
                                                <option value="3">Março</option> 
                                                <option value="4">Abril</option> 
                                                <option value="5">Maio</option> 
                                                <option value="6">Junho</option> 
                                                <option value="7">Julho</option> 
                                                <option value="8">Agosto</option> 
                                                <option value="9">Setembro</option> 
                                                <option value="10">Outubro</option> 
                                                <option value="11">Novembro</option> 
                                                <option value="12">Dezembro</option> 
                                            </select>
                                            <select name="txtano">
                                                <option value="1990">1990</option>
                                                <option value="1991">1991</option> 
                                                <option value="1992">1992</option> 
                                                <option value="1993">1993</option> 
                                                <option value="1994">1994</option> 
                                                <option value="1995">1995</option> 
                                                <option value="1996">1996</option> 
                                                <option value="1997">1997</option> 
                                                <option value="1998">1998</option>
                                                <option value="1999">1999</option> 
                                                <option value="2000">2000</option> 
                                                <option value="2001">2001</option>
                                                <option value="2002">2002</option> 
                                            </select>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <div class="input_field">
                                        Sexo:<select name="txtsexo" >
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
                                            <input type="text" name ="txttelefone" ><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <label for="Nacionalidade" class="titulo_box">
                                        Nacionalidade:
                                        <div class="input_field">
                                            <i class="fa-solid fa-user"></i>
                                            <input type="text" name ="txtnacionalidade"><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <label for="Rg" class="titulo_box">
                                        RG:
                                        <div class="input_field">
                                            <i class="fa-solid fa-user"></i>
                                            <input type="text" name ="txtRG"><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_box">
                                    <label for="cartaodecredito" class="titulo_box">
                                        Cartao de credito:
                                        <div class="input_field">
                                            <input type="text" name ="txtcartaodecredito"><BR>
                                        </div>
                                    </label>
                                </div>

                                <div class="input_button">
                                    <input type="submit" name="btnoperacao" value="Cadastrar" class="login_button">
                                </div>
                            </div>
                        </div>
                    </div>        
                </form>
            </main>
        </body>
    </html>

