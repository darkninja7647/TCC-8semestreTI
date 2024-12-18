/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.customer;

import dao.CustomerDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author LIDIA
 */
public class FindByDocumentCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        CustomerDAO customerdao = new CustomerDAO();
         try {
        Customer customer = Customer.getBuilder()
        .withIdentifierDocument(request.getParameter("txtIdentifierDocument"))
        .build();
        Customer ifEmployeeExist = customerdao.findByDocument(customer);
            if (ifEmployeeExist == null){
                request.setAttribute("error", "Este cliente não existe no sistema.");
                List<Customer> allCustomer  = customerdao.findAll();
            request.setAttribute("allCustomer", allCustomer);
                return "customerList.jsp";
            }
            customer = customerdao.findByDocument(customer);
            request.setAttribute("customer", customer);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "customerFindByDocument.jsp";
    }
}
