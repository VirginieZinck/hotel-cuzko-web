package com.example.hotelcuzcoweb.DataProviders.WebServices;

import lombok.ToString;

public class Mail {

    public int id = 1;

    public int userId = 1;

    public String title;

    public String body;

    public Mail() {
    }

    public Mail(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
