package com.sinhvien.finalproject.QuanLi.User;

public class User {
    private String id;
    private String DepName;
    private String User;
    private String Pass;
    private String Name;
    private String Phone_Number;
    private String Address;

    public User() {
    }

    public User(String id, String depName, String user, String pass, String name, String phone_Number, String address) {
        this.id = id;
        DepName = depName;
        User = user;
        Pass = pass;
        Name = name;
        Phone_Number = phone_Number;
        Address = address;
    }

    @Override
    public String toString() {
        return
                "Department: " + DepName +
                        "| Username: " + User +
                        "| Name: " + Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String depName) {
        DepName = depName;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}