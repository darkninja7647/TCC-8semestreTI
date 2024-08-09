/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ppoo_decorator;

import model.Usuario;

/**
 *
 * @author LIDIA
 */
public class BasicAuthenticator implements Authenticator {
    @Override
    public void authenticate(Usuario user) throws Exception {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new Exception("E-mail inválido.");
        }
        if (user.getSenha() == null || user.getSenha().length() < 6) {
            throw new Exception("Senha inválida.");
        }
        System.out.println("Autenticação bem-sucedida.");
    }
}
