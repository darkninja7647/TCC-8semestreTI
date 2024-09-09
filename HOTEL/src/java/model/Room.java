/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LIDIA
 */
public class Room {
    
    private int idRoom;
    private int capacity;
    private int accessLevel;
    private String statusRoom;
    
    public int getId() {
        return idRoom;
    }

    public void setId(int id) {
        this.idRoom = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
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
        
        public RoomBuilder comId(int id){
            room.idRoom = id;
            return this;
        }
        public RoomBuilder comCapacity (int capacity){
            room.capacity = capacity;
            return this;
        }
        public RoomBuilder comAccessLevel (int accessLevel){
            room.accessLevel = accessLevel;
            return this;
        }
        public RoomBuilder comStatusRoom (String statusRoom){
            room.statusRoom = statusRoom;
            return this;
        }
        public Room constroi(){
            return room;
        }
    }
    
}
