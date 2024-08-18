/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.usuario;

import dao.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author darkn
 */
public class CadastrarUsuarioAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
                 Usuario u =  Usuario.getBuilder()
                .comemail(request.getParameter("txtlogin"))
                .comsenha(request.getParameter("txtsenha"))
                .constroi();


                UsuarioDAO udao = new UsuarioDAO();

                try {
                    udao.Cadastrar(u);
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Erro: " + ex.getMessage());
                }
return "index.html";            
    
    }
}
