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
import model.Room;
import util.FabricaConexao;

/**
 *
 * @author LIDIA
 */
public class RoomDAO {

    public void cadastrar(Room room) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "INSERT INTO Room(capacity, accessLevel, statusRoom)VALUES(?,?,?)";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, room.getCapacity());
        pstmt.setInt(2, room.getAccessLevel());
        pstmt.setString(3, room.getStatusRoom());
        pstmt.execute();
        //4
        conexao.close();
    }
    public void alterar(Room room) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "UPDATE Room SET capacity = ?, accessLevel = ?, statusRoom = ? WHERE idRoom = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, room.getCapacity());
        pstmt.setInt(2, room.getAccessLevel());
        pstmt.setString(3, room.getStatusRoom());
        pstmt.setInt(4, room.getId());
        pstmt.execute();
        //4
        conexao.close();

    }

    public void excluir(Room room) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "DELETE FROM room where idRoom = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, room.getId());
        pstmt.execute();
        //4
        conexao.close();
    }

    public List<Room> consultar() throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "SELECT * FROM Room";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Room> lista = new ArrayList<Room>();
        while (rs.next()) {
            Room room = new Room();
            room.setId(rs.getInt("idRoom"));
            room.setCapacity(rs.getInt("capacity"));
            room.setAccessLevel(rs.getInt("accessLevel"));
            room.setStatusRoom(rs.getString("statusRoom"));
            lista.add(room);
        }
        //4
        return lista;
    }
    
    public Room consultarById(Room room) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "select * from Room where idRoom = ?";
        //3       
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, room.getId());
        ResultSet rs = pstmt.executeQuery();

        Room cli = new Room();
        if (rs.next()) {
            cli.setId(rs.getInt("idRoom"));
            cli.setCapacity(rs.getInt("capacity"));
            cli.setAccessLevel(rs.getInt("accessLevel"));
            cli.setStatusRoom(rs.getString("statusRoom"));
        }
        //4
        return cli;
    }
}
