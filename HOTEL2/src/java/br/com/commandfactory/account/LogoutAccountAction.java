package br.com.commandfactory.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

public class LogoutAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obtém o objeto Account da sessão
            Account account = (Account) request.getSession().getAttribute("account");

            if (account != null) {
                // Usa o método closeUserSession para encerrar a sessão
                account.closeUserSession(request);
            }

            // Redireciona para a página inicial
            return "index.html";
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao encerrar a sessão: " + e.getMessage());
            return "erro.jsp";
        }
    }
}