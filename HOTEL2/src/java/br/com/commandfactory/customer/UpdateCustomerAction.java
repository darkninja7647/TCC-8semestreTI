/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.customer;

import dao.CustomerDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author LIDIA
 */
public class UpdateCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = Customer.getBuilder()
                .withCustomerId(Integer.parseInt(request.getParameter("txtId")))
                .build();
        CustomerDAO customerdao = new CustomerDAO();
        try {
            if (customerdao.hasReservations(customer.getIdCustomer())) {
                request.setAttribute("error", "Não é possivel atualizar o cliente, pois já tem uma reserva.");
                request.setAttribute("allCustomer", customerdao.findAll()); // Adiciona a lista de clientes
                return "customerList.jsp"; // Ou exibe mensagem na mesma página
            }

            customer = customerdao.findById(customer);
            request.setAttribute("customer", customer);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "customerUpdate.jsp";
    }
}
