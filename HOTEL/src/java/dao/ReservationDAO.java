/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.FabricaConexao;
import model.Reservation;
/**
 *
 * @author LIDIA
 */
public class ReservationDAO {
    public void alterar(Reservation reservation) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "UPDATE Reservation SET tempoReserva = 'END' WHERE id = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, reservation.getTempoReserva());
        pstmt.execute();
        //4
        conexao.close();

    }
    public void cadastrar(Reservation reservation) throws ClassNotFoundException, SQLException {
        Connection conexao = FabricaConexao.getConexao();
        String sql = "INSERT INTO Reservation(tempoReserva,ID_Customer,ID_Room) values(?,?,?)";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, reservation.getTempoReserva());
        pstmt.setInt(2, reservation.getID_Customer());
        pstmt.setInt(3, reservation.getID_Room());
    }
}
