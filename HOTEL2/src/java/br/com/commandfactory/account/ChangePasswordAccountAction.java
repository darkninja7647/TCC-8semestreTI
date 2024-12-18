/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import dao.AccountDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import javax.servlet.http.HttpSession;


/**
 *
 * @author darkn
 */
public class ChangePasswordAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

         String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

        AccountDAO accountdao = new AccountDAO();

        try {
            HttpSession session = request.getSession(false); // Recupera a sessão existente
            if (session == null || session.getAttribute("idAccount") == null) {
                request.setAttribute("error", "Sessão expirada. Faça login novamente.");
                return "index.html";
            }
            // Verifique ase a nova senha é a mesma que senha confirmada
             if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "A nova senha e a confirmação não coincidem.");
                return "accountFirstAccess.jsp"; // Retorna para a página de alteração de senha
            }

            // Obtém o idAccount da sessão
            int idAccount = (int) session.getAttribute("idAccount");

            Account account = Account.getBuilder()
                    .withIdAccount(idAccount)
                    .build();

            // Chama o método findById passando o objeto Account
            Account accountChecked = accountdao.findPasswordById(account);
            // verifica se é a mesma senha que a antiga

            if (accountChecked != null && accountChecked.getAccountPassword().equals(oldPassword )) {
                accountChecked.setAccountPassword(confirmPassword);
            // Caso for salvar a nova senha

                if (accountdao.updatePassword(accountChecked)) {
                    request.setAttribute("success", "Senha atualizada com sucesso.");
                } else {
                    request.setAttribute("error", "Erro ao atualizar a senha.");
                }
            } else {
                request.setAttribute("error", "Senha antiga incorreta.");
            }
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            request.setAttribute("error", "Erro ao processar a atualização de senha.");
        }

        return "index.html";
    }
}
