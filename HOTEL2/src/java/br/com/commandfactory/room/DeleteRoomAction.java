/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.room;

import dao.RoomDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class DeleteRoomAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Room room = Room.getBuilder()
                .withIdRoom(Integer.parseInt(request.getParameter("txtIdRoom")))
                .build();
        RoomDAO roomdao = new RoomDAO();
        try {
            if (roomdao.hasReservations(room.getIdRoom())) {
                request.setAttribute("error", "Não é possível deletar o quarto, pois já teve ou tem uma reserva.");
                request.setAttribute("allRoom", roomdao.findAll()); // Adiciona a lista de clientes
                return "room.jsp"; // Ou exibe mensagem na mesma página
            }

            roomdao.delete(room);
            List<Room> allRoom = roomdao.findAll();
            request.setAttribute("allRoom", allRoom);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "room.jsp";
    }
}
