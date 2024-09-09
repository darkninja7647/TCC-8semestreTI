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
public class ConsultarByIdCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Customer cli = Customer.getBuilder()
        .comId(Integer.parseInt(request.getParameter("txtid")))
        .constroi();
        CustomerDAO clidao = new CustomerDAO();
        try {
            cli = clidao.consultarById(cli);
            request.setAttribute("customer", cli);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "resultadoconsultarid.jsp";
    }
}
