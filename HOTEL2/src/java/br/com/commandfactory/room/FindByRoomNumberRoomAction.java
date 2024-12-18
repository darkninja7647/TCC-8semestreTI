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
 * @author darkn
 */
public class FindByRoomNumberRoomAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Room room = Room.getBuilder()
        .withRoomNumber(request.getParameter("txtRoomNumber"))
        .build();
        RoomDAO roomdao = new RoomDAO();
        try {
            room = roomdao.findByRoomNumber(room);
            request.setAttribute("lista", room);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "roomFindByRoomNumber.jsp";
    }
}
