/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author LIDIA
 */
public class Reservation {

    
    private int idReservation;
    private String tempoReserva;
    private int ID_Customer;
    private int ID_Room;
    
    
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    
    public String getTempoReserva() {
        return tempoReserva;
    }

    public void setTempoReserva(String tempoReserva) {
        this.tempoReserva = tempoReserva;
    }

    public int getID_Customer() {
        return ID_Customer;
    }

    public void setID_Customer(int ID_Customer) {
        this.ID_Customer = ID_Customer;
    }

    public int getID_Room() {
        return ID_Room;
    }

    public void setID_Room(int ID_Room) {
        this.ID_Room = ID_Room;
    }   
    
    // ----------------------------BUILDER-------------------------------------
    
    public static ReservationBuilder getBuilder(){
        return new ReservationBuilder();
    }
    public static class ReservationBuilder {
        private Reservation reserva = new Reservation();
        
        public ReservationBuilder comId (int idReservation){
            reserva.idReservation = idReservation;
            return this;
        }
        public ReservationBuilder comTempoReserva (String tempoReserva){
            reserva.tempoReserva = tempoReserva;
            return this;
        }
        public ReservationBuilder comIDCustomer (int idCustomer){
            reserva.ID_Customer = idCustomer;
            return this;
        }
        public ReservationBuilder comIDRoom (int idRoom){
            reserva.ID_Room = idRoom;
            return this;
        }
        public Reservation constroi(){
            return reserva;
        }   
    }
    
}
