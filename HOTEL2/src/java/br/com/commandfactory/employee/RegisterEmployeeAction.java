/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.employee;

import dao.EmployeeDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Employee;

/**
 *
 * @author LIDIA
 */
public class RegisterEmployeeAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            EmployeeDAO employeedao = new EmployeeDAO();
            
            Employee emp = Employee.getBuilder()
                    .withIdentifierDocumentEmployee(request.getParameter("txtIdentifierDocumentEmployee"))
                    .build();
            
            Employee existingDocumentoforEmployee = employeedao.findByDocument(emp);
            if (existingDocumentoforEmployee != null){
                request.setAttribute("error", "Este documento já existe no sistema.");
                return "employeeRegister.jsp";
            }
            
            String errorMessage = Employee.validateEmployeeFields(
                    request.getParameter("txtNameEmployee"),
                    request.getParameter("txtIdentifierDocumentEmployee"),
                    request.getParameter("txtSector")
            );
            // Se houver erros, retorna ao formulário com a mensagem de erro
            if (!errorMessage.isEmpty()) {
                request.setAttribute("error", errorMessage);
                return "employeeRegister.jsp"; // Retorna ao formulário para correções
            }
            // Criação do objeto Room após validação dos campos
            Employee employee = Employee.getBuilder()
                    .withIdentifierDocumentEmployee(request.getParameter("txtIdentifierDocumentEmployee")) // Número do quarto
                    .withNameEmployee(request.getParameter("txtNameEmployee")) // Capacidade
                    .withSector(request.getParameter("txtSector")) // Andar
                    .withstatusEmployee(request.getParameter("txtStatusEmployee")) // Tipo do quarto
                    .build();
            employeedao.register(employee);
            
            List<Employee> lista = employeedao.findAll();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "employeeList.jsp";
    }
}
