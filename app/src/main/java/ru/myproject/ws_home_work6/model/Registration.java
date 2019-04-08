package ru.myproject.ws_home_work6.model;
import android.os.Parcel;


import java.io.Serializable;


public class Registration implements Serializable {

    private String email;

    private String password;


    public Registration(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
