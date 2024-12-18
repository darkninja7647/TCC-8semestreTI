/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.typerequest;

import dao.TypeRequestDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TypeRequest;

/**
 *
 * @author darkn
 */
public class UpdateTypeRequestAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        TypeRequest typerequest = TypeRequest.getBuilder()
                .withIdTypeRequest(Integer.parseInt(request.getParameter("txtidtyperequest")))
                .build();
        TypeRequestDAO typerequestdao = new TypeRequestDAO();
        //tratativa de erro para ver se realiza o findAll by id 
        try {
            if (typerequestdao.hasRequest(typerequest.getIdTypeRequest())){
                request.setAttribute("error", "Não é possivel atualizar,pois existem solicitações associadas.");
                request.setAttribute("lista", typerequestdao.findAll()); // Adiciona a lista de clientes
                return "typeRequest.jsp"; // Ou exibe mensagem na mesma página
            }
            // Consulta o TypeRequest por ID e atribui o resultado ao objeto

            typerequest = typerequestdao.findById(typerequest);
            request.setAttribute("typerequest", typerequest);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "typeRequestUpdate.jsp";
    }
}
