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
public class RegisterTypeRequestAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        TypeRequestDAO typerequestdao = new TypeRequestDAO();

        try {

            //tratativa de erro para ver se realiza o register
            String errorMessage = TypeRequest.validateTypeRequestFields(
                    request.getParameter("txttyperequest")
            );
            if (!errorMessage.isEmpty()) {
                request.setAttribute("error", errorMessage);
                return "typeRequestRegister.jsp"; // Retorna ao formulário para correções
            }
            TypeRequest typerequest = TypeRequest.getBuilder()
                    .withTypeRequest(request.getParameter("txttyperequest"))
                    .build();
            //botao cadastrando
            typerequestdao.register(typerequest);
            List<TypeRequest> lista = typerequestdao.findAll();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "typeRequest.jsp";
    }
}
