/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.typerequest;

import dao.TypeRequestDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TypeRequest;

/**
 *
 * @author darkn
 */
public class FindAllTypeRequestAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
  TypeRequestDAO typerequestdao = new TypeRequestDAO();

        try {
            //botao listar todos
                List<TypeRequest> lista = typerequestdao.findAll();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "typeRequest.jsp";
    }
}
    

