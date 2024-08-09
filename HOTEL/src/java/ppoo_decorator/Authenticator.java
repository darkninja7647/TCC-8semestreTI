/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ppoo_decorator;

import model.Usuario;


/**
 *
 * @author LIDIA
 */
public interface Authenticator {
    void authenticate(Usuario user) throws Exception;
}
