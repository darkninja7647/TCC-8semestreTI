/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import dao.AccountDAO;
import dao.RequestDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author darkn
 */
public class DeleteAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Account ac = Account.getBuilder()
                .withIdAccount(Integer.parseInt(request.getParameter("txtIdAccount")))
                .build();
        AccountDAO accdao = new AccountDAO();
        RequestDAO requestDAO = new RequestDAO();
        try {
              if (requestDAO.hasRequestsByAccount(ac.getIdAccount())) {
                // Se houver solicitações associadas, define uma mensagem de erro
                request.setAttribute("error", "Não é possível excluir a conta, pois existem solicitações associadas.");
                // Recupera a lista de contas para exibir
                List<Account> accountlist = accdao.findAll();
                request.setAttribute("accountlist", accountlist);
                return "accountList.jsp"; // Retorna para a página de listagem de contas
            }

            ///detete
            //deleta a account
            accdao.delete(ac);
            List<Account> accountlist = accdao.findAll();
            request.setAttribute("accountlist", accountlist);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "accountList.jsp";
    }
}
