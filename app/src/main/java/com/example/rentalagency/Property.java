package com.example.rentalagency;
import java.util.Date;

public class Property {
    String Address;
    int Bedrooms;
    String City;
    int Floors;
    int Hike;
    String Owner;
    int Pincode;
    int Rent;
    String State;
    Date yoc;
    public Property(){

    }

    public Property(String address, int bedrooms, String city, int floors, int hike, String owner, int pincode, int rent, String state, Date yoc) {
        Address = address;
        Bedrooms = bedrooms;
        City = city;
        Floors = floors;
        Hike = hike;
        Owner = owner;
        Pincode = pincode;
        Rent = rent;
        State = state;
        this.yoc = yoc;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getBedrooms() {
        return Bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        Bedrooms = bedrooms;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getFloors() {
        return Floors;
    }

    public void setFloors(int floors) {
        Floors = floors;
    }

    public int getHike() {
        return Hike;
    }

    public void setHike(int hike) {
        Hike = hike;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public int getRent() {
        return Rent;
    }

    public void setRent(int rent) {
        Rent = rent;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getYoc() {
        return yoc;
    }

    public void setYoc(Date yoc) {
        this.yoc = yoc;
    }
}
