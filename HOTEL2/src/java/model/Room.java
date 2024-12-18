/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LIDIA
 */
public class Room {

    private int idRoom;
    private int capacity;
    private int floor;
    private String typeroom;
    private String statusRoom;
    private String roomnumber;
    

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor(){
        return floor;
    }
    
    public void setFloor (int floor){
        this.floor = floor;
    }
    
    public String geTyperoom() {
        return typeroom;
    }

    public void setTyperoom(String typeroom) {
        this.typeroom = typeroom;
    }

    public String getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(String statusRoom) {
        this.statusRoom = statusRoom;
    }
    
    // ----------------------------BUILDER-------------------------------------
    public static RoomBuilder getBuilder(){
        return new RoomBuilder();
    }
    public static class RoomBuilder {
        
        private Room room = new Room();
        
        public RoomBuilder withIdRoom(int idRoom){
            room.idRoom = idRoom;
            return this;
        }
        public RoomBuilder withCapacity (int capacity){
            room.capacity = capacity;
            return this;
        }

        public RoomBuilder withFloor (int floor){
            room.floor = floor;
            return this;
        }
        public RoomBuilder withTypeRoom (String typeroom){
            room.typeroom = typeroom;
            return this;
        }
        public RoomBuilder withStatusRoom (String statusRoom){
            room.statusRoom = statusRoom;
            return this;
        }
        
         public RoomBuilder withRoomNumber (String roomnumber){
            room.roomnumber = roomnumber;
            return this;
        }
        public Room build(){
            return room;
        }
    }
    
    // ----------------------------Metodos-------------------------------------
    // validacao de erros de algumas variaveis

   public static String validateRoomFields(String capacity, String floor, String typeRoom, String roomNumber) {
    StringBuilder validationMessage = new StringBuilder();

    // Validação dos campos usando um mapa de campos
    Map<String, String> fieldValidations = new HashMap<>();
    fieldValidations.put("Número do quarto", roomNumber);
    fieldValidations.put("Capacidade", capacity);
    fieldValidations.put("Andar", floor);

    // Verificando cada campo
    for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
        // Verifica se o campo está vazio ou nulo
        if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
            validationMessage.append("O ").append(entry.getKey()).append(" campo é obrigatório.\\n");
        }
    }

    // Se a capacidade não for um número válido ou for menor que 1
    try {
        if (Integer.parseInt(capacity) <= 0) {
            validationMessage.append("A capacidade deve ser um número positivo e maior que 0.<br>");
        }
    } catch (NumberFormatException e) {
        validationMessage.append("A capacidade deve ser um número válido.\\n");
    }
    
    // Se o andar não for um número válido ou for menor que 1
    try {
        if (Integer.parseInt(floor) <= 0) {
            validationMessage.append("O andar deve ser um número positivo e maior que 0.<br>");
        }
    } catch (NumberFormatException e) {
        validationMessage.append("O andar deve ser um número válido.\\n");
    }

    return validationMessage.toString();
}
}