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
import model.Customer;
import util.DatabaseConnection;

/**
 *
 * @author darkn
 */
public class CustomerDAO {

    public void register(Customer customer) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO Customer(nameCustomer,identifierDocument,birthDate,gender,phoneNumber) VALUES(?,?,?,?,?)";
        //3
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, customer.getNameCustomer());
            pstmt.setString(2, customer.getIdentifierDocument());
            java.sql.Date sqlDate = new java.sql.Date(customer.getBirthDate().getTime());
            pstmt.setDate(3, new java.sql.Date((customer.getBirthDate().getTime())));
            pstmt.setString(4, customer.getGender());
            pstmt.setString(5, customer.getPhoneNumber());

            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar cliente: " + e.toString(), e);
        }

    }

    public void delete(Customer customer) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM Customer where idCustomer = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, customer.getIdCustomer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir cliente: " + e.toString(), e);
        }
    }

    public void update(Customer customer) throws ClassNotFoundException, SQLException {

        //1
        String sql = "UPDATE Customer SET nameCustomer = ?, identifierDocument = ?, birthDate = ?, gender = ?, phoneNumber = ? WHERE idCustomer = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, customer.getNameCustomer());
            pstmt.setString(2, customer.getIdentifierDocument());
            pstmt.setDate(3, new java.sql.Date((customer.getBirthDate().getTime())));
            pstmt.setString(4, customer.getGender());
            pstmt.setString(5, customer.getPhoneNumber());
            pstmt.setInt(6, customer.getIdCustomer());

            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cliente: " + e.toString(), e);
        }
    }

    public List<Customer> findAll() throws ClassNotFoundException, SQLException {
        //1    public List<Customer> consultar() throws ClassNotFoundException, SQLException {

        List<Customer> allCustomers = new ArrayList<Customer>();

        String sql = "SELECT idCustomer, nameCustomer, identifierDocument, birthDate, gender, phoneNumber FROM Customer";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setIdCustomer(rs.getInt("idCustomer"));
                customer.setNameCustomer(rs.getString("nameCustomer"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setIdentifierDocument(rs.getString("identifierDocument"));
                customer.setBirthDate(rs.getDate("birthDate"));
                customer.setGender(rs.getString("gender"));

                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar todos os clientes: " + e.toString(), e);
        }
        //4
        return allCustomers;
    }

    //consultar por id
    public Customer findById(Customer customer) throws ClassNotFoundException, SQLException {
        Customer foundCustomer = null;

        String sql = "SELECT idCustomer, nameCustomer, identifierDocument, birthDate, gender, phoneNumber FROM Customer WHERE idCustomer = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) { //2
            pstmt.setInt(1, customer.getIdCustomer());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundCustomer = new Customer();
                    foundCustomer.setIdCustomer(rs.getInt("idCustomer"));
                    foundCustomer.setNameCustomer(rs.getString("nameCustomer"));
                    foundCustomer.setPhoneNumber(rs.getString("phoneNumber"));
                    foundCustomer.setIdentifierDocument(rs.getString("identifierDocument"));
                    foundCustomer.setBirthDate(rs.getDate("birthDate"));
                    foundCustomer.setGender(rs.getString("gender"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar cliente por ID: " + e.toString(), e);
        }
        return foundCustomer;

    }

    public Customer findByDocument(Customer customer) throws ClassNotFoundException, SQLException {
        Customer foundCustomer = null;

        String sql = "SELECT idCustomer, nameCustomer, identifierDocument, birthDate, gender, phoneNumber FROM Customer WHERE identifierDocument = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, customer.getIdentifierDocument());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundCustomer = new Customer();
                    foundCustomer.setIdCustomer(rs.getInt("idCustomer"));
                    foundCustomer.setNameCustomer(rs.getString("nameCustomer"));
                    foundCustomer.setPhoneNumber(rs.getString("phoneNumber"));
                    foundCustomer.setIdentifierDocument(rs.getString("identifierDocument"));
                    foundCustomer.setBirthDate(rs.getDate("birthDate"));
                    foundCustomer.setGender(rs.getString("gender"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar cliente por documento: " + e.toString(), e);
        }
        return foundCustomer;

    }

    public Customer findByDocumentId(String DocumentoIdentifiers) throws ClassNotFoundException, SQLException {
        Customer foundCustomer = null;
        String sql = "SELECT  idCustomer FROM Customer WHERE identifierDocument = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, DocumentoIdentifiers);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundCustomer = new Customer();
                    foundCustomer.setIdCustomer(rs.getInt("idCustomer"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar cliente por ID: " + e.toString(), e);
        }
        return foundCustomer;

    }
    public List<Customer> CustomerDocument() throws ClassNotFoundException, SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT nameCustomer,identifierDocument from Customer ORDER BY idCustomer DESC    ";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                 Customer customer = new Customer(); 
                customer.setNameCustomer(rs.getString("nameCustomer"));
                customer.setIdentifierDocument(rs.getString("identifierDocument"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os números dos quartos: " + e.toString(), e);
        }
        return customers;
    }
    
    public boolean hasReservations(int customerId) throws ClassNotFoundException, SQLException {
    // Consulta para verificar se existe uma reserva associada ao cliente
    String sql = "SELECT * FROM Customer c INNER JOIN Reservation r ON c.idCustomer = r.id_Customer WHERE c.idCustomer = ?";
    try (Connection conexao = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conexao.prepareStatement(sql)) {
        // Define o parâmetro para o ID do cliente
        pstmt.setInt(1, customerId);
        // Executa a consulta
        ResultSet rs = pstmt.executeQuery();
        // Retorna true se houver ao menos um registro (ou seja, uma reserva associada)
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Propaga a exceção para o chamador, se necessário
    }
}
}
