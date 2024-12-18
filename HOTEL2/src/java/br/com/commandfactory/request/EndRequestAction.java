/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.request;

import dao.RequestDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Request;

/**
 *
 * @author darkn
 */
public class EndRequestAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RequestDAO requestdao = new RequestDAO();
        
        Request requestClass = Request.getBuilder()
                .withIdRequest(Integer.parseInt(request.getParameter("txtidrequest")))
                .build();
        try {
            requestdao.EndRequest(requestClass);
            List<Request> listaRequest = requestdao.findByPendingRequest();
            request.setAttribute("listarequest", listaRequest);
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "request.jsp";
    }
}
