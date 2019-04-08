package ru.myproject.ws_home_work6.model;

import java.io.Serializable;

public class StatusRegistration implements Serializable {


    private  String status;

    public StatusRegistration (){

    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
