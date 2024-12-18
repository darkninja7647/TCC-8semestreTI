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
import model.Room;
import util.DatabaseConnection;

/**
 *
 * @author LIDIA
 */
public class RoomDAO {

    //metodo para cadastrar/registrar um quarto
    public void register(Room room) throws ClassNotFoundException, SQLException {
        // 1. Obter a conexão com o banco de dados

        String sql = "INSERT INTO Room(capacity, floor, typeroom, roomnumber, statusRoom) VALUES (?, ?, ?, ?, 'Livre')";

        // 2. Query SQL corrigida para o statusRoom com valor fixo
        try (Connection conexao = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, room.getCapacity());
            pstmt.setInt(2, room.getFloor());
            pstmt.setString(3, room.geTyperoom());
            pstmt.setString(4, room.getRoomnumber());
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cliente: " + e.toString(), e);
        }
    }

    //metodo que atualiza as informacoes de um quarto
    public void update(Room room) throws ClassNotFoundException, SQLException {
        //1

        String sql = "UPDATE Room SET capacity = ?, floor = ?, typeroom = ?, roomnumber = ? WHERE idRoom = ?";

        try (Connection conexao = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, room.getCapacity());
            pstmt.setInt(2, room.getFloor());
            pstmt.setString(3, room.geTyperoom());
            pstmt.setString(4, room.getRoomnumber());
            pstmt.setInt(5, room.getIdRoom());
            pstmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Erro: O documento identificador já existe. Por favor, utilize um documento único.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cliente: " + e.toString(), e);
        }
    }
//metodo de deletar um quarto

    public void delete(Room room) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM room where idRoom = ?";

        try (Connection conexao = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, room.getIdRoom());
            pstmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir cliente: " + e.toString(), e);
        }
    }

    //metodo para procurar varios quartos 
    public List<Room> findAll() throws ClassNotFoundException, SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT idRoom, roomnumber, capacity, floor, typeroom, statusRoom FROM Room";

        try (Connection conexao = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setIdRoom(rs.getInt("idRoom"));
                room.setRoomnumber(rs.getString("roomnumber"));
                room.setCapacity(rs.getInt("capacity"));
                room.setFloor(rs.getInt("floor"));
                room.setTyperoom(rs.getString("typeroom"));
                room.setStatusRoom(rs.getString("statusRoom"));
                rooms.add(room);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao consultar quartos: " + e.getMessage());
            e.printStackTrace();
        }
        return rooms;
    }

    //metodo que procura um quarto por id, introduzindo uma variavel 
    public Room findById(Room roomid) throws ClassNotFoundException, SQLException {
        Room room = null; // Declarado, mas não instanciado
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT idRoom, roomnumber, capacity, floor, typeroom, statusRoom FROM Room WHERE idRoom = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, roomid.getIdRoom());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                room = new Room(); // Instanciado apenas se houver dados no ResultSet
                room.setIdRoom(rs.getInt("idRoom"));
                room.setRoomnumber(rs.getString("roomnumber"));
                room.setCapacity(rs.getInt("capacity"));
                room.setFloor(rs.getInt("floor"));
                room.setTyperoom(rs.getString("typeroom"));
                room.setStatusRoom(rs.getString("statusRoom"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar todos os clientes: " + e.toString(), e);
        }
        return room;
    }

    //metodo para procurar um quarto atraves do numero do quarto
    public Room findByRoomNumber(Room roomNumber) throws ClassNotFoundException, SQLException {
        Room room = null;
        String sql = "SELECT idRoom,roomnumber, capacity, floor, typeroom, statusRoom FROM Room where roomnumber = ?";
//1
        try (Connection conexao = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, roomNumber.getRoomnumber());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                room = new Room();
                room.setIdRoom(rs.getInt("idRoom"));
                room.setRoomnumber(rs.getString("roomnumber"));
                room.setCapacity(rs.getInt("capacity"));
                room.setFloor(rs.getInt("floor"));
                room.setTyperoom(rs.getString("typeroom"));
                room.setStatusRoom(rs.getString("statusRoom"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar todos os clientes: " + e.toString(), e);
        }
        return room;
    }

    //metodo que procura O room pelo numero do quarto
    
    public Room findByRoomNumberIdAll(String roomnumber) throws ClassNotFoundException, SQLException {
        //1
        Room foundRoom = null;
        String sql = "SELECT idRoom FROM Room WHERE roomnumber = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, roomnumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    foundRoom = new Room();
                    foundRoom.setIdRoom(rs.getInt("idRoom"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar o quarto por ID: " + e.toString(), e);
        }
        return foundRoom;
    }

    public List<Room> RoomNumbersAndTypes() throws ClassNotFoundException, SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT roomnumber,typeroom FROM Room where statusRoom = 'Livre'";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                 Room room = new Room(); 
                room.setRoomnumber(rs.getString("roomnumber"));
                room.setTyperoom(rs.getString("typeroom"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os números dos quartos: " + e.toString(), e);
        }
        return rooms;
    }
    
      public List<Room> RoomNumbersAndTypesAll() throws ClassNotFoundException, SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT roomnumber,typeroom,statusRoom FROM Room";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                 Room room = new Room(); 
                room.setRoomnumber(rs.getString("roomnumber"));
                room.setTyperoom(rs.getString("typeroom"));
                room.setStatusRoom(rs.getString("statusRoom"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os números dos quartos: " + e.toString(), e);
        }
        return rooms;
    }
    public boolean hasReservations(int roomId) throws ClassNotFoundException, SQLException {
    String sql = "SELECT * FROM Room r INNER JOIN Reservation res ON r.idRoom = res.id_Room WHERE r.idRoom = ?";
    try (Connection conexao = DatabaseConnection.getConnection(); 
         PreparedStatement pstmt = conexao.prepareStatement(sql)) {
        pstmt.setInt(1, roomId);
        ResultSet rs = pstmt.executeQuery();
        // Retorna true se houver ao menos um registro (ou seja, uma reserva associada)
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna false caso não haja reservas para o quarto
}
}
