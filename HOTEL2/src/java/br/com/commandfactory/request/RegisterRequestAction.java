/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.request;

import dao.AccountDAO;
import dao.RoomDAO;
import dao.RequestDAO;
import java.sql.SQLException;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Room;
import model.Request;
import model.Email;

/**
 *
 * @author darkn
 */
public class RegisterRequestAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RequestDAO requestdao = new RequestDAO();
        AccountDAO accountdao = new AccountDAO();
        RoomDAO roomdao = new RoomDAO();

        try {
            // Busca o funcionário com base no documento fornecido na requisição
            Account account = accountdao.findByEmailId(request.getParameter("txtemail").toLowerCase());
            //verificando se localiza algum de acordo com o documento encaminhado

            if (account == null) {
                request.setAttribute("error", "Funcionário não encontrado. Verifique o Email e tente novamente.");
                return "requestRegister.jsp";

            }
            // Busca o room com base no documento fornecido na requisição
            Room room = roomdao.findByRoomNumberIdAll(request.getParameter("txtroomnumber"));
            //verificando se localiza algum de acordo com o documento encaminhado
            if (room == null) {
                request.setAttribute("error", "Quarto não encontrado. Verifique o número e tente novamente.");
                return "requestRegister.jsp";
            }

            Request requestClass = Request.getBuilder()
                    .comIdRoom(room.getIdRoom())
                    .withIdCollaboratorRequest(Integer.parseInt(request.getParameter("txtidremetente")))
                    .withIdCollaboratorResponse(account.getId_Employee())
                    .withIdTypeRequest(Integer.parseInt(request.getParameter("txtidtyperequest")))
                    .withdDescriptionRequest(request.getParameter("txtdescriptionrequest"))
                    .withRequestStatus(request.getParameter("txtrequestStatus"))
                    .build();

            String validationErrors = requestClass.validateRequestFields(
                    request.getParameter("txtdescriptionrequest")
            );

            if (!validationErrors.isEmpty()) {
                // Se houver erros de validação, os exibe na página de atualização
                request.setAttribute("error", validationErrors);
                List<Request> listarequest = requestdao.findByPendingRequest();
                request.setAttribute("listarequest", listarequest);
                return "request";
            }

            requestdao.register(requestClass);
            // preparar o Email
            String email = request.getParameter("txtemail").toLowerCase();
            String subject = "Aviso de solicitação";
            String body = "Você recebeu uma solicitação referente ao quarto "+request.getParameter("txtroomnumber")+" ! segue a descrição: " + request.getParameter("txtdescriptionrequest");
            // Enviar o Email
            Email emailModel = Email.getBuilder()
                    .comTo(email)
                    .comSubject(subject)
                    .comBody(body)
                    .constroi();
            emailModel.send(); // Certifique-se de que a classe Email tenha um método send()

            request.setAttribute("message", "Um email de solicitação foi enviado para " + email);
            List<Request> listarequest = requestdao.findByPendingRequest();
            request.setAttribute("listarequest", listarequest);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (MessagingException ex) {
            request.setAttribute("erro", "Erro ao enviar o email: " + ex.getMessage());
        }
        return "request.jsp";
    }

}