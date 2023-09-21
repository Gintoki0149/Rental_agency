package com.example.rentalagency;

public class Request {
    private String From;
    private String To;
    private String message;
    private String address;
    private String status;
    public Request(String From,String To,String message, String address, String status){
        this.From = From;
        this.To = To;
        this.message = message;
        this.address = address;
        this.status = status;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public String getMessage() {
        return message;
    }

    public String getAddress(){return address;}

    public String getStatus(){return status;}
}
