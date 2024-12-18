/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import dao.AccountDAO;
import dao.EmployeeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;

/**
 *
 * @author darkn
 */
public class RegisterAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO empdao = new EmployeeDAO();
        AccountDAO accountdao = new AccountDAO();
        try {

            Employee emp = Employee.getBuilder()
                    .withIdentifierDocumentEmployee(request.getParameter("txtidentifierDocumentEmployee"))
                    .build();
            Employee employee = empdao.findByDocument(emp);
            if (employee == null){
                request.setAttribute("error", "Este funcionário não existe no sistema.");
                return "accountRegister.jsp";
            }
            // Verifica se já existe uma Account associada a esse Employee
            Account existingAccountForEmployee = accountdao.findByEmployeeId(employee.getIdEmployee());
            if (existingAccountForEmployee != null) {
                request.setAttribute("error", "Este funcionário já possui uma conta.");
                return "accountRegister.jsp"; // Retorna para a página de cadastro
            }
            Account existente = accountdao.findByEmail(request.getParameter("txtEmail").toLowerCase());
            if (existente != null) {
                // Se o e-mail já existe, define uma mensagem de erro e redireciona para a página de cadastro
                request.setAttribute("error", "O e-mail já está em uso. Escolha outro.");
                return "accountRegister.jsp"; // Página JSP de cadastro de conta
            }

            Account account = Account.getBuilder()
                    .withemail(request.getParameter("txtEmail").toLowerCase())
                    .withAccountPassword(request.getParameter("txtAccountPassword"))
                    .withAccessLevel(request.getParameter("txtAccessLevel"))
                    .withFirstAccess(Integer.parseInt(request.getParameter("txtFirstAccess")))
                    .withId_Employee(employee.getIdEmployee())
                    .build();
            
            if (accountdao.register(account)) {

                List<Account> accountlist = accountdao.findAll();
                request.setAttribute("accountlist", accountlist);
            } else {
                // Falha no cadastro
                request.setAttribute("error", "Erro ao cadastrar a conta.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "accountList.jsp";

    }
}
