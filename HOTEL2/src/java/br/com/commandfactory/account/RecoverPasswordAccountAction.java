    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package br.com.commandfactory.account;

    import dao.AccountDAO;
    import java.sql.SQLException;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.Date;
    import java.util.UUID;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import model.Account;
    import model.Email;
    import model.Email.EmailBuilder;

    /**
     *
     * @author darkn
     */
    public class RecoverPasswordAccountAction implements ICommand {

        @Override
        public String executar(HttpServletRequest request, HttpServletResponse response) {
            String email = request.getParameter("txtEmail").toLowerCase(); // Supondo que o input na JSP tenha esse nome
            AccountDAO accountDAO = new AccountDAO();

            try {
                // Verificar se o email existe no banco
                Account account = accountDAO.findByEmail(email);
                if (account != null) {
                    Account accountWithToken = Account.getBuilder()
                            .withemail(account.getEmail())
                            .generateTokenRecovery()// Isso vai gerar o token e a data de expiração
                            .build();

                    String tokenRecuperacao = accountWithToken.getRecoveryToken();
                    Date dataExpiracao = accountWithToken.getExpirationDate();

                    accountDAO.updateTokenAndFirstAccess(account.getEmail(), tokenRecuperacao, dataExpiracao);

                    // Preparar o email
                    String subject = "Recuperação de Senha";
                    String body = "Clique no link abaixo para redefinir sua senha:\n"
                            + "http://localhost:8080/HOTEL2/accountEmail.jsp?token=" + tokenRecuperacao;

                    // Enviar o email
                    Email emailModel = Email.getBuilder()
                            .comTo(email)
                            .comSubject(subject)
                            .comBody(body)
                            .constroi();
                    emailModel.send(); // Certifique-se de que a classe Email tenha um método send()

                    request.setAttribute("mensagem", "Um email de recuperação foi enviado para " + email);
                    return "index.html"; // Página de sucesso
                } else {
                    request.setAttribute("erro", "Email não encontrado.");
                    return "erro.jsp"; // Página de erro
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao recuperar a senha: " + e.getMessage());
                return "erro.jsp"; // Página de erro
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao enviar o email: " + e.getMessage());
                return "erro.jsp"; // Página de erro
            }
        }
    }
