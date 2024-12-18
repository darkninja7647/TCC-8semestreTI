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
public class DeleteEmployeeAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Employee emp = Employee.getBuilder()
        .withIdEmployee(Integer.parseInt(request.getParameter("txtIdEmployee")))
        .build();
        EmployeeDAO empdao = new EmployeeDAO();
        try {
             if (empdao.hasAccount(emp.getIdEmployee())) {
                request.setAttribute("error", "Não é possivel deletar o fucionario, pois já teve ou tem uma conta cadastrada.");
                request.setAttribute("lista", empdao.findAll()); // Adiciona a lista de clientes
                return "employeeList.jsp"; // Ou exibe mensagem na mesma página
            }

            empdao.delete(emp);
            List<Employee> lista = empdao.findAll();
            request.setAttribute("lista", lista);
       } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "employeeList.jsp";
    }
}
