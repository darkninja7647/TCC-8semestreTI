/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.reservation;

import dao.ReservationDAO;
import dao.RoomDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reservation;

/**
 *
 * @author darkn
 */
public class CloseReservationAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        ReservationDAO reservationdao = new ReservationDAO();
        Reservation reservation = Reservation.getBuilder()
                .withId(Integer.parseInt(request.getParameter("txtidreservation")))
                .build();
        try {
            //encerrando a resevation
            reservationdao.EndReservation(reservation);
            List<Reservation> listReservation = reservationdao.queryByActive();
            request.setAttribute("listReservation", listReservation);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "reservation.jsp";
    }
}
