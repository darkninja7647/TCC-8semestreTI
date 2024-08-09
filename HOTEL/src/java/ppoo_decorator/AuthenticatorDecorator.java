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
public abstract class AuthenticatorDecorator implements Authenticator {
    protected Authenticator decoratedAuthenticator;

    public AuthenticatorDecorator(Authenticator decoratedAuthenticator) {
        this.decoratedAuthenticator = decoratedAuthenticator;
    }

    public void authenticate(Usuario user) throws Exception {
        decoratedAuthenticator.authenticate(user);
    }
}
