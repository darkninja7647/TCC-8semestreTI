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
public class DeletarRoomAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response){
        Room room = Room.getBuilder()
        .comId(Integer.parseInt(request.getParameter("txtid")))
        .constroi();
        RoomDAO roomdao = new RoomDAO();
        try {
            roomdao.excluir(room);
            List<Room> lista = roomdao.consultar();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "reservation.jsp";
    }
}
