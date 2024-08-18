/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darkn
 */
public class NovocadastroUsuarioAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response){ 
     return "cadastro.jsp";       
    }   
}
