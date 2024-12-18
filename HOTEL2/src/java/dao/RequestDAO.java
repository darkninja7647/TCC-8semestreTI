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
import model.Employee;
import model.Room;
import model.Request;
import model.TypeRequest;
import util.DatabaseConnection;

/**
 *
 * @author LIDIA
 */
public class RequestDAO {

    
    
    //metodo para cadastra a request
     public void register(Request Request) throws ClassNotFoundException, SQLException {

        //1
        Connection conexao = DatabaseConnection.getConnection();

        //2
        String sql = "INSERT INTO Request(id_Room, id_CollaboratorRequest, id_CollaboratorResponse, id_TypeRequest, startDate, descriptionRequest, requestStatus, endDate)"
                + "values (?,?,?,?,curdate(),?,'Pendente',NULL)";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, Request.getId_Room());
        pstmt.setInt(2, Request.getId_CollaboratorRequest());
        pstmt.setInt(3, Request.getId_CollaboratorResponse());
        pstmt.setInt(4, Request.getId_TypeRequest());
        pstmt.setString(5, Request.getDescriptionRequest());
        pstmt.execute();
        //4
        conexao.close();
    }

    //metodo para encerrar a request pelo id 
    public void EndRequest(Request Request) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = DatabaseConnection.getConnection();
        //2
        String sql = "UPDATE Request SET requestStatus = 'Encerrado', endDate = curdate() WHERE idRequest = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, Request.getIdRequest());
        pstmt.execute();
        //4
        conexao.close();

    }

    // Consultar onde statusRequest é Pendente
    public List<Request> findByPendingRequest() throws ClassNotFoundException, SQLException {
    // 1. Conectar ao banco de dados
    Connection conexao = DatabaseConnection.getConnection();

    // 2. Criar a consulta SQL para buscar as solicitações com status 'Pendente'
    String sql = "SELECT r_room.roomnumber AS room, e_collaboratorRequest.nameEmployee AS collaboratorRequest, e_collaboratorRequest.idEmployee AS collaboratorRequestId, "
               + "e_collaboratorResponse.nameEmployee AS collaboratorResponse, e_collaboratorResponse.idEmployee AS collaboratorResponseId, "
               + "ts.typeRequest AS TypeRequest, s.startDate, s.descriptionRequest, s.idRequest "
               + "FROM Request s "
               + "JOIN Room r_room On s.id_Room = r_room.idRoom "
               + "JOIN Employee e_collaboratorRequest ON s.id_CollaboratorRequest = e_collaboratorRequest.idEmployee "
               + "JOIN Employee e_collaboratorResponse ON s.id_CollaboratorResponse = e_collaboratorResponse.idEmployee "
               + "JOIN TypeRequest ts ON s.id_TypeRequest = ts.idTypeRequest WHERE s.Requeststatus = 'Pendente'";

    // 3. Preparar o statement e executar a consulta
    PreparedStatement pstmt = conexao.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    // 4. Criar uma lista para armazenar os resultados
    List<Request> listarequest = new ArrayList<>();

    // 5. Iterar pelos resultados e preencher a lista de solicitações
    while (rs.next()) {
        // Criar e preencher os objetos relacionados
        Room room = new Room();
        room.setRoomnumber(rs.getString("room"));

        Employee collaboratorRequest = new Employee();
        collaboratorRequest.setNameEmployee(rs.getString("collaboratorRequest"));
        collaboratorRequest.setIdEmployee(rs.getInt("collaboratorRequestId"));  // Atribui o ID do colaboradorRequest

        Employee collaboratorResponse = new Employee();
        collaboratorResponse.setNameEmployee(rs.getString("collaboratorResponse"));
        collaboratorResponse.setIdEmployee(rs.getInt("collaboratorResponseId"));  // Atribui o ID do colaboradorResponse

        TypeRequest typerequest = new TypeRequest();
        typerequest.setTypeRequest(rs.getString("TypeRequest"));

        // Criar e preencher o objeto Request
        Request request = new Request();
        request.setRoom(room);
        request.setCollaboratorRequest(collaboratorRequest);
        request.setCollaboratorResponse(collaboratorResponse);
        request.setTypeRequest(typerequest);
        request.setStartDate(rs.getDate("startDate"));
        request.setDescriptionRequest(rs.getString("descriptionRequest"));
        request.setIdRequest(rs.getInt("idRequest"));

        // Adicionar a solicitação à lista
        listarequest.add(request);
    }
    // 6. Fechar a conexão
    rs.close();
    pstmt.close();
    conexao.close();

    // 7. Retornar a lista de solicitações
    return listarequest;
}
    
     public boolean hasRequestsByAccount(int idAccount) throws SQLException, ClassNotFoundException {
        boolean hasRequests = false;
        String sql = "SELECT COUNT(*) FROM Request WHERE id_CollaboratorRequest = (SELECT id_Employee FROM AccountEmployee WHERE idAccount = ?) OR id_CollaboratorResponse = (SELECT id_Employee FROM AccountEmployee WHERE idAccount = ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idAccount);
            stmt.setInt(2, idAccount);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    hasRequests = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return hasRequests;
    }

}
