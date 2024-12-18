/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.employee;

import dao.EmployeeDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author LIDIA
 */
public class UpdateEmployeeAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Employee emp = Employee.getBuilder()
        .withIdEmployee(Integer.parseInt(request.getParameter("txtIdEmployee")))
        .build();
        EmployeeDAO empdao = new EmployeeDAO();
        try {
            emp = empdao.findById(emp);
            request.setAttribute("employee", emp);
        } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
            return "employeeUpdate.jsp";
    }
}


                   
         
            
