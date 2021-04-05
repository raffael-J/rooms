package com.example.hotelmanagement.database.entity;

public class Reservation {
    private String firstname;
    private String lastname;
    private String roomname;
    private String checkIn;
    private String checkOut;



    public Reservation() {

    }

    public Reservation(String firstname, String lastname, String roomname, String checkIn, String checkOut) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.roomname = roomname;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
