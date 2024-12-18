/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import dao.AccountDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author darkn
 */
public class FindByEmailAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        
        AccountDAO accountdao = new AccountDAO();
        try {
            Account existente = accountdao.findByEmail(request.getParameter("txtEmail").toLowerCase());
            if (existente == null) {
                // Se o e-mail já existe, define uma mensagem de erro e redireciona para a página de cadastro
                request.setAttribute("error", "O e-mail não existe!");
                List<Account> accountlist = accountdao.findAll();
                request.setAttribute("accountlist", accountlist);
                return "accountList.jsp"; // Página JSP de cadastro de conta
            }
            
            Account account = Account.getBuilder()
                .withemail(request.getParameter("txtEmail").toLowerCase())
                .build();
            //consultar o uma conta atrelado aquela senha
           account = accountdao.findByEmailEmployeeInformation(account);
            request.setAttribute("account", account);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "accountFindByEmail.jsp";
    }
}


