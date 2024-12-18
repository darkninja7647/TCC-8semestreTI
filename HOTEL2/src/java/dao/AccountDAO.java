/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Employee;
import util.DatabaseConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author darkn
 */
public class AccountDAO {

    // Método para realizar login baseado no email e na senha do usuário
    public Account Login(Account account) throws ClassNotFoundException, SQLException {
        // Definindo a sql de SQL para realizar a consulta na tabela AccountEmployee
        String sql = "SELECT AccountEmployee.idAccount, AccountEmployee.email, AccountEmployee.accountPassword, AccountEmployee.accessLevel, AccountEmployee.firstAccess, Employee.sector, Employee.statusEmployee, AccountEmployee.id_employee FROM AccountEmployee INNER JOIN Employee ON AccountEmployee.id_Employee = Employee.idEmployee WHERE Employee.statusEmployee IN('Ativo','Ocupado') and email = ?";
        // Usando try-with-resources para garantir que a conexão e os recursos sejam fechados automaticamente
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, account.getEmail());
            ResultSet rs = pstmt.executeQuery();
            Account account1 = new Account();
            try {
                if (rs.next()) {
                    // inserindo dados no objeto Account com os dados retornados da consulta
                    account1.setIdAccount(rs.getInt("idAccount"));
                    account1.setEmail(rs.getString("email"));
                    account1.setAccountPassword(rs.getString("accountPassword"));
                    account1.setFirstAccess(rs.getInt("firstAccess"));
                    account1.setAccessLevel(rs.getString("accessLevel"));
                    account1.setId_Employee(rs.getInt("id_Employee"));
                    // Cria o objeto Employee e preenche o campo setor
                    Employee employee = new Employee();
                    employee.setSector(rs.getString("sector"));
                    account1.setEmployee(employee);
                }
            } catch (SQLException e) {
                throw new SQLException("Erro ao processar o resultado da consulta: " + e.getMessage(), e);
            }
            return account1;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O funcionario já tem uma conta cadastrada.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar conta: " + e.getMessage(), e);
        }
    }

    // Método para criar/registrar uma nova conta
    public boolean register(Account account) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO AccountEmployee (email, AccountPassword, accessLevel, firstAccess, id_Employee) VALUES (?, ?, ?, ?, ?)";
        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            // Define os parâmetros para a consulta
            pstmt.setString(1, account.getEmail());
            pstmt.setString(2, account.getAccountPassword());
            pstmt.setString(3, account.getAccessLevel());
            pstmt.setInt(4, account.getFirstAccess());
            pstmt.setInt(5, account.getId_Employee());
            // Executa a inserção e verifica se a operação foi bem-sucedida
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Caso haja erro ao realizar a inserção
            throw new SQLException("Erro ao registrar conta: " + e.getMessage(), e);
        }
    }

    // Método para buscar um id da conta pelo e-mail
    public Account findByEmail(String email) throws ClassNotFoundException, SQLException {
        String sql = "SELECT email, idAccount FROM AccountEmployee WHERE email = ?";
        Account foundEmail = null;
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            // Verificando se existe um registro com o e-mail fornecido
            if (rs.next()) {
                foundEmail = new Account();
                foundEmail.setIdAccount(rs.getInt("idAccount"));
                foundEmail.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar o e-mail: " + e.getMessage());
            throw e;
        }
        // Retorna a conta encontrada 
        return foundEmail;
    }

    //metodo que procura O id_Employee pelo email
    public Account findByEmailId(String email) throws ClassNotFoundException, SQLException {
        //1
        Account foundEmail = null;
        String sql = "SELECT ae.id_Employee FROM AccountEmployee ae INNER JOIN Employee e ON ae.id_Employee = e.idEmployee WHERE ae.email = ?  AND e.statusEmployee = 'Ativo'";
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundEmail = new Account();
                    foundEmail.setId_Employee(rs.getInt("id_Employee"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar funcionário por ID: " + e.toString(), e);
        }
        return foundEmail;
    }
// Método para buscar todas as contas onde apenas os funcionarios estejam ativos e pegando os nomes dos usuarios.

    public List<Account> findAll() throws ClassNotFoundException, SQLException {
        //1    public List<Customer> consultar() throws ClassNotFoundException, SQLException {
        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "SELECT AccountEmployee.idAccount,AccountEmployee.email,AccountEmployee.accessLevel,AccountEmployee.firstAccess,Employee.statusEmployee,Employee.nameEmployee,AccountEmployee.id_employee FROM AccountEmployee INNER JOIN Employee ON AccountEmployee.id_Employee = Employee.idEmployee WHERE Employee.statusEmployee IN('Ativo','Ocupado')";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Account> accountlist = new ArrayList<Account>();
        while (rs.next()) {
            Account ac = new Account();
            Employee emp = new Employee();
            emp.setNameEmployee(rs.getString("nameEmployee"));

            ac.setIdAccount(rs.getInt("idAccount"));
            ac.setEmail(rs.getString("email"));
            ac.setAccessLevel(rs.getString("accessLevel"));
            ac.setId_Employee(rs.getInt("id_Employee"));
            ac.setEmployee(emp);
            accountlist.add(ac);
        }
        //4
        return accountlist;
    }

    // Método para excluir uma conta pelo ID
    public void delete(Account account) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM AccountEmployee WHERE idAccount = ?";
        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, account.getIdAccount()); // Define o ID da conta a ser excluída
            // Executa a exclusão
            int rowsAffected = pstmt.executeUpdate();

            // Se nenhuma linha foi afetada, isso indica que a conta não foi encontrada
            if (rowsAffected == 0) {
                throw new SQLException("Erro: Nenhuma conta encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            // Lança exceção caso ocorra algum erro ao executar a exclusão
            throw new SQLException("Erro ao excluir conta: " + e.getMessage(), e);
        }
    }

    // Método para atualizar o token de recuperação e a data de expiração
    public void updateTokenAndFirstAccess(String email, String tokenRecovery, Date expirationDate) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE AccountEmployee SET recoveryToken = ?,expirationDate = ? WHERE email = ?";
        LocalDateTime localDateTime = Instant.ofEpochMilli(expirationDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ExpirationdateStr = localDateTime.format(formatter);
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, tokenRecovery);
            pstmt.setString(2, ExpirationdateStr);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar token e data de expiração: " + e.getMessage());
            throw e;  // Re-lança a exceção após o log
        }
    }

    // Método para buscar uma conta pelo ID
    public Account findById(Account account) throws ClassNotFoundException, SQLException {
        String sql = "SELECT AccountEmployee.idAccount,AccountEmployee.email,AccountEmployee.accessLevel,Employee.sector,Employee.statusEmployee,AccountEmployee.id_employee FROM AccountEmployee INNER JOIN Employee ON AccountEmployee.id_Employee = Employee.idEmployee WHERE Employee.statusEmployee = 'Ativo' and idAccount = ?";
        Account ac = null;
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, account.getIdAccount());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ac = new Account();
                    Employee emp = new Employee();
                    ac.setIdAccount(rs.getInt("idAccount"));
                    ac.setEmail(rs.getString("email"));
                    ac.setAccessLevel(rs.getString("accesslevel"));
                    ac.setId_Employee(rs.getInt("id_Employee"));
                    emp.setNameEmployee(rs.getString("nameEmployee"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar Account pelo ID: " + e.getMessage());
            throw e;  // Re-lança a exceção após o log
        }
        return ac;
    }

    // Método para atualizar a senha de uma conta
    public boolean updatePassword(Account account) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE AccountEmployee SET accountPassword = ?, firstAccess = 2 WHERE idAccount = ?";
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, account.getAccountPassword());
            pstmt.setInt(2, account.getIdAccount());
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao alterar senha: " + e.getMessage());
            throw e;  // Re-lança a exceção após o log
        }
    }

    // Método para buscar uma conta pelo token de recuperação
    public Account findByToken(String token) throws ClassNotFoundException, SQLException {
        String sql = "SELECT idAccount, expirationDate FROM AccountEmployee WHERE recoveryToken = ?";
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, token);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setIdAccount(rs.getInt("idAccount"));
                    account.setExpirationDate(rs.getTimestamp("expirationDate"));
                    return account;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar token: " + e.getMessage());
            throw e;  // Re-lança a exceção após o log
        }
    }
