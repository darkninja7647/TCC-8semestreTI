/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.usuario;

import dao.ClienteDAO;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Usuario;

/**
 *
 * @author darkn
 */
public class LoginUsuarioAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
      
                   UsuarioDAO udao = new UsuarioDAO();    
                Usuario u =  Usuario.getBuilder()
                .comemail(request.getParameter("txtlogin"))
                .comsenha(request.getParameter("txtsenha"))
                .constroi();

             
                try {
                    if (udao.Login(u)) {
                        Cliente c = new Cliente();
                        ClienteDAO cdao = new ClienteDAO();
                        List<Cliente> lista = cdao.consultar();
                        request.setAttribute("lista", lista);
                        return "inicio.jsp";
                    } else {
                        return "erro.jsp";
                    }
  } catch (ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Erro SQL: " + ex.getMessage());
                }
        return "inicio.jsp";
      
        }
}
