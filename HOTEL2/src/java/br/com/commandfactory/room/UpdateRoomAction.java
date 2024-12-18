/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.room;

import dao.RoomDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class UpdateRoomAction implements ICommand {
    
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Room room = Room.getBuilder()
                .withIdRoom(Integer.parseInt(request.getParameter("txtIdRoom")))
                .build();
        RoomDAO roomdao = new RoomDAO();
        // Consulta o Room por ID e atribui o resultado ao objeto

        try {
             if (roomdao.hasReservations(room.getIdRoom())) {
                request.setAttribute("error", "Não é possivel atualizar o quarto, pois já tem uma reserva.");
                request.setAttribute("allRoom", roomdao.findAll()); // Adiciona a lista de clientes
                return "room.jsp"; // Ou exibe mensagem na mesma página
            }
            room = roomdao.findById(room);
            request.setAttribute("room", room);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "roomUpdate.jsp";
    }
}
