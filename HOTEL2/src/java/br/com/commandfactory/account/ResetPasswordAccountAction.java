/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import dao.AccountDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author darkn
 */
    public class ResetPasswordAccountAction implements ICommand{

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        String novaSenha = request.getParameter("newPassword"); 
        String senhaconfirmada = request.getParameter("confirmPassword"); // Supondo que o input na JSP tenha esse nome
        AccountDAO accountDAO = new AccountDAO();

     try {
        // Verificar se o token é válido e obter a conta associada
        Account account = accountDAO.findByToken(token);
        if (account != null) {
            // Verificar se o token não está expirado
            Date dataExpiracao = account.getExpirationDate(); 
            if (LocalDateTime.now().isBefore(dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())) {
                
                // Verificar se a nova senha e a confirmação são iguais
                if (novaSenha.equals(senhaconfirmada)) {
                    
                    // Usar o Builder para atualizar a conta com a nova senha
                    Account accountParaAtualizar = Account.getBuilder()
                        .withIdAccount(account.getIdAccount()) // Definindo o id da conta
                        .withAccountPassword(novaSenha) // Definindo a nova senha
                        .build(); // Constroi o objeto Account

                    // Atualizar a senha no banco de dados
                    boolean sucesso = accountDAO.updatePassword(accountParaAtualizar);
                    
                    if (sucesso) {
                        request.setAttribute("mensagem", "Senha redefinida com sucesso!");
                        return "index.html"; // Página de sucesso
                    } else {
                        request.setAttribute("erro", "Falha ao atualizar a senha.");
                        return "erro.jsp"; // Página de erro
                    }
                } else {
                    request.setAttribute("erro", "As senhas não correspondem.");
                    return "erro.jsp"; // Página de erro
                }
            } else {
                request.setAttribute("erro", "Token inválido ou expirado.");
                return "erro.jsp"; // Página de erro
            }
        } else {
            request.setAttribute("erro", "Token inválido ou expirado.");
            return "erro.jsp"; // Página de erro
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        request.setAttribute("erro", "Erro ao redefinir a senha: " + e.getMessage());
        return "erro.jsp"; // Página de erro
    }
}
}