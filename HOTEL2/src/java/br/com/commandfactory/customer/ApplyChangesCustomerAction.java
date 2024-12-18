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
public class ApplyChangesCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        // Cria uma instância do DAO para manipular os dados de cliente
        CustomerDAO customerdao = new CustomerDAO();
        StringBuilder errorMessage = new StringBuilder();
        Customer customer;

        try {

            // Valida os dados do cliente utilizando o método validateCustomer
            String validationErrors = Customer.validateCustomer(
                    request.getParameter("txtNameCustomer"),
                    request.getParameter("txtIdentifierDocument"),
                    request.getParameter("txtBirthDate"),
                    request.getParameter("txtGender"),
                    request.getParameter("txtPhoneNumber")
            );

            // Constrói o objeto Customer a partir dos parâmetros da requisição
            customer = Customer.getBuilder()
                    .withCustomerId(Integer.parseInt(request.getParameter("txtId")))
                    .withCustomerName(request.getParameter("txtNameCustomer"))
                    .withIdentifierDocument(request.getParameter("txtIdentifierDocument"))
                    .withBirthDate(request.getParameter("txtBirthDate"))
                    .withGender(request.getParameter("txtGender"))
                    .withPhoneNumber(request.getParameter("txtPhoneNumber"))
                    .build();
            
            Customer existingDocumentoforCustomer = customerdao.findByDocument(customer);
            if (existingDocumentoforCustomer != null
                    && existingDocumentoforCustomer.getIdCustomer() != customer.getIdCustomer()) {
                // O documento já existe para outro funcionário
                request.setAttribute("error", "Este documento já está associado a outro cliente.");
                customer = customerdao.findById(customer);
                request.setAttribute("customer", customer);
                return "customerUpdate.jsp";
                
            } else if (existingDocumentoforCustomer != null
                    && !existingDocumentoforCustomer.getIdentifierDocument().equals(customer.getIdentifierDocument())) {
                // O documento informado não bate com o registrado anteriormente
                request.setAttribute("error", "O documento inserido não corresponde ao já registrado para este cliente.");
                customer = customerdao.findById(customer);
                request.setAttribute("customer", customer);
                return "customerUpdate.jsp";
            }
            
            if (!validationErrors.isEmpty()) {
                // Se houver erros de validação, os exibe na página de atualização
                request.setAttribute("error", validationErrors);
                customer = customerdao.findById(customer);
                request.setAttribute("customer", customer);
                return "customerUpdate.jsp";
            }

            // Atualiza o cliente no banco de dados
            customerdao.update(customer);

            // Busca todos os clientes para exibir na lista após a atualização
            List<Customer> allCustomer = customerdao.findAll();
            request.setAttribute("allCustomer", allCustomer);
        } catch (SQLException | ClassNotFoundException ex) {
            errorMessage.append("Erro ao tentar atualizar o quarto: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "customerUpdate.jsp"; // Retorna ao formulário em caso de erro de DAO

        } catch (NumberFormatException ex) {
            errorMessage.append("Erro no formato dos dados: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "customerUpdate.jsp"; // Volta ao formulário em caso de erro de formato
        }

        return "customerList.jsp";
    }
}
