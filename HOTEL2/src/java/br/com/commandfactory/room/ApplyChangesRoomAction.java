/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.room;

import dao.RoomDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class ApplyChangesRoomAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RoomDAO roomdao = new RoomDAO();
        StringBuilder errorMessage = new StringBuilder();

        try {

// Validação dos campos
            String validationError = Room.validateRoomFields(request.getParameter("txtCapacity"),
                    request.getParameter("txtFloor"),
                    request.getParameter("txtTypeRoom"),
                    request.getParameter("txtRoomNumber")
            );
            // Construção do objeto Room com o Builder
            Room room = Room.getBuilder()
                    .withIdRoom(Integer.parseInt(request.getParameter("txtIdRoom"))) // ID do quarto
                    .withRoomNumber(request.getParameter("txtRoomNumber")) // Número do quarto
                    .withCapacity(Integer.parseInt(request.getParameter("txtCapacity"))) // Capacidade
                    .withFloor(Integer.parseInt(request.getParameter("txtFloor"))) // Andar
                    .withTypeRoom(request.getParameter("txtTypeRoom")) // Tipo do quarto
                    .build();

            
            Room existingRoomNumber = roomdao.findByRoomNumber(room);
            if (existingRoomNumber != null
                    && existingRoomNumber.getIdRoom()!= room.getIdRoom()) {
                // O documento já existe para outro funcionário
                request.setAttribute("error", "Este número de quarto já está associado a outro quarto.");
                room = roomdao.findById(room);
            request.setAttribute("room", room);
                return "roomUpdate.jsp";
            } else if (existingRoomNumber != null
                    && !existingRoomNumber.getRoomnumber().equals(room.getRoomnumber())) {
                // O documento informado não bate com o registrado anteriormente
                request.setAttribute("error", "O número de quarto inserido não corresponde ao já registrado para este quarto.");
                room = roomdao.findById(room);
            request.setAttribute("room", room);
                return "roomUpdate.jsp";
            }
            // Se houver erro de validação, define a mensagem e retorna ao formulário
            if (!validationError.isEmpty()) {
                request.setAttribute("error", validationError);
                return "roomUpdate.jsp"; // Volta para o formulário
            }

            // Chamada para o método DAO com tratamento de erro
            roomdao.update(room);

            // Lista atualizada de rooms
            List<Room> lista = roomdao.findAll();
            request.setAttribute("allRoom", lista);

        } catch (SQLException | ClassNotFoundException ex) {
            errorMessage.append("Erro ao tentar atualizar o quarto: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "roomUpdate.jsp"; // Retorna ao formulário em caso de erro de DAO

        } catch (NumberFormatException ex) {
            errorMessage.append("Erro no formato dos dados: ").append(ex.getMessage()).append("\\n");
            request.setAttribute("error", errorMessage.toString());
            return "roomUpdate.jsp"; // Volta ao formulário em caso de erro de formato
        }

        return "room.jsp"; // Redireciona para a lista de rooms em caso de sucesso
    }
}
