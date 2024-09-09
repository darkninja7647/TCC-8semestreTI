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
public class AlterarRoomAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Room cli = Room.getBuilder()
        .comId(Integer.parseInt(request.getParameter("txtid")))
        .constroi();
        RoomDAO roomdao = new RoomDAO();
        try {
            cli = roomdao.consultarById(cli);
            request.setAttribute("room", cli);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "reservationalterado.jsp";
    }
}
