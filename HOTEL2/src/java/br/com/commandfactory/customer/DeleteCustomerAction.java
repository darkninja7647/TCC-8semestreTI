/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.customer;

import dao.CustomerDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Customer;

/**
 *
 * @author LIDIA
 */
public class DeleteCustomerAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = Customer.getBuilder()
        .withCustomerId(Integer.parseInt(request.getParameter("txtId")))
        .build();
        CustomerDAO customerdao = new CustomerDAO();
        try {
              if (customerdao.hasReservations(customer.getIdCustomer())) {
                request.setAttribute("error", "Não é possivel deletar o cliente, pois já teve ou tem uma reserva.");
                request.setAttribute("allCustomer", customerdao.findAll()); // Adiciona a lista de clientes
                return "customerList.jsp"; // Ou exibe mensagem na mesma página
            }

            customerdao.delete(customer);
            List<Customer> allCustomer = customerdao.findAll();
            request.setAttribute("allCustomer", allCustomer);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "customerList.jsp";
    }
}
