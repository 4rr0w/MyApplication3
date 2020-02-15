package com.example.myapplication;

public class Users {
    String id;
    String first;
    String last;
    String mail;
    String pass;
    String link;

    public Users() {

    }

    public Users(String id, String firstName, String lastname, String email,String pass,String link) {
        this.id = id;
        this.first = firstName;
        this.last = lastname;
        this.mail = email;
        this.pass = pass;
        this.link = link;
    }

    public String getid() { return first; }

    public String getFirstName() {
        return first;
    }

    public String getLastname() {
        return last;
    }

    public String getEmail() {
        return mail;
    }

    public String getpass() { return pass; }

    public String getlink(){ return link;}
}