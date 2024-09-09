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
public class Customer {

    private int id;
    private String nome;
    private String RG;
    private String CPF;
    private Date dataNascimento;
    private String sexo;
    private String telefone;


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

    // ----------------------------BUILDER-------------------------------------
    public static CustomerBuilder getBuilder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder {

        private Customer cli = new Customer();

        public CustomerBuilder comId(int id) {
            cli.id = id;
            return this;
        }

        public CustomerBuilder comNome(String nome) {
            cli.nome = nome;
            return this;
        }

        public CustomerBuilder comRG(String RG) {
            cli.RG = RG;
            return this;
        }

        public CustomerBuilder comCPF(String CPF) {
            cli.CPF = CPF;
            return this;
        }

        public CustomerBuilder comDataNascimento(int dia, int mes, int ano) {           
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            String data = ano + "-" + mes + "-" + dia;
            try {
                cli.dataNascimento = sdf.parse(data);
            } catch (Exception ex) {

            }
            return this;
        }

        public CustomerBuilder comSexo(String sexo) {
            cli.sexo = sexo;
            return this;
        }

        public CustomerBuilder comTelefone(String telefone) {
            cli.telefone = telefone;
            return this;
        }

        public Customer constroi() {
            return cli;
        }
    }
}
