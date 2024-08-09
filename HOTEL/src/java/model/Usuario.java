/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author darkn
 */
public class Usuario {
    private int id;
    private String email;
    private String senha;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    } 
    
        public static Usuario.UsuarioBuilder getBuilder() {
        return new Usuario.UsuarioBuilder();
    }

    
        public static class UsuarioBuilder {

        private Usuario u = new Usuario();
        
                public UsuarioBuilder comemail(String email) {
            u.email = email;
            return this;
                }
            
            public UsuarioBuilder comsenha(String senha) {
            u.senha = senha;
            return this;
            }
            
             public Usuario constroi() {
            return u;
        }
        }
}
