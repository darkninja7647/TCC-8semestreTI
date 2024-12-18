
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author darkn
 */
public class Account {

    private int idAccount;
    private String email;
    private String accountPassword;
    private int id_Employee;
    private String accessLevel;
    private int firstAccess;
    private String recoveryToken;
    private Date expirationDate;

    private Employee employee;

    public String getRecoveryToken() {
        return recoveryToken;
    }

    public void setRecoveryToken(String recoveryToken) {
        this.recoveryToken = recoveryToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Objetos relacionados
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the id
     */
    public int getIdAccount() {
        return idAccount;
    }

    /**
     * @param idAccount the id to set
     */
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountPassword
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * @param accountPassword the accountPassword to set
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getFirstAccess() {
        return firstAccess;
    }

    public void setFirstAccess(int firstAccess) {
        this.firstAccess = firstAccess;
    }

    public int getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(int id_Employee) {
        this.id_Employee = id_Employee;
    }    

    //cria uma sessao 
    public void iniciarSessaoUsuario(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("idAccount", this.idAccount);
        session.setAttribute("email", this.email);
        session.setAttribute("accountPassword", this.accountPassword);
        session.setAttribute("firstAccess", this.firstAccess);
        session.setAttribute("accessLevel", this.accessLevel);
        session.setAttribute("id_Employee", this.id_Employee);
        session.setAttribute("account", this); // Armazena o objeto atual (this) na sessão

        // Inclui o objeto Employee e o campo setor
        if (this.employee != null) {
            session.setAttribute("setor", this.employee.getSector());
        }
        session.setMaxInactiveInterval(1800);

    }
// fecha a sessao

    public void closeUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Recupera a sessão, se existir
        if (session != null) {
            session.invalidate(); // Invalida e termina a sessão
        }
    }
    public static AccountBuilder getBuilder() {
        return new AccountBuilder();
    }
    public static class AccountBuilder {

        private Account u = new Account();

        public AccountBuilder withIdAccount(int idAccount) {
            u.idAccount = idAccount;
            return this;
        }

        public AccountBuilder withemail(String email) {
            u.email = email;
            return this;
        }

        public AccountBuilder withAccountPassword(String accountPassword) {
            u.accountPassword = accountPassword;  // Criptografa a accountPassword antes de atribuí-la
            return this;
        }

        public AccountBuilder withAccessLevel(String accessLevel) {
            u.accessLevel = accessLevel;
            return this;
        }

        public AccountBuilder withFirstAccess(int firstAccess) {
            u.firstAccess = firstAccess;
            return this;
        }

        public AccountBuilder withId_Employee(int id_Employee) {
            u.id_Employee = id_Employee;
            return this;
        }

        public AccountBuilder withRecoveryToken(String recoveryToken) {
            u.recoveryToken = recoveryToken;
            return this;
        }

        public AccountBuilder withExpirationDate(Date expirationDate) {
            u.expirationDate = expirationDate;
            return this;
        }

        public AccountBuilder generateTokenRecovery() {
            u.recoveryToken = UUID.randomUUID().toString(); // Gera o token único
            u.expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hora de expiração
            return this;
        }

        public Account build() {
            return u;
        }

    }
// validacao de erros de algumas variaveis

    public static String validateAccountEmployee(String email, String accountPassword, String accessLevel, Integer firstAccess, String recoveryToken, String expirationDate) {
        StringBuilder validationMessage = new StringBuilder();

        // Validação dos campos usando um mapa de campos
        Map<String, String> fieldValidations = new HashMap<>();
        fieldValidations.put("Email", email);
        fieldValidations.put("Senha", accountPassword);
        fieldValidations.put("Nível de acesso", accessLevel);

        // Verificando cada campo obrigatório
        for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
            if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
                validationMessage.append("O campo ").append(entry.getKey()).append(" é obrigatório.\\n");
            }
        }

        // Verifica o campo de acesso inicial (firstAccess) se está preenchido e correto
        if (firstAccess == null || (firstAccess != 0 && firstAccess != 1)) {
            validationMessage.append("O campo de primeiro acesso (firstAccess) deve ser 0 ou 1.\\n");
        }

        // Validação do formato do email
        if (email != null && !email.trim().isEmpty() && !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            validationMessage.append("O email informado não possui um formato válido.\\n");
        }
        return validationMessage.toString();
    }
}
