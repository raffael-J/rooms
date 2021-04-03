package com.example.hotelmanagement;

public class Reservation {
    private String firstname;
    private String lastname;
    private String roomid;


    public Reservation() {

    }

    public Reservation(String firstname, String lastname, String roomid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.roomid = roomid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}
