package com.caffeine.artenon.Model;

public class UserDetails {

    String uid, email, pass, time;

    public UserDetails() {}

    public UserDetails(String uid, String email, String pass, String time) {
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getTime() {
        return time;
    }
}
