package com.example.hotelmanagement;


public class Room {
    private String type;
    private String name;
    private String amount;
    private boolean resereved;

    public Room() {
    }

    public Room(String type, String name, String amount, boolean reserved) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.resereved = reserved;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isResereved() {
        return resereved;
    }

    public void setResereved(String resereved) {
        this.resereved = Boolean.valueOf(resereved);
    }
}

