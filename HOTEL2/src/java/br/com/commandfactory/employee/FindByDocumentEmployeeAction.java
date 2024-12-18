/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.employee;

import dao.EmployeeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author LIDIA
 */
public class FindByDocumentEmployeeAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO empdao = new EmployeeDAO();

        try {
             Employee emp = Employee.getBuilder()
                    .withIdentifierDocumentEmployee(request.getParameter("txtIdentifierDocumentEmployee"))
                    .build();
            Employee ifEmployeeExist = empdao.findByDocument(emp);
            if (ifEmployeeExist == null){
                request.setAttribute("error", "Este funcionário não existe no sistema.");
                List<Employee> lista = empdao.findAll();
            request.setAttribute("lista", lista);
                return "employeeList.jsp";
            }
            
            emp = empdao.findByDocument(emp);
            request.setAttribute("emp", emp);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "employeeFindByDocument.jsp";
    }
}
