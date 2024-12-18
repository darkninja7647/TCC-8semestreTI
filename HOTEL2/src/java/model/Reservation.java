/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LIDIA
 */
public class Reservation {

    private int idReservation;
    private String statusReservation;
    private int id_Customer;
    private int id_Room;
    private Date startDate;
    private Date endDate;

    private Room room;
    private Customer customer;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(String statusReservation) {
        this.statusReservation = statusReservation;
    }

    public int getId_Customer() {
        return id_Customer;
    }

    public void setId_Customer(int id_Customer) {
        this.id_Customer = id_Customer;
    }

    public int getId_Room() {
        return id_Room;
    }

    public void setId_Room(int id_Room) {
        this.id_Room = id_Room;
    }

    // Getters e Setters para os objetos relacionados
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // ----------------------------BUILDER-------------------------------------
    public static ReservationBuilder getBuilder() {
        return new ReservationBuilder();
    }

    public static class ReservationBuilder {

        private Reservation reservation = new Reservation();
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Formato para inserção no banco

        public ReservationBuilder withId(int idReservation) {
            reservation.idReservation = idReservation;
            return this;
        }

        public ReservationBuilder withStatusReservation(String statusReservation) {
            reservation.statusReservation = statusReservation;
            return this;
        }

        public ReservationBuilder withIdCustomer(int id_Customer) {
            reservation.id_Customer = id_Customer;
            return this;
        }

        public ReservationBuilder withIdRoom(int idRoom) {
            reservation.id_Room = idRoom;
            return this;
        }

        public ReservationBuilder withStartDate(String startDate) {
            try {
                Date date = dateFormat.parse(startDate);  // Converte a string para Date
                reservation.setStartDate(date);  // Configura a data de início
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }

        public ReservationBuilder withEndDate(String endDate) {
            try {
                Date date = dateFormat.parse(endDate);  // Converte a string para Date
                reservation.setEndDate(date);  // Configura a data de término
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }

        public Reservation build() {
            return reservation;
        }
    }

     // ----------------------------Metodos-------------------------------------
     public String formatStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.startDate);
    }

    public String formatEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.endDate);
    }
    
    // validacao de erros de algumas variaveis
    public static String validateReservation(String startDate, String endDate) {
        StringBuilder validationMessage = new StringBuilder();

        // Validação dos campos usando um mapa de campos
        Map<String, String> fieldValidations = new HashMap<>();
        fieldValidations.put("data de inicio", startDate);
        fieldValidations.put("data de encerramento", endDate);

        // Verificando cada campo
        for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
            // Verifica se o campo está vazio ou nulo
            if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
                validationMessage.append("O ").append(entry.getKey()).append(" campo é obrigatório.\\n");
            }
        }
//verificar o valor da data para nao ser nulo
            if (startDate != null && !startDate.trim().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(startDate);

        // Obter a data de ontem
        Date today = new Date();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 1 dia em milissegundos
        Date yesterday = new Date(today.getTime() - oneDayInMillis);
                    //verificar se a data irá acontecer.
                    if (date.before(yesterday)) {
                        validationMessage.append("A data de inicio de reserva não pode ser antes da data Atual.\\n");
                    }
                } catch (ParseException e) {
                    validationMessage.append("Erro ao formatar a data de inicio de reserva. Verifique o formato. (Formato esperado: yyyy-MM-dd)\\n");
                }
            }
        
        if (endDate != null && !endDate.trim().isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(endDate);
                Date today = new Date();
                //verificar se a data irá acontecer.
                if (date.before(today)) {
                    validationMessage.append("A data de fim de reserva não pode ser antes antes da data Atual.\\n");
                }
            } catch (ParseException e) {
                validationMessage.append("Erro ao formatar a data de fim de reserva. Verifique o formato. (Formato esperado: yyyy-MM-dd)\\n");
            }
        }
        return validationMessage.toString();
    }
}

