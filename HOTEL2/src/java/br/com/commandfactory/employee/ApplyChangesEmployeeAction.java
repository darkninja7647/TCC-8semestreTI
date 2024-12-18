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
public class ApplyChangesEmployeeAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        EmployeeDAO employeedao = new EmployeeDAO();
        StringBuilder errorMessage = new StringBuilder();
        try {

            // Validação dos campos
            String validationError = Employee.validateEmployeeFields(
                    request.getParameter("txtNameEmployee"),
                    request.getParameter("txtIdentifierDocumentEmployee"),
                    request.getParameter("txtSector")
            );
            Employee employee = Employee.getBuilder()
                    .withIdEmployee(Integer.parseInt(request.getParameter("txtIdEmployee")))
                    .withIdentifierDocumentEmployee(request.getParameter("txtIdentifierDocumentEmployee"))
                    .withNameEmployee(request.getParameter("txtNameEmployee"))
                    .withSector(request.getParameter("txtSector"))
                    .withstatusEmployee(request.getParameter("txtStatusEmployee"))
                    .build();

            Employee existingDocumentoforEmployee = employeedao.findByDocument(employee);
            if (existingDocumentoforEmployee != null
                    && existingDocumentoforEmployee.getIdEmployee() != employee.getIdEmployee()) {
                // O documento já existe para outro funcionário
                request.setAttribute("error", "Este documento já está associado a outro funcionário.");
                employee = employeedao.findById(employee);
                request.setAttribute("employee", employee);
                return "employeeUpdate.jsp";
            } else if (existingDocumentoforEmployee != null
                    && !existingDocumentoforEmployee.getIdentifierDocumentEmployee().equals(employee.getIdentifierDocumentEmployee())) {
                // O documento informado não bate com o registrado anteriormente
                request.setAttribute("error", "O documento inserido não corresponde ao já registrado para este funcionário.");
                employee = employeedao.findById(employee);
                request.setAttribute("employee", employee);
                return "employeeUpdate.jsp";
            }

            // Se houver erro de validação, define a mensagem e retorna ao formulário
            if (!validationError.isEmpty()) {
                request.setAttribute("error", validationError);
                employee = employeedao.findById(employee);
                request.setAttribute("employee", employee);
                return "employeeUpdate.jsp"; // Volta para o formulário
            }

            if (employeedao.hasAccount(employee.getIdEmployee())) {
                // O funcionário já possui uma conta
                Employee existingEmployee = employeedao.findById(employee);

                // Verifica se o status foi alterado
                if (!existingEmployee.getStatusEmployee().equals(employee.getStatusEmployee())) {
                    // Permite alterar apenas o status
                    existingEmployee.setStatusEmployee(employee.getStatusEmployee());
                    employeedao.update(existingEmployee); // Reutiliza o método de atualização completo
                    List<Employee> lista = employeedao.findAll(); // Busca todos os funcionários atualizados
                      request.setAttribute("error", "Apenas foi possivel alterar o status do funcionario, pois o mesmo já tem um conta.");
                    request.setAttribute("lista", lista); // Adiciona a lista ao request para exibição
                    return "employeeList.jsp"; // Retorna para a página de listagem
                } else {
                    request.setAttribute("error", "O status do funcionário não foi alterado porque já está como '" + existingEmployee.getStatusEmployee() + "'.");
                    request.setAttribute("employee", employee);
                    return "employeeUpdate.jsp";
                }
            } else {
                // O funcionário não possui uma conta: atualiza todos os dados
                employeedao.update(employee); // Atualiza todos os dados do funcionário
            }

           
            List<Employee> lista = employeedao.findAll();
            request.setAttribute("lista", lista);
        } catch (SQLException | ClassNotFoundException ex) {
            errorMessage.append("Erro ao tentar atualizar o funcionario: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "employeeUpdate.jsp"; // Retorna ao formulário em caso de erro de DAO

        } catch (NumberFormatException ex) {
            errorMessage.append("Erro no formato dos dados: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "employeeUpdate.jsp"; // Volta ao formulário em caso de erro de formato
        }

        return "employeeList.jsp";
    }
}
