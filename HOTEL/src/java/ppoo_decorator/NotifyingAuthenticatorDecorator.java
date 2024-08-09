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
public class NotifyingAuthenticatorDecorator extends AuthenticatorDecorator {

    public NotifyingAuthenticatorDecorator(Authenticator decoratedAuthenticator) {
        super(decoratedAuthenticator);
    }

    @Override
    public void authenticate(Usuario user) {
        try {
            decoratedAuthenticator.authenticate(user);
        } catch (Exception e) {
            if (e.getMessage().contains("E-mail inválido")) {
                System.out.println("Erro de autenticação para o usuário com ID " + user.getId() + ": O e-mail fornecido '" + user.getEmail() + "' é inválido.");
            } else if (e.getMessage().contains("Senha inválida")) {
                System.out.println("Erro de autenticação para o usuário com ID " + user.getId() + ": A senha fornecida não atende aos critérios de segurança.");
            } else {
                System.out.println("Erro de autenticação para o usuário com ID " + user.getId() + ": " + e.getMessage());
            }
        }
    }
}
