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
public class RegisterRoomAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RoomDAO roomdao = new RoomDAO();
        // Realizando a validação dos campos
        try {
            String errorMessage = Room.validateRoomFields(
                    request.getParameter("txtCapacity"),
                    request.getParameter("txtFloor"),
                    request.getParameter("txtTypeRoom"),
                    request.getParameter("txtRoomNumber"));

            // Se houver erros, retorna ao formulário com a mensagem de erro
            if (!errorMessage.isEmpty()) {
                request.setAttribute("error", errorMessage);
                return "roomRegister.jsp"; // Retorna ao formulário para correções
            }

            // Criação do objeto Room após validação dos campos
            Room room = Room.getBuilder()
                    .withRoomNumber(request.getParameter("txtRoomNumber")) // Número do quarto
                    .withCapacity(Integer.parseInt(request.getParameter("txtCapacity"))) // Capacidade
                    .withFloor(Integer.parseInt(request.getParameter("txtFloor"))) // Andar
                    .withTypeRoom(request.getParameter("txtTypeRoom")) // Tipo do quarto
                    .build();

            Room existingRoomNumber = roomdao.findByRoomNumber(room);
            if (existingRoomNumber != null){
                request.setAttribute("error", "Este quarto já existe no sistema.");
                return "roomRegister.jsp";
            }
            // Cadastro e recuperação da lista atualizada
            roomdao.register(room);
            List<Room> allRoom = roomdao.findAll();
                request.setAttribute("allRoom", allRoom);
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("error", "nao foi realizado o registro do quarto: " + ex.getMessage().replace("\n", "\\n"));
        }

        return "room.jsp";
    }
}
