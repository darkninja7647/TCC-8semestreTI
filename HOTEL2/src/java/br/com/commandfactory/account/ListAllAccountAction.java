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
public class ListAllAccountAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        AccountDAO accdao = new AccountDAO();

        try {
            // realizando a chamada de listagem
            List<Account> accountlist = accdao.findAll();
            request.setAttribute("accountlist", accountlist);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "accountList.jsp";
    }    
}
