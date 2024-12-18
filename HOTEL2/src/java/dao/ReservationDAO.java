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
import java.util.Date;
import java.util.List;
import model.Customer;
import util.DatabaseConnection;
import model.Reservation;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class ReservationDAO {

    // Método para encerrar uma reserva, alterando para finalized.
    public void EndReservation(Reservation reservation) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE reservation SET statusReservation = 'Encerrado' WHERE idReservation = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getIdReservation());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao encerrar a reserva: " + e.getMessage());
            throw e;  // Re-throw para que o chamador possa tratar, se necessário
        }
    }

    

    //     * Método para cadastrar uma nova reserva no banco de dados.
    public void register(Reservation reservation) throws ClassNotFoundException, SQLException {
        // Verifica se o quarto está disponível
        if (!isRoomAvailable(reservation.getId_Room(), reservation.getStartDate(), reservation.getEndDate())) {
            throw new SQLException("Este quarto já possui uma reserva ativa no período informado.");
        }

        String sql = "INSERT INTO Reservation(statusReservation, id_Customer, id_Room, startDate, endDate) "
                + "VALUES ('Ativo', ?, ?, ?, ?)";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getId_Customer());
            pstmt.setInt(2, reservation.getId_Room());
            pstmt.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            pstmt.execute();
        }
    }

    public boolean isRoomAvailable(int roomId, Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        String sql = "SELECT COUNT(*) AS conflictCount "
                + "FROM Reservation "
                + "WHERE id_Room = ? "
                + "AND statusReservation = 'Ativo' "
                + "AND ((? BETWEEN startDate AND endDate) OR (? BETWEEN startDate AND endDate) OR (startDate BETWEEN ? AND ?))";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, roomId);
            pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
            pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(5, new java.sql.Date(endDate.getTime()));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("conflictCount") == 0; // Retorna true se não houver conflitos
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar disponibilidade do quarto: " + e.getMessage());
            throw e;
        }
        return false;
    }

    // Método para consultar todas as reservas ativas.
    public List<Reservation> queryByActive() throws ClassNotFoundException, SQLException {
        Connection conexao = DatabaseConnection.getConnection();
        String sql = "SELECT b.idReservation,r.roomnumber,c_customer.nameCustomer AS customer,b.startDate,b.endDate FROM Reservation b INNER JOIN Customer c_customer ON b.id_Customer = c_customer.idCustomer INNER JOIN  Room r ON b.id_Room = r.idRoom WHERE  b.statusReservation = 'Ativo'";

        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        // 4. Criar uma lista para armazenar os resultados
        List<Reservation> listReservation = new ArrayList<>();

        // 5. Iterar pelos resultados e preencher a lista de solicitações
        while (rs.next()) {
            Room room = new Room();
            room.setRoomnumber(rs.getString("roomnumber"));

            Customer customer = new Customer();
            customer.setNameCustomer(rs.getString("customer"));

            Reservation reservation = new Reservation();
            reservation.setIdReservation(rs.getInt("idReservation"));
            reservation.setRoom(room);  // Associando a sala
            reservation.setCustomer(customer);  // Associando o cliente
            reservation.setStartDate(rs.getDate("startDate"));  // Atribuindo a data de início
            reservation.setEndDate(rs.getDate("endDate"));  // Atribuindo a data de fim

            listReservation.add(reservation);
        }
        rs.close();
        pstmt.close();
        conexao.close();
        return listReservation;
    }

}
