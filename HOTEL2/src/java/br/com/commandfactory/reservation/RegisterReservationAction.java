package br.com.commandfactory.reservation;

import dao.ReservationDAO;
import dao.CustomerDAO;
import dao.RoomDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reservation;
import model.Customer;
import model.Room;

/**
 *
 * @author LIDIA
 */
public class RegisterReservationAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        ReservationDAO reservationdao = new ReservationDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        RoomDAO roomdao = new RoomDAO();

        try {
            // Busca o cliente com base no documento fornecido na requisição
            Customer customer = customerDAO.findByDocumentId(request.getParameter("txtdocumentoidentifiers"));
            if (customer == null) {
                request.setAttribute("error", "Cliente não encontrado. Verifique o documento e tente novamente.");
                return "reservationRegister.jsp";
            }

            // Busca o quarto com base no número fornecido na requisição
            Room room = roomdao.findByRoomNumberIdAll(request.getParameter("txtroomnumber"));
            if (room == null) {
                request.setAttribute("error", "Quarto não encontrado. Verifique o número e tente novamente.");
                return "reservationRegister.jsp";
            }

            // Datas da reserva
            String startDateStr = request.getParameter("txtstartdate");
            String endDateStr = request.getParameter("txtenddate");

            // Validação de dados específicos da reserva
            Reservation reservation = new Reservation();
            String validationErrors = reservation.validateReservation(startDateStr, endDateStr);
            if (!validationErrors.isEmpty()) {
                request.setAttribute("error", validationErrors);
                return "reservationRegister.jsp";
            }
            
            // Criação da reserva
            Reservation reservationbuilder = Reservation.getBuilder()
                    .withIdCustomer(customer.getIdCustomer())
                    .withIdRoom(room.getIdRoom())
                    .withStartDate(startDateStr)
                    .withEndDate(endDateStr)
                    .build();

            // Validação de conflitos de reserva no quarto
            if (!reservationdao.isRoomAvailable(room.getIdRoom(), reservationbuilder.getStartDate(), reservationbuilder.getEndDate())) {
                request.setAttribute("error", "O quarto já possui uma reserva ativa para o período informado.");
                return "reservationRegister.jsp";
            }

           

            // Registro da reserva no banco de dados
            reservationdao.register(reservationbuilder);

            // Atualização da lista de reservas
            List<Reservation> listReservation = reservationdao.queryByActive();
            request.setAttribute("listReservation", listReservation);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            request.setAttribute("error", "Erro ao processar a reserva: " + ex.getMessage());
            return "reservationRegister.jsp";
        }

        // Retorna para a página de reservas
        return "reservation.jsp";
    }
}