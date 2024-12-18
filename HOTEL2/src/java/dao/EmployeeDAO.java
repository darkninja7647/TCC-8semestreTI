package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import util.DatabaseConnection;

public class EmployeeDAO {

    //cadastrar/registrar um funcionario  
    public void register(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee(nameEmployee, identifierDocumentEmployee, sector, statusEmployee) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employee.getNameEmployee());
            pstmt.setString(2, employee.getIdentifierDocumentEmployee());
            pstmt.setString(3, employee.getSector());
            pstmt.setString(4, employee.getStatusEmployee());
            pstmt.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar funcionario: " + e.toString(), e);
        }
    }

    //metodo que deleta o Employee atraves do Id
    public void delete(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Employee WHERE idEmployee = ?";
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employee.getIdEmployee());
            pstmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir funcionário: " + e.toString(), e);
        }
    }

    //metodo que atualizar o Employee atraves do Id 
    public void update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET nameEmployee = ?, identifierDocumentEmployee = ?, sector = ?, statusEmployee = ? WHERE idEmployee = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employee.getNameEmployee());
            pstmt.setString(2, employee.getIdentifierDocumentEmployee());
            pstmt.setString(3, employee.getSector());
            pstmt.setString(4, employee.getStatusEmployee());
            pstmt.setInt(5, employee.getIdEmployee());
            pstmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar funcionario: " + e.toString(), e);
        }
    }

    //metodo que lista varios funcioarios 
    public List<Employee> findAll() throws SQLException, ClassNotFoundException {
        List<Employee> list = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT idEmployee,nameEmployee, identifierDocumentEmployee, sector, statusEmployee FROM Employee";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setIdEmployee(rs.getInt("idEmployee"));
                emp.setNameEmployee(rs.getString("nameEmployee"));
                emp.setIdentifierDocumentEmployee(rs.getString("identifierDocumentEmployee"));
                emp.setSector(rs.getString("sector"));
                emp.setStatusEmployee(rs.getString("statusEmployee"));
                list.add(emp);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar todos os clientes: " + e.toString(), e);
        }
        return list;
    }

    //metodo que procura um funcionario por ID
    public Employee findById(Employee employee) throws SQLException, ClassNotFoundException {
        Employee emp = new Employee();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT idEmployee,nameEmployee, identifierDocumentEmployee, sector, statusEmployee FROM Employee WHERE idEmployee = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employee.getIdEmployee());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                emp.setIdEmployee(rs.getInt("idEmployee"));
                emp.setNameEmployee(rs.getString("nameEmployee"));
                emp.setIdentifierDocumentEmployee(rs.getString("identifierDocumentEmployee"));
                emp.setSector(rs.getString("sector"));
                emp.setStatusEmployee(rs.getString("statusEmployee"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar todos os clientes: " + e.toString(), e);
        }
        return emp;
    }

    //metodo que procura um funcionario por documento
    public Employee findByDocument(Employee emp) throws ClassNotFoundException, SQLException {
        Employee foundEmployee = null;

        String sql = "SELECT idEmployee, nameEmployee, identifierDocumentEmployee, sector, statusEmployee FROM Employee WHERE identifierDocumentEmployee = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, emp.getIdentifierDocumentEmployee());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundEmployee = new Employee();
                    foundEmployee.setIdEmployee(rs.getInt("idEmployee"));
                    foundEmployee.setNameEmployee(rs.getString("nameEmployee"));
                    foundEmployee.setIdentifierDocumentEmployee(rs.getString("identifierDocumentEmployee"));
                    foundEmployee.setSector(rs.getString("sector"));
                    foundEmployee.setStatusEmployee(rs.getString("statusEmployee"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar funcionário por documento: " + e.toString(), e);
        }

        return foundEmployee;
    }

    public List<Employee> findallwithoutidaccount() throws ClassNotFoundException, SQLException {
        List<Employee> accountlist = new ArrayList();

        String sql = "SELECT e.idEmployee, e.nameEmployee, e.identifierDocumentEmployee, e.sector, e.statusEmployee FROM Employee e LEFT JOIN AccountEmployee a ON e.idEmployee = a.id_Employee WHERE a.idAccount IS NULL AND e.statusEmployee = 'Ativo'";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setIdEmployee(rs.getInt("idEmployee"));
                    employee.setNameEmployee(rs.getString("nameEmployee"));
                    employee.setIdentifierDocumentEmployee(rs.getString("identifierDocumentEmployee"));
                    employee.setSector(rs.getString("sector"));
                    employee.setStatusEmployee(rs.getString("statusEmployee"));
                    accountlist.add(employee);
                }
            }
            return accountlist;
        }
    }
    public boolean hasAccount(int idaccount) throws ClassNotFoundException, SQLException {
    String sql = "SELECT Employee.idEmployee FROM Employee INNER JOIN AccountEmployee ON Employee.idEmployee = AccountEmployee.id_Employee where idEmployee = ?;";
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
