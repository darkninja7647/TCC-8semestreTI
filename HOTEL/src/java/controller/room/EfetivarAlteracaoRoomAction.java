/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.room;

import dao.RoomDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class EfetivarAlteracaoRoomAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {      
        RoomDAO roomdao = new RoomDAO();
        Room room = Room.getBuilder()
                .comId(Integer.parseInt(request.getParameter("txtidRoom")))
                .comCapacity(Integer.parseInt(request.getParameter("txtcapacity")))
                .comAccessLevel(Integer.parseInt(request.getParameter("txtaccessLevel")))
                .comStatusRoom(request.getParameter("txtstatusRoom"))
                .constroi();
        try {
            roomdao.alterar(room);
            List<Room> lista = roomdao.consultar();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "reservation.jsp";
    }
}
