package com.example.myapplication;

public class Users {
    String id;
    String first;
    String last;
    String mail;

    public Users(){

    }

    public Users(String id, String firstName, String lastname, String email) {
        this.id = id;
        this.first = firstName;
        this.last = lastname;
        this.mail = email;
    }

    public String getid(){
        return first;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastname() {
        return last;
    }

    public String getEmail() {
        return mail;
    }
}
