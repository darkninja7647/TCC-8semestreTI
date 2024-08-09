/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author darkn
 */
public class Cliente {

    private int id;
    private String nome;
    private String sobrenome;
    private String RG;
    private String CPF;
    private Date dataNascimento;
    private String sexo;
    private String telefone;
    private String nacionalidade;
    private String cartaodecredito;

    public String getcartaodecredito() {
        return cartaodecredito;
    }

    public void setcartaodecredito(String cartaodecredito) {
        this.cartaodecredito = cartaodecredito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCartaodecredito() {
        return cartaodecredito;
    }

    public void setCartaodecredito(String cartaodecredito) {
        this.cartaodecredito = cartaodecredito;
    }

    // ----------------------------BUILDER-------------------------------------
    public static ClienteBuilder getBuilder() {
        return new ClienteBuilder();
    }

    public static class ClienteBuilder {

        private Cliente cli = new Cliente();

        public ClienteBuilder comId(int id) {
            cli.id = id;
            return this;
        }

        public ClienteBuilder comNome(String nome) {
            cli.nome = nome;
            return this;
        }

        public ClienteBuilder comSobrenome(String sobrenome) {
            cli.sobrenome = sobrenome;
            return this;
        }

        public ClienteBuilder comRG(String RG) {
            cli.RG = RG;
            return this;
        }

        public ClienteBuilder comCPF(String CPF) {
            cli.CPF = CPF;
            return this;
        }

        public ClienteBuilder comDataNascimento(int dia, int mes, int ano) {           
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            String data = ano + "-" + mes + "-" + dia;
            try {
                cli.dataNascimento = sdf.parse(data);
            } catch (Exception ex) {

            }
            return this;
        }

        public ClienteBuilder comSexo(String sexo) {
            cli.sexo = sexo;
            return this;
        }

        public ClienteBuilder comTelefone(String telefone) {
            cli.telefone = telefone;
            return this;
        }

        public ClienteBuilder comNacionalidade(String nacionalidade) {
            cli.nacionalidade = nacionalidade;
            return this;
        }

        public ClienteBuilder comCartaodecredito(String cartaodecredito) {
            cli.cartaodecredito = cartaodecredito;
            return this;
        }

        public Cliente constroi() {
            return cli;
        }
    }
}