//Metodo de busca realizada por idAccount procurando o Employee com o status do employee ativo

    public Account findPasswordById(Account account) throws ClassNotFoundException, SQLException {

        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "SELECT AccountEmployee.idAccount,AccountEmployee.accountPassword,Employee.sector,Employee.statusEmployee,AccountEmployee.id_employee FROM AccountEmployee INNER JOIN Employee ON AccountEmployee.id_Employee = Employee.idEmployee WHERE Employee.statusEmployee = 'Ativo' and idAccount = ?;";
        //3       
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, account.getIdAccount());
        ResultSet rs = pstmt.executeQuery();
        Account ac = new Account();
        if (rs.next()) {
            ac.setIdAccount(rs.getInt("idAccount"));
            ac.setAccountPassword(rs.getString("accountPassword"));
        }

        //4
        return ac;
    }

    //Metodo de busca passando o id para retornar um objeto account
    public Account findByEmployeeId(int employeeId) throws SQLException, ClassNotFoundException {
        //String que esta rodando com o banco de dados e a declaracao do objeto
        String sql = "SELECT * FROM AccountEmployee WHERE id_Employee = ?";
        Account account = null;
        // tratativa de erro focada em conexao
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            // tratativa de erro focada em em lista
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    account = Account.getBuilder()
                            .withIdAccount(rs.getInt("idAccount"))
                            .withemail(rs.getString("email"))
                            .withAccountPassword(rs.getString("accountPassword"))
                            .withId_Employee(rs.getInt("id_Employee"))
                            .withAccessLevel(rs.getString("accessLevel"))
                            .withFirstAccess(rs.getInt("firstAccess"))
                            .withRecoveryToken(rs.getString("recoveryToken"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar por Employee ID: " + ex.getMessage());
            throw ex;
        }
        return account;
    }

    public Account findByEmailEmployeeInformation(Account email) throws SQLException, ClassNotFoundException {
        Account account = new Account();
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT accountemployee.email, accountemployee.idAccount, accountemployee.accessLevel, employee.nameEmployee FROM AccountEmployee  accountemployee INNER JOIN Employee employee ON accountemployee.id_Employee = employee.idEmployee WHERE AccountEmployee.email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email.getEmail());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setNameEmployee(rs.getString("nameEmployee"));
                    account.setEmployee(employee);
                    account.setEmail(rs.getString("email"));
                    account.setIdAccount(rs.getInt("idAccount"));
                    account.setAccessLevel(rs.getString("accessLevel"));
                }
            } catch (SQLException e) {
                throw new SQLException("Erro ao consultar o email:" + e.toString(), e);
            }
        }
        return account;
    }

    public List<Account> employeefindsectornameandemail() throws ClassNotFoundException, SQLException {
        List<Account> accountlist = new ArrayList();

        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "SELECT accountemployee.id_Employee,AccountEmployee.email,employee.sector,Employee.statusEmployee,Employee.nameEmployee FROM AccountEmployee INNER JOIN Employee ON AccountEmployee.id_Employee = Employee.idEmployee WHERE Employee.statusEmployee = 'Ativo'";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Account ac = new Account();
            Employee emp = new Employee();
            emp.setNameEmployee(rs.getString("nameEmployee"));
            emp.setSector(rs.getString("sector"));
            ac.setEmail(rs.getString("email"));
            ac.setId_Employee(rs.getInt("id_Employee"));
            ac.setEmployee(emp);
            accountlist.add(ac);
        }
        //4
        return accountlist;
    }

    public boolean hasRequest(int idaccount) throws ClassNotFoundException, SQLException {
    String sql = "SELECT r.idRequest FROM AccountEmployee ae INNER JOIN Request r ON ae.id_Employee = r.id_CollaboratorRequest WHERE ae.idAccount = ?";
    try (Connection conexao = DatabaseConnection.getConnection(); 
         PreparedStatement pstmt = conexao.prepareStatement(sql)) {
        pstmt.setInt(1, idaccount);
        ResultSet rs = pstmt.executeQuery();
        // Retorna true se houver ao menos um registro (ou seja, uma reserva associada)
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna false caso não haja reservas para o quarto
}
}
