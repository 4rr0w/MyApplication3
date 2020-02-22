package com.example.myapplication;

import android.net.Uri;

public class Users {
    private String id;
    private String first;
    private String last;
    private String mail;
    private String link;
    private String phone;
    private String qualification;


    private Uri profileImage;

    public Users() {

    }

    public Users(String id, String firstName, String lastName, String email,String link, String phone,Uri profileImage,String qualification) {
        this.id = id;
        this.first = firstName;
        this.last = lastName;
        this.mail = email;
        this.link = link;
        this.phone = phone;
        this.profileImage = profileImage;
        this.qualification = qualification;
    }

    public String getid() { return first; }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public String getEmail() {
        return mail;
    }

    public String getLink(){ return link;}

    public String getPhone(){ return phone;}

    public Uri getProfileImage() {
        return profileImage;
    }

    public String getQualification() {
        return qualification;
    }
}