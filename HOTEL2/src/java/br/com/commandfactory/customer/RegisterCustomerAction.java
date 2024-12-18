/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.customer;

import dao.CustomerDAO;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import model.Customer;
import static sun.security.krb5.KrbException.errorMessage;

/**
 *
 * @author LIDIA
 */
public class RegisterCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        // Cria uma instância do DAO para manipular os dados de cliente
        CustomerDAO customerdao = new CustomerDAO();
        StringBuilder errorMessage = new StringBuilder();
        Customer customer;

        try {
            // Constrói o objeto Customer a partir dos parâmetros da requisição
            customer = Customer.getBuilder()
                    .withCustomerName(request.getParameter("txtNameCustomer"))
                    .withIdentifierDocument(request.getParameter("txtIdentifierDocument"))
                    .withBirthDate(request.getParameter("txtBirthDate"))
                    .withGender(request.getParameter("txtGender"))
                    .withPhoneNumber(request.getParameter("txtPhoneNumber"))
                    .build();

            // Valida os dados do cliente utilizando o método validateCustomer
            String validationErrors = Customer.validateCustomer(
                    request.getParameter("txtNameCustomer"),
                    request.getParameter("txtIdentifierDocument"),
                    request.getParameter("txtBirthDate"),
                    request.getParameter("txtGender"),
                    request.getParameter("txtPhoneNumber")
            );

            if (!validationErrors.isEmpty()) {
                // Se houver erros de validação, os exibe na página de atualização
                request.setAttribute("error", validationErrors);
                return "customerRegister.jsp";
            }

            // Verifica se o documento do cliente já está em uso por outro cliente
            Customer existente = customerdao.findByDocument(customer);
            if (existente != null && (existente.getIdCustomer() != customer.getIdCustomer())) {
                // Documento já está em uso por outro cliente
                request.setAttribute("error", "O Documento já está em uso.");
                return "customerRegister.jsp";  // Volta para a página de registro com erro
            }

            // Atualiza o cliente no banco de dados
            customerdao.register(customer);

            // Busca todos os clientes para exibir na lista após a atualização
            List<Customer> allCustomer = customerdao.findAll();
            request.setAttribute("allCustomer", allCustomer);

            // Define o cliente atualizado no request para garantir que chegue na JSP
            request.setAttribute("customer", customer);
            System.out.println("Cliente atualizado: " + customer.getIdCustomer() + ", Nome: " + customer.getNameCustomer());

        } catch (Exception e) {
            // Caso ocorra algum erro em qualquer uma das etapas (validação, verificação de documento ou atualização)
            request.setAttribute("error", "Erro ao processar o cliente: " + e.getMessage());
            return "customerRegister.jsp";  // Retorna para a página de atualização em caso de erro
        }

        // Redireciona para a lista de clientes após a atualização
        return "customerList.jsp";
    }
}
