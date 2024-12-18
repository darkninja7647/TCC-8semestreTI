/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.room;

import dao.RoomDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class ListAllRoomAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RoomDAO roomdao = new RoomDAO();
        try {
            List<Room> allRoom = roomdao.findAll();
                request.setAttribute("allRoom", allRoom);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "room.jsp";
    }
}
