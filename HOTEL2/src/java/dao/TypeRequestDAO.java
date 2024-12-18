/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TypeRequest;
import util.DatabaseConnection;

/**
 *
 * @author darkn
 */
public class TypeRequestDAO {

    //metodo para register o tipo solicitacao
    public void register(TypeRequest typerequest) throws ClassNotFoundException, SQLException {
        // 1. Query SQL corrigida para o statusRoom com valor fixo
        String sql = "INSERT INTO TypeRequest(typeRequest) value (?)";
        // 2. Tentar obter a conexão com o banco de dados
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            // 3. Definir os valores dos parâmetros
            pstmt.setString(1, typerequest.getTypeRequest());
            // 4. Executar o statement
            pstmt.executeUpdate();  // executeUpdate para operações que modificam o banco de dados
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o Tipo de Solicitação: " + e.getMessage());
            throw e;  // Re-throw para que o chamador possa tratar, se necessário
        }
    }
    //metodo que altera o tipo solicitacao por id

    public void update(TypeRequest typerequest) throws ClassNotFoundException, SQLException {
        //1 Query SQL corrigida para o statusRoom com valor fixo
        String sql = "UPDATE Typerequest SET typeRequest = ? WHERE idTypeRequest = ?";
        // 2. Tentar obter a conexão com o banco de dados
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            //3. Definir os valores dos parâmetros
            pstmt.setString(1, typerequest.getTypeRequest());
            pstmt.setInt(2, typerequest.getIdTypeRequest());
            // 4. Executar o statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o Tipo de Solicitação: " + e.getMessage());
            throw e;  // Re-throw para que o chamador possa tratar, se necessário
        }
    }

    //metodo para delete o tipo de solicitacao por id 
    public void delete(TypeRequest typerequest) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "DELETE FROM TypeRequest where idTypeRequest = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, typerequest.getIdTypeRequest());
        pstmt.execute();
        //4
        conexao.close();
    }
    //metodo que pesquisa os tipos de solicitacao

    public List<TypeRequest> findAll() throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "select * from TypeRequest";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<TypeRequest> lista = new ArrayList<TypeRequest>();
        while (rs.next()) {
            TypeRequest ts = new TypeRequest();
            ts.setIdTypeRequest(rs.getInt("idTypeRequest"));
            ts.setTypeRequest(rs.getString("typeRequest"));

            lista.add(ts);
        }
        //4
        return lista;
    }
    //metodo que pesquisa os tipos de solicitacao por id 

    public TypeRequest findById(TypeRequest TypeRequestbyid) throws ClassNotFoundException, SQLException {
        String sql = "select * from TypeRequest where idTypeRequest = ?";
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, TypeRequestbyid.getIdTypeRequest());
            ResultSet rs = pstmt.executeQuery();

            TypeRequest typerequest = new TypeRequest();
            if (rs.next()) {
                typerequest.setIdTypeRequest(rs.getInt("idTypeRequest"));
                typerequest.setTypeRequest(rs.getString("typeRequest"));
            }
            return typerequest;
        } catch (SQLException e) {
            System.err.println("Erro ao consultar o tipo solicitação o Tipo de Solicitação: " + e.getMessage());
            throw e;  // Re-throw para que o chamador possa tratar, se necessário
        }
        //4
    }
    
    public List<TypeRequest> idAndTypeRequest() throws ClassNotFoundException, SQLException {
        List<TypeRequest> TypeRequests = new ArrayList<>();
        String sql = "SELECT idTypeRequest, typeRequest from TypeRequest;";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                 TypeRequest typerequest = new TypeRequest(); 
                typerequest.setIdTypeRequest(rs.getInt("idTypeRequest"));
                typerequest.setTypeRequest(rs.getString("typeRequest"));
                TypeRequests.add(typerequest);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os números dos quartos: " + e.toString(), e);
        }
        return TypeRequests;
    }
    
    public boolean hasRequest(int idtype) throws ClassNotFoundException, SQLException {
    String sql = "SELECT * FROM TypeRequest t INNER JOIN Request r ON t.idTypeRequest = r.id_TypeRequest WHERE t.idTypeRequest = ?;";
    try (Connection conexao = DatabaseConnection.getConnection(); 
         PreparedStatement pstmt = conexao.prepareStatement(sql)) {
        pstmt.setInt(1, idtype);
        ResultSet rs = pstmt.executeQuery();
        // Retorna true se houver ao menos um registro (ou seja, uma reserva associada)
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna false caso não haja reservas para o quarto
}
}
