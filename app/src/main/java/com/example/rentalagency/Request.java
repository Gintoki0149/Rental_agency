package com.example.rentalagency;

public class Request {
    private String From;
    private String To;
    private String message;
    public Request(String From,String To,String message){
        this.From = From;
        this.To = To;
        this.message = message;
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
}
