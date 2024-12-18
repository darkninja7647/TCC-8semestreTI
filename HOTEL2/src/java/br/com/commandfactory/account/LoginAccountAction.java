/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.account;

import dao.AccountDAO;
import dao.RoomDAO;
import dao.RequestDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Room;
import model.Request;


/**
 *
 * @author darkn
 */
public class LoginAccountAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        AccountDAO accountdao = new AccountDAO();

        Account account = Account.getBuilder()
                .withemail(request.getParameter("txtEmail").toLowerCase())
                .withAccountPassword(request.getParameter("txtAccountPassword"))
                .build();

        try {
            Account userAuth = accountdao.Login(account);

            //  senha capturada e compará-la com a senha do banco de dados
            if (userAuth != null && account.getAccountPassword().equals(userAuth.getAccountPassword())) {
                userAuth.iniciarSessaoUsuario(request); // Cria a sessão com as informações de userAuth
                if (userAuth.getAccessLevel().equals("funcionario")) {
                    RequestDAO requestdao = new RequestDAO();
                    account.setId_Employee(userAuth.getId_Employee());
                    try {
                        List<Request> allPendingRequests = requestdao.findByPendingRequest();
                        List<Request> filteredRequests = new ArrayList<>();

                        for (Request req : allPendingRequests) {

                            if (req.getCollaboratorResponse().getIdEmployee() == account.getId_Employee()) {
                                filteredRequests.add(req);
                            }
                        }

                        // Define a lista filtrada como atributo para o JSP
                        request.setAttribute("listarequest", filteredRequests);
                    } catch (ClassNotFoundException | SQLException ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }

                    return "request.jsp";
                }
                RoomDAO roomdao = new RoomDAO();

                List<Room> allRoom = roomdao.findAll();
                request.setAttribute("allRoom", allRoom);

                return "room.jsp";
            } else {
                return "erro.jsp";
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ClassNotFound: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage());
        }
        return "erro.jsp";
    }
}
